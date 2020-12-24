package com.happylife.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.happylife.core.common.Constants;

import java.util.concurrent.TimeUnit;

public class OAuth2Config {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.stateless(false);
        }
        public void configure(HttpSecurity http) throws Exception {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/tuoke-web/api/auth2/users/login","/swagger-ui.html", "/tuoke-web/api/idem").permitAll()
                    .antMatchers(HttpMethod.POST, "/tuoke-web/api/auth2/users").hasAnyAuthority(Constants.ROLE_ADMIN)
                    .antMatchers(HttpMethod.DELETE, "/tuoke-web/api/auth2/user2/**").hasAnyAuthority(Constants.ROLE_ADMIN)
                    .antMatchers(HttpMethod.PUT, "/tuoke-web/api/auth2/user2").hasAnyAuthority(Constants.ROLE_ADMIN)
                    .anyRequest()
                    .authenticated();
        }

        @Configuration
        @EnableAuthorizationServer
        protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

            @Autowired
            @Qualifier("authenticationManagerBean")
            private AuthenticationManager authenticationManager;

            @Autowired
            private RedisConnectionFactory redisConnectionFactory;

            @Override
            public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
                            String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode(Constants.CLIENT_SECRET);
                             //配置客户端，使用密码模式验证鉴权
                             clients.inMemory()
                                    .withClient(Constants.CLIENT_ID)
                                    //密码模式及refresh_token模式
                                    .authorizedGrantTypes(Constants.GRANT_TYPE[0], Constants.GRANT_TYPE[1])
                                     .scopes("all")
                                     .secret(finalSecret);
            }

            @Bean
            public RedisTokenStore redisTokenStore() {
                            return new RedisTokenStore(redisConnectionFactory);
            }

            @Override
            public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
                endpoints.tokenStore(redisTokenStore()).authenticationManager(authenticationManager);
                DefaultTokenServices tokenService = new DefaultTokenServices();
                tokenService.setTokenStore(endpoints.getTokenStore());
                tokenService.setSupportRefreshToken(true);
                tokenService.setClientDetailsService(endpoints.getClientDetailsService());
                tokenService.setTokenEnhancer(endpoints.getTokenEnhancer());
                tokenService.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1));
                tokenService.setRefreshTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1));
                tokenService.setReuseRefreshToken(false);
                endpoints.tokenServices(tokenService);
            }

            @Override
            public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
                oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
            }

        }

    }
}
