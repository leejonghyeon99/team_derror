INSERT INTO authority (id,name) VALUES (1,'ROLE_USER'), (2,'ROLE_ADMIN');
select * from authority;
select * from member;

update authority set name = 'ROLE_USER' where id = 1;
update authority set name = 'ROLE_ADMIN' where id = 2;

Insert into member (auth_id,username,password,name,age,phone, email)
values ('1','testid', 'testpw','testname',23,'010-1234-1234','testemail@email.com');

select * from post;
select * from comment;
insert into post (member_id, category, title, content)
values (2,'testcategory','testtitle','testcontent');

INSERT INTO comment (member_id, post_id, comment_id, content)
VALUES (4, 1, 4, '이거봐라');

select * from member;
select * from post;
select * from post where post.member_id in (3,4,5);
SELECT count(distinct p.id) FROM member m join post p on m.auth_id = 2 and p.title like concat('%','권','%');

