
INSERT INTO authority (id,name) VALUES (1,'ROLE_USER'), (2,'ROLE_ADMIN');


INSERT INTO member (auth_id, provider, provider_id, username, password, name, age, phone, email) VALUES
                                                                                                     ('1', ' ',' ','USER1', '$2a$10$rAfJ26cXbfHkLUivwtSxxefOXUDZ4o5LQ3o4OnxiOH/vC/CeUbp0e', '회원1', '20', '010-1234-5678', 'user1@mail.com'),
                                                                                                     ('1', ' ',' ','USER2', '$2a$10$rAfJ26cXbfHkLUivwtSxxefOXUDZ4o5LQ3o4OnxiOH/vC/CeUbp0e', '회원2', '25', '010-1111-3333', 'user2@mail.com'),
                                                                                                     ('2', ' ',' ','ADMIN', '$2a$10$rAfJ26cXbfHkLUivwtSxxefOXUDZ4o5LQ3o4OnxiOH/vC/CeUbp0e', '관리자', '30', '010-2222-4444', 'admin1@mail.com')
;

-- 샘플 글

INSERT INTO post (member_id, category, title, content, thumbnail) VALUES
                                                                      (1,'free', '1번째 자유게시판', '내용입니다1','대만.jpeg'),
                                                                      (1,'free', '2번째 자유게시판', '내용입니다1', '디즈니랜드.jpeg'),
                                                                      (2,'free', '3번째 자유게시판', '내용입니다1', '런던브릿지.jpeg'),
                                                                      (2,'free', '4번째 자유게시판', '내용입니다1', '런던시계탑.jpeg'),
                                                                      (3,'free', '5번째 자유게시판', '내용입니다1', '로마.jpeg'),
                                                                      (3,'free', '6번째 자유게시판', '내용입니다1', '루브르박물관.jpeg'),
                                                                      (1,'share', '1번째 공유게시판', '내용입니다1','미국그랜드케니언.jpeg'),
                                                                      (1,'share', '2번째 공유게시판', '내용입니다1', '미국자유의여신상.jpeg'),
                                                                      (2,'share', '3번째 공유게시판', '내용입니다1', '베니스.jpeg'),
                                                                      (2,'share', '4번째 공유게시판', '내용입니다1', '브라질.jpeg'),
                                                                      (3,'share', '5번째 공유게시판', '내용입니다1', '산토리니.jpeg'),
                                                                      (3,'share', '6번째 공유게시판', '내용입니다1', '스페인 바르셀로나.jpeg'),
                                                                      (3,'notice', '1번째 공지사항', '내용입니다1', '에펠탑.jpeg'),
                                                                      (3,'notice', '2번째 공지사항', '내용입니다1', '오페라극장.jpeg'),
                                                                      (3,'notice', '3번째 공지사항', '내용입니다1','default.jpg'),
                                                                      (3,'notice', '4번째 공지사항', '내용입니다1','default.jpg'),
                                                                      (3,'notice', '5번째 공지사항', '내용입니다1',  'default.jpg'),
                                                                      (3,'notice', '6번째 공지사항', '내용입니다1', 'default.jpg')
;

-- 샘플 첨부파일
INSERT INTO attach(post_id, sourcename, filename) VALUES
                                                      (1, '대만.jpeg', '대만.jpeg'),
                                                      (2, '디즈니랜드.jpeg', '디즈니랜드.jpeg'),
                                                      (3, '런던브릿지.jpeg', '런던브릿지.jpeg'),
                                                      (4, '런던시계탑.jpeg', '런던시계탑.jpeg'),
                                                      (5, '로마.jpeg', '로마.jpeg'),
                                                      (6, '루브르박물관.jpeg', '루브르박물관.jpeg'),
                                                      (7, '미국그랜드케니언.jpeg', '미국그랜드케니언.jpeg'),
                                                      (8, '미국자유의여신상.jpeg', '미국자유의여신상.jpeg'),
                                                      (9, '베니스.jpeg', '베니스.jpeg'),
                                                      (10, '브라질.jpeg', '브라질.jpeg'),
                                                      (11, '산토리니.jpeg', '산토리니.jpeg'),
                                                      (12, '스페인 바르셀로나.jpeg', '스페인 바르셀로나.jpeg'),
                                                      (13, '에펠탑.jpeg', '에펠탑.jpeg'),
                                                      (14, '오페라극장.jpeg', '오페라극장.jpeg')
;
select * from post p join comment c on p.id = c.post_id;

-- 샘플 댓글
INSERT INTO comment(member_id, post_id, content) VALUES
                                                     (1, 1,'잘보고 갑니다'),
                                                     (1, 2,'잘보고 갑니다'),
                                                     (1, 3,'잘보고 갑니다'),
                                                     (1, 4,'잘보고 갑니다'),
                                                     (1, 5,'잘보고 갑니다'),
                                                     (1, 6,'잘보고 갑니다'),
                                                     (2, 1,'하이하이'),
                                                     (2, 2,'하이하이'),
                                                     (2, 3,'하이하이'),
                                                     (2, 4,'하이하이'),
                                                     (2, 5,'하이하이'),
                                                     (2, 6,'하이하이'),
                                                     (3, 1,'좋은 정보 땡큐~'),
                                                     (3, 2,'좋은 정보 땡큐~'),
                                                     (3, 3,'좋은 정보 땡큐~'),
                                                     (3, 4,'좋은 정보 땡큐~'),
                                                     (3, 5,'좋은 정보 땡큐~'),
                                                     (3, 6,'좋은 정보 땡큐~')
;


select * from comment;

-- 페이징 테스트용 다량의 데이터
INSERT INTO post(member_id, category, title, content)
SELECT member_id, category, title, content FROM post;

SELECT COUNT(*) FROM post;









