package com.example.demo.service.board;

import com.example.demo.domain.board.Attachment;

import java.util.List;

public interface AttachmentService {

    Attachment findById(Long id);

    Attachment findOne(Long id);
}

