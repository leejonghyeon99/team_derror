package com.example.demo.service.board;

import com.example.demo.domain.board.Attachment;
import com.example.demo.repository.post.AttachmentRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(SqlSession sqlSession){
        attachmentRepository = sqlSession.getMapper(AttachmentRepository.class);
    }

    @Override
    public Attachment findById(Long id) {
        return attachmentRepository.findById(id);
    }


    @Override
    public Attachment findOne(Long id) {
        return attachmentRepository.findFileOne(id);
    }

}
