SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS attach;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS share_plan;
DROP TABLE IF EXISTS event_plan;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS authority;




/* Create Tables */

CREATE TABLE attach
(
    id bigint NOT NULL AUTO_INCREMENT,
    post_id bigint NOT NULL,
    sourcename varchar(200) NOT NULL,
    filename varchar(200) NOT NULL,
    type varbinary(50) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE authority
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL ,
    PRIMARY KEY (id),
    UNIQUE (name)
);



CREATE TABLE comment
(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    post_id bigint NOT NULL,
    comment_id bigint,
    content text NOT NULL,
    created_date datetime default now(),
    PRIMARY KEY (id)
);


CREATE TABLE event_plan
(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    master_id bigint NOT NULL,
    title varchar(50) NOT NULL,
    created_date datetime default now(),
    national varchar(50) NOT NULL,
    IATA varchar(3),
    PRIMARY KEY (id),
    UNIQUE (master_id)
);


CREATE TABLE friends
(
    id bigint NOT NULL AUTO_INCREMENT,
    from_member bigint NOT NULL,
    to_member bigint NOT NULL,
    status char NOT NULL default 0,
    PRIMARY KEY (id)
);



-- 이건 회원정보입니다.
CREATE TABLE member
(
    id bigint NOT NULL AUTO_INCREMENT,
    auth_id int NOT NULL,
    provider varchar(20),
    provider_id varchar(200),
    username varchar(100) NOT NULL,
    password varchar(200) NOT NULL,
    name varchar(20) NOT NULL,
    age int NOT NULL,
    phone varchar(16) NOT NULL,
    email varchar(80),
    thumnail_img varchar(300),
    created_date datetime default now(),
    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (email)
) COMMENT = '이건 회원정보입니다.';


CREATE TABLE post
(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    category varchar(30) NOT NULL,
    title varchar(80) NOT NULL,
    content longtext NOT NULL,
    created_date datetime NOT NULL default now(),
    view_cnt int default 0,
    PRIMARY KEY (id)
);


CREATE TABLE share_plan
(
    id bigint NOT NULL AUTO_INCREMENT,
    event_id bigint NOT NULL,
    invitee bigint NOT NULL,
    created_date datetime default now(),
    PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE member
    ADD FOREIGN KEY (auth_id)
        REFERENCES authority (id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE comment
    ADD FOREIGN KEY (comment_id)
        REFERENCES comment (id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE share_plan
    ADD FOREIGN KEY (event_id)
        REFERENCES event_plan (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE comment
    ADD FOREIGN KEY (member_id)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE event_plan
    ADD FOREIGN KEY (member_id)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE friends
    ADD FOREIGN KEY (to_member)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE friends
    ADD FOREIGN KEY (from_member)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE post
    ADD FOREIGN KEY (member_id)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE share_plan
    ADD FOREIGN KEY (invitee)
        REFERENCES member (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE attach
    ADD FOREIGN KEY (post_id)
        REFERENCES post (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;


ALTER TABLE comment
    ADD FOREIGN KEY (post_id)
        REFERENCES post (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;



