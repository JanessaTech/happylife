insert into public.saler(saler_id, name, password, email, phone, create_date, update_date) values('8cb5dc7e-dbb6-49b3-8014-eda25c2665a6', 'saler1', '111111', 'saler1@qq.com', 123456, '2020-12-11 03:47:14.150694-05', '2020-12-11 03:47:14.150694-05');
insert into public.saler(saler_id, name, password, email, phone, create_date, update_date) values('39f9662e-d673-4763-8353-0950fd8aad38', 'saler2', '222222', 'saler2@qq.com', 123456, '2020-12-11 03:53:52.860154-05', '2020-12-11 03:53:52.860154-05');
insert into public.saler(saler_id, name, password, email, phone, create_date, update_date) values('d6648f20-210a-4f45-a291-2ccd1d358ee9', 'saler3', '333333', 'saler3@qq.com', 123456, '2020-12-11 03:54:17.634263-05', '2020-12-11 03:54:17.634263-05');
insert into public.saler(saler_id, name, password, email, phone, create_date, update_date) values('afd8be07-6bae-49cb-97cc-f46d7e5bab90', 'saler4', '444444', 'saler4@qq.com', 123456, '2020-12-11 03:55:13.562597-05', '2020-12-11 03:55:13.562597-05');
insert into public.saler(saler_id, name, password, email, phone, create_date, update_date) values('21a88318-b1f1-4209-bd06-864eb75b3e22', 'saler5', '555555', 'saler5@qq.com', 123456, '2020-12-11 03:55:42.092786-05', '2020-12-11 03:55:42.092786-05');

insert into public.shop(shop_id, shop_name, saler_id, status, create_date, update_date) values('b1216cc5-93b3-4c4f-8838-66e7546aa64b', 'shop1','8cb5dc7e-dbb6-49b3-8014-eda25c2665a6', 'on', '2020-12-11 04:01:49.655198-05', '2020-12-11 04:01:49.655198-05');
insert into public.shop(shop_id, shop_name, saler_id, status, create_date, update_date) values('29b7dc94-d56c-4398-b1c2-f74ccc551eb1', 'shop2','39f9662e-d673-4763-8353-0950fd8aad38', 'on', '2020-12-11 04:05:37.499463-05', '2020-12-11 04:05:37.499463-05');
insert into public.shop(shop_id, shop_name, saler_id, status, create_date, update_date) values('4fd60742-89d7-4bb8-84b7-62efc12a6af6', 'shop3','d6648f20-210a-4f45-a291-2ccd1d358ee9', 'on', '2020-12-11 04:05:58.196567-05', '2020-12-11 04:05:58.196567-05');
insert into public.shop(shop_id, shop_name, saler_id, status, create_date, update_date) values('4087ca2e-4666-43df-8eda-abd35dac5bba', 'shop4','afd8be07-6bae-49cb-97cc-f46d7e5bab90', 'on', '2020-12-11 04:06:13.673181-05', '2020-12-11 04:06:13.673181-05');
insert into public.shop(shop_id, shop_name, saler_id, status, create_date, update_date) values('91a3ac17-51b4-42a9-8d51-7a0e806a6d51', 'shop5','21a88318-b1f1-4209-bd06-864eb75b3e22', 'on', '2020-12-11 04:06:42.435812-05', '2020-12-11 04:06:42.435812-05');

insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('2ae2382f-b1ee-41f4-a70d-6465d56799d1', 'item11', 'b1216cc5-93b3-4c4f-8838-66e7546aa64b', 113.45, 'this is item11 in shop1', '2020-12-11 04:13:00.902062-05', '2020-12-11 04:13:00.902062-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('fd551639-72e1-4c12-a75f-5bfe60ba0cfb', 'item12', 'b1216cc5-93b3-4c4f-8838-66e7546aa64b', 213.45, 'this is item12 in shop1', '2020-12-11 04:18:14.733894-05', '2020-12-11 04:18:14.733894-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('57b48d9c-20b6-4d9a-bf35-6ae23dc8b9b9', 'item13', 'b1216cc5-93b3-4c4f-8838-66e7546aa64b', 313.45, 'this is item13 in shop1', '2020-12-11 04:18:32.597867-05', '2020-12-11 04:18:32.597867-05');

insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('7061b01f-38db-46f1-9c67-0fe4b5810c50', 'item21', '29b7dc94-d56c-4398-b1c2-f74ccc551eb1', 123.45, 'this is item21 in shop2', '2020-12-11 04:13:00.902062-05', '2020-12-11 04:13:00.902062-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('864cc71e-6ec0-4bd7-a7b9-840a0b7482a6', 'item22', '29b7dc94-d56c-4398-b1c2-f74ccc551eb1', 223.45, 'this is item22 in shop2', '2020-12-11 04:18:14.733894-05', '2020-12-11 04:18:14.733894-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('c97581bd-2158-45bf-a1b5-eb1fe6e712b8', 'item23', '29b7dc94-d56c-4398-b1c2-f74ccc551eb1', 323.45, 'this is item23 in shop2', '2020-12-11 04:18:32.597867-05', '2020-12-11 04:18:32.597867-05');

insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('d3fa59d6-837e-4d12-8ea1-7cb5a9e35571', 'item31', '4fd60742-89d7-4bb8-84b7-62efc12a6af6', 133.45, 'this is item31 in shop3', '2020-12-11 04:13:00.902062-05', '2020-12-11 04:13:00.902062-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('068012a7-a1b3-4fdf-9b6f-d076dcd583ac', 'item32', '4fd60742-89d7-4bb8-84b7-62efc12a6af6', 233.45, 'this is item32 in shop3', '2020-12-11 04:18:14.733894-05', '2020-12-11 04:18:14.733894-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('a5ec616c-775d-4cd2-98e2-1c5700a8279d', 'item33', '4fd60742-89d7-4bb8-84b7-62efc12a6af6', 333.45, 'this is item33 in shop3', '2020-12-11 04:18:32.597867-05', '2020-12-11 04:18:32.597867-05');

insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('2919f2e9-cca7-4f73-a1b0-e26c7978954d', 'item41', '4087ca2e-4666-43df-8eda-abd35dac5bba', 143.45, 'this is item41 in shop4', '2020-12-11 04:13:00.902062-05', '2020-12-11 04:13:00.902062-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('17289293-668e-413c-b7cf-64d4d780d629', 'item42', '4087ca2e-4666-43df-8eda-abd35dac5bba', 243.45, 'this is item42 in shop4', '2020-12-11 04:18:14.733894-05', '2020-12-11 04:18:14.733894-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('637afed0-ae1d-4119-8f3c-316f70431ca7', 'item43', '4087ca2e-4666-43df-8eda-abd35dac5bba', 343.45, 'this is item43 in shop4', '2020-12-11 04:18:32.597867-05', '2020-12-11 04:18:32.597867-05');

insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('33841b6c-a8e5-42e2-afeb-b4de26720a6c', 'item51', '91a3ac17-51b4-42a9-8d51-7a0e806a6d51', 153.45, 'this is item51 in shop5', '2020-12-11 04:13:00.902062-05', '2020-12-11 04:13:00.902062-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('a2adc16c-8998-407f-b5a3-2a20935b9a4e', 'item52', '91a3ac17-51b4-42a9-8d51-7a0e806a6d51', 253.45, 'this is item52 in shop5', '2020-12-11 04:18:14.733894-05', '2020-12-11 04:18:14.733894-05');
insert into public.item(item_id, title, shop_id, price, details, create_date, update_date) values('c81d005b-fd55-45ea-b698-58f8d236f111', 'item53', '91a3ac17-51b4-42a9-8d51-7a0e806a6d51', 353.45, 'this is item53 in shop5', '2020-12-11 04:18:32.597867-05', '2020-12-11 04:18:32.597867-05');

insert into public."user"(user_id, name, password, sex, addr, profile, create_date, update_date) values('28255bce-512a-4ffa-a1c1-3847e928e11f', 'user1', '11111', 'female', 'xian', 'www.baidu/user1.jpg', '2020-12-11 04:45:17.090819-05', '2020-12-11 04:45:17.090819-05');
insert into public."user"(user_id, name, password, sex, addr, profile, create_date, update_date) values('6779b313-af72-4a70-b40d-accb45ccad62', 'user2', '22222', 'female', 'beijing', 'www.baidu/user2.jpg', '2020-12-11 04:45:17.090819-05', '2020-12-11 04:45:18.090819-05');
insert into public."user"(user_id, name, password, sex, addr, profile, create_date, update_date) values('9810cb68-1b80-40cc-9ce4-75e89856e797', 'user3', '33333', 'female', 'nanjing', 'www.baidu/user3.jpg', '2020-12-11 04:45:17.090819-05', '2020-12-11 04:45:19.090819-05');
insert into public."user"(user_id, name, password, sex, addr, profile, create_date, update_date) values('8936061f-4fcc-45f1-b73a-5484e3b6a064', 'user4', '44444', 'female', 'shanghai', 'www.baidu/user4.jpg', '2020-12-11 04:45:17.090819-05', '2020-12-11 04:45:20.090819-05');

insert into public.addr(addr_id, user_id, addr_info, receiver, phone, create_date, update_date) values('d8ab4e94-c908-48ed-a7ee-8408db269b2e', '28255bce-512a-4ffa-a1c1-3847e928e11f', 'addr1 for user1', 'user1', 15319401521, '2020-12-11 05:03:18.461015-05', '2020-12-11 05:03:18.461015-05');
insert into public.addr(addr_id, user_id, addr_info, receiver, phone, create_date, update_date) values('b45ce530-5265-40b6-b840-b2119693dac0', '28255bce-512a-4ffa-a1c1-3847e928e11f', 'addr2 for user1', 'user1', 15319401521, '2020-12-11 05:03:18.461015-05', '2020-12-11 05:03:18.461015-05');
insert into public.addr(addr_id, user_id, addr_info, receiver, phone, create_date, update_date) values('f87d8a9b-656f-45a2-9dac-1339ac52e8ce', '6779b313-af72-4a70-b40d-accb45ccad62', 'addr1 for user2', 'user2', 15319401521, '2020-12-11 05:03:18.461015-05', '2020-12-11 05:03:18.461015-05');
insert into public.addr(addr_id, user_id, addr_info, receiver, phone, create_date, update_date) values('027631c6-ff68-499f-9c94-c4b41f874985', '6779b313-af72-4a70-b40d-accb45ccad62', 'addr2 for user2', 'user2', 15319401521, '2020-12-11 05:03:18.461015-05', '2020-12-11 05:03:18.461015-05');
insert into public.addr(addr_id, user_id, addr_info, receiver, phone, create_date, update_date) values('899d5fef-54b7-4534-85ac-1dd5470795bb', '9810cb68-1b80-40cc-9ce4-75e89856e797', 'addr1 for user3', 'user3', 15319401521, '2020-12-11 05:03:18.461015-05', '2020-12-11 05:03:18.461015-05');
insert into public.addr(addr_id, user_id, addr_info, receiver, phone, create_date, update_date) values('ec5d931d-2a57-40e6-a5c3-c318d09238b6', '9810cb68-1b80-40cc-9ce4-75e89856e797', 'addr2 for user3', 'user3', 15319401521, '2020-12-11 05:03:18.461015-05', '2020-12-11 05:03:18.461015-05');
insert into public.addr(addr_id, user_id, addr_info, receiver, phone, create_date, update_date) values('50265b38-700e-418d-8004-9c1f785fc101', '8936061f-4fcc-45f1-b73a-5484e3b6a064', 'addr1 for user4', 'user4', 15319401521, '2020-12-11 05:03:18.461015-05', '2020-12-11 05:03:18.461015-05');
insert into public.addr(addr_id, user_id, addr_info, receiver, phone, create_date, update_date) values('07a34992-ddf7-4c81-b901-c3333a83b2b5', '8936061f-4fcc-45f1-b73a-5484e3b6a064', 'addr2 for user4', 'user4', 15319401521, '2020-12-11 05:03:18.461015-05', '2020-12-11 05:03:18.461015-05');

insert into public.shopping_cart(shopping_id, item_ids, user_id) values('886c68ea-a625-4aec-88cb-08dc4c63fbf9', '{2ae2382f-b1ee-41f4-a70d-6465d56799d1, fd551639-72e1-4c12-a75f-5bfe60ba0cfb}', '28255bce-512a-4ffa-a1c1-3847e928e11f');
insert into public.shopping_cart(shopping_id, item_ids, user_id) values('52d5966f-2274-438d-b324-5d3173482962', '{864cc71e-6ec0-4bd7-a7b9-840a0b7482a6}', '6779b313-af72-4a70-b40d-accb45ccad62');
insert into public.shopping_cart(shopping_id, item_ids, user_id) values('49e62d87-feef-40d4-9339-926f50ae0b60', '{068012a7-a1b3-4fdf-9b6f-d076dcd583ac, a5ec616c-775d-4cd2-98e2-1c5700a8279d}', '9810cb68-1b80-40cc-9ce4-75e89856e797');
insert into public.shopping_cart(shopping_id, item_ids, user_id) values('d5e0e849-26c6-48f0-8c63-baf2b44259c2', '{33841b6c-a8e5-42e2-afeb-b4de26720a6c, a2adc16c-8998-407f-b5a3-2a20935b9a4e, c81d005b-fd55-45ea-b698-58f8d236f111}', '8936061f-4fcc-45f1-b73a-5484e3b6a064');

insert into public."order"(order_id, user_id, shop_id, item_ids, total_price, status, create_date, update_date) values('56944dd2-3040-473d-aa1f-29e1ad05c58e', '28255bce-512a-4ffa-a1c1-3847e928e11f', 'b1216cc5-93b3-4c4f-8838-66e7546aa64b', '{2ae2382f-b1ee-41f4-a70d-6465d56799d1, fd551639-72e1-4c12-a75f-5bfe60ba0cfb}', 567.3, 'awaiting', '2020-12-11 05:32:08.47093-05', '2020-12-11 05:32:08.47093-05');
insert into public."order"(order_id, user_id, shop_id, item_ids, total_price, status, create_date, update_date) values('8681f477-0bd3-48d0-8cfe-4e1716bd6229', '6779b313-af72-4a70-b40d-accb45ccad62', '29b7dc94-d56c-4398-b1c2-f74ccc551eb1', '{864cc71e-6ec0-4bd7-a7b9-840a0b7482a6}', 53.1, 'awaiting', '2020-12-11 05:32:08.47093-05', '2020-12-11 05:32:08.47093-05');