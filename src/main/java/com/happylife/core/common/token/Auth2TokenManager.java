package com.happylife.core.common.token;

import com.happylife.core.common.Constants;
import com.happylife.core.dto.token.Token;
import com.happylife.core.exception.TokenException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component("auth2TokenManager")
public class Auth2TokenManager extends AbstractTokenManger{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Token createToken(String name, String password) throws TokenException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String credentials = Constants.CLIENT_ID + ":" + Constants.CLIENT_SECRET;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        headers.add(Constants.HEADER_KEY_AUTHORIZATION, Constants.PREFIX_AUTHORIZATION + encodedCredentials);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(Constants.GRANT_TYPE_KEY, Constants.GRANT_TYPE[0]);
        map.add(Constants.USERNAME, name);
        map.add(Constants.PASSWORD, password);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Token> response = null;
        try{
            response = restTemplate.exchange(Constants.AUTH2_TOKEN_URL, HttpMethod.POST,entity,Token.class);
        }catch (HttpClientErrorException ex){
            throw new TokenException(ex.getMessage(), ex);
        }catch (RestClientException ex){
            throw new TokenException(ex.getMessage(), ex);
        }catch (Exception ex){
            throw new TokenException(ex.getMessage(), ex);
        }
        return response.getBody();
    }

    @Override
    public Token refreshToken(Token token) throws TokenException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String credentials = Constants.CLIENT_ID + ":" + Constants.CLIENT_SECRET;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        headers.add(Constants.HEADER_KEY_AUTHORIZATION, Constants.PREFIX_AUTHORIZATION + encodedCredentials);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(Constants.GRANT_TYPE_KEY, Constants.GRANT_TYPE[1]);
        map.add(Constants.REFRESH_TOKEN, token.getRefresh_token());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Token> response = null;
        try{
            response = restTemplate.exchange(Constants.AUTH2_TOKEN_URL, HttpMethod.POST,entity,Token.class);
        }catch (HttpClientErrorException ex){
            throw new TokenException(ex.getMessage(), ex);
        }catch (RestClientException ex){
            throw new TokenException(ex.getMessage(), ex);
        }catch (Exception ex){
            throw new TokenException(ex.getMessage(), ex);
        }
        return response.getBody();
    }
}
