INSERT INTO authority (id,name) VALUES (1,'ROLE_USER'), (2,'ROLE_ADMIN');
INSERT INTO event_plan
select * from authority;
select * from member;
select * from post;
select * from event_plan;


alter table member add column thumbnail_img varchar(200) default 'default_thumbnail.png';
alter table post add column thumbnail varchar(200) default 'default.jpg';

select *
from post
where post.member_id = (select id from member where username = 'USER1')
;

update authority set name = 'ROLE_USER' where id = 1;
update authority set name = 'ROLE_ADMIN' where id = 2;
update post set category='notice';
Insert into member (auth_id,username,password,name,age,phone, email)
values ('1','testid', 'testpw','testname',23,'010-1234-1234','testemail@email.com');

select * from post;
select * from comment;
insert into post (member_id, category, title, content)
values (1,'testcategory','testtitle','testcontent');

INSERT INTO comment (member_id, post_id, comment_id, content)
VALUES (4, 1, 4, '이거봐라');

select * from member;
select * from post;
select * from post where post.member_id in (3,4,5);
SELECT count(distinct p.id) FROM member m join post p on m.auth_id = 2 and p.title like concat('%','권','%');


drop table calendar;
CREATE TABLE calendar(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       start_date DATE NOT NULL,
                       end_date DATE,
                       color VARCHAR(20),
                       all_day BOOLEAN DEFAULT TRUE,
                       memo TEXT,
                       member_id BIGINT NOT NULL ,
                       FOREIGN KEY (member_id) REFERENCES Member(id)
);

select * from calendar;
select * from event_plan;
