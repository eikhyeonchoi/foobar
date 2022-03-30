drop table if exists banner CASCADE;
drop table if exists block CASCADE;
drop table if exists board CASCADE;
drop table if exists category CASCADE;
drop table if exists category_board CASCADE;
drop table if exists comment CASCADE;
drop table if exists file CASCADE;
drop table if exists hashtag CASCADE;
drop table if exists kick CASCADE;
drop table if exists member CASCADE;
drop table if exists news CASCADE;
drop table if exists recommend CASCADE;
drop table if exists scrap CASCADE;
drop table if exists syscode CASCADE;


create table banner (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    position_sys varchar(255) not null,
    name varchar(255) not null,
    start_dt timestamp not null,
    end_dt timestamp not null,
    ord integer default 1 not null,
    use_fl boolean default true not null,
    create_dt timestamp,
    update_dt timestamp
);

create table block (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    from_member_id integer not null,
    to_member_id integer not null,
    create_dt timestamp,
    update_dt timestamp
);

create table board (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id integer not null,
    title varchar(255) not null,
    text_content LONGTEXT not null,
    html_content LONGTEXT not null,
    fix_fl boolean default false not null,
    open_fl boolean default true not null,
    view_cnt integer default 0 not null,
    create_dt timestamp,
    update_dt timestamp
);

create table category (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type_sys varchar(255) not null,
    name varchar(255) not null,
    ord integer default 1 not null,
    use_fl boolean default true not null,
    create_dt timestamp,
    update_dt timestamp
);

create table category_board (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    board_id integer not null,
    category_id integer not null
);

create table comment (
     id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
     parent_id integer,
     member_id integer not null,
     tag_member_id integer,
     board_id integer not null,
     content TEXT not null,
     delete_fl boolean default false not null,
     create_dt timestamp,
     update_dt timestamp
);

create table file (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    context varchar(500),
    target_id integer,
    target_tb varchar(255),
    create_dt timestamp,
    update_dt timestamp
);

create table hashtag (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    value varchar(255) not null
);

create table kick (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id integer not null,
    start_dt timestamp not null,
    end_dt timestamp not null,
    create_dt timestamp,
    update_dt timestamp
);

create table member (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_sys varchar(255) not null,
    email varchar(255) not null,
    nickname varchar(255) not null,
    pwd varchar(255) not null,
    refresh_token varchar(255),
    create_dt timestamp,
    update_dt timestamp
);

create table news (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id integer not null,
    message varchar(255) not null,
    source varchar(500) not null,
    create_dt timestamp,
    update_dt timestamp
);

create table recommend (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id integer not null,
    target_id integer not null,
    target_tb varchar(255) not null,
    create_dt timestamp,
    update_dt timestamp
);

create table scrap (
    id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    board_id integer,
    member_id integer,
    create_dt timestamp,
    update_dt timestamp
);

create table syscode (
    code varchar(255) not null,
    parent_sys VARCHAR(100),
    value varchar(255) not null,
    create_dt timestamp,
    update_dt timestamp
);


alter table banner add constraint FK_banner_position_sys_to_syscode_code foreign key (position_sys) references syscode;
alter table block add constraint FK_block_from_member_id_to_member_id foreign key (from_member_id) references member;
alter table block add constraint FK_block_to_member_id_to_member_id foreign key (to_member_id) references member;
alter table board add constraint FK_board_member_id_to_member_id foreign key (member_id) references member;
alter table category add constraint FK_category_type_sys_to_syscode_code foreign key (type_sys) references syscode;
alter table category_board add constraint FK_category_board_board_id_to_board_id foreign key (board_id) references board;
alter table category_board add constraint FK_category_board_category_id_to_category_id foreign key (category_id) references category;
alter table comment add constraint FK_comment_board_id_to_board_id foreign key (board_id) references board;
alter table comment add constraint FK_comment_member_id_to_member_id foreign key (member_id) references member;
alter table comment add constraint FK_comment_parent_id_to_comment_id foreign key (parent_id) references comment;
alter table comment add constraint FK_comment_tag_member_id_to_member_id foreign key (tag_member_id) references member;
alter table kick add constraint FK_kick_member_id_to_member_id foreign key (member_id) references member;
alter table member add constraint UK_member__email unique (email);
alter table member add constraint UK_member__nickname unique (nickname);
alter table member add constraint FK_member_role_sys_to_syscode_code foreign key (role_sys) references syscode;
alter table news add constraint FK_news_member_id_to_member_Id foreign key (member_id) references member;
alter table recommend add constraint FK_recommend_member_id_to_member_id foreign key (member_id) references member;
alter table scrap add constraint FK_scrap_board_id_to_board_id foreign key (board_id) references board;
alter table scrap add constraint FK_scrap_member_id_to_member_id foreign key (member_id) references member;
alter table syscode add constraint FK_syscode_parent_sys_to_syscode_code free roreign key (parent_sys) references syscode;