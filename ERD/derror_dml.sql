INSERT INTO authority (id,name) VALUES (1,'USER'), (2,'ADMIN');
select * from authority;
select * from member;


Insert into member (auth_id,username,password,name,age,phone, email)
values ('1','testid', 'testpw','testname',23,'010-1234-1234','testemail@email.com');

select * from post;

insert into post (member_id, category, title, content)
values (2,'testcategory','testtitle','testcontent');




