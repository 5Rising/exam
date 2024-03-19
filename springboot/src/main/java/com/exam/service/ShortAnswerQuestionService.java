package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.ShortAnswerQuestion;

import java.util.List;

public interface ShortAnswerQuestionService {
    List<ShortAnswerQuestion> findByIdAndType(Integer paperId);

    IPage<ShortAnswerQuestion> findAll(Page<ShortAnswerQuestion> page);

    ShortAnswerQuestion findOnlyQuestionId();

    int add(ShortAnswerQuestion shortAnswerQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo);

    ShortAnswerQuestion findByQuestionId(Integer questionId);
}

