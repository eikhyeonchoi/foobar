insert into syscode (code, value, parent_sys) values ('root', '루트', null);

insert into syscode (code, value, parent_sys) values ('user_role', '유저역할', 'root');
insert into syscode (code, value, parent_sys) values ('user_role_common', '일반', 'user_role');
insert into syscode (code, value, parent_sys) values ('user_role_admin', '괸리자', 'user_role');

insert into syscode (code, value, parent_sys) values ('banner_position', '배너위치', 'root');
insert into syscode (code, value, parent_sys) values ('banner_position_main_header', '메인헤더', 'banner_position');
insert into syscode (code, value, parent_sys) values ('banner_position_main_side', '메인사이드바', 'banner_position');
insert into syscode (code, value, parent_sys) values ('banner_position_main_footer', '메인푸터', 'banner_position');

insert into syscode (code, value, parent_sys) values ('category', '카테고리', 'root');

insert into member (role_sys, email, nickname, pwd) values ('user_role_common', 'eikhyeon8542@gmail.com', '익콩이', '$2a$10$nO0UuwJx0zl9UlO/85TDh.ZC44lMACnykzRfY.1kNXD9q1UL2Sw1.');