package com.example.demo.repository;

import com.example.demo.domain.board.Post;

import java.util.List;
/**
 @Author 장고운
 */
public interface PostRepository {

    // 새 글 작성 (INSERT) <- Post
    int save(Post post); //save메소드 마이바티스가 xml파일과 맵핑시켜줌
    // 메소드 이름이 unique 여야함. 동일이름이 있으면 xml에서 불러오기 힘듬

    // 특정 id 글 내용 읽기 (SELECT)
    // 만약 해당 id 의 글 없으면 null 리턴함
    Post findById(Long id);

    List<Post> findByUserName(String username);

    // 특정 id 글 조회수 +1 증가 (UPDATE)
    int viewCnt(Long id);

    // 전체 글 목록 : 최신순 (SELECT)
    List<Post> findAll();

    // 특정 id 글 수정 (제목, 내용) (UPDATE)
    int update(Post post);

    // 특정 id 글 삭제하기 (DELETE)
    int delete(Post post);

    // 페이징
    // from 부터 rows 개 만큼 SELECT
    List<Post> selectFromRow(int from, int rows, String category);

    // 최신순 조회
    List<Post> selectFromRowByViewCnt(int from, int rows, String category);

    // 전체 글의 개수
    int countAll();

    // 키워드 검색 수
    int countAllKeyword(String keyword);

    // 검색 리스트 불러오기
    List<Post> findByList(String keyword,int from, int rows);

}
