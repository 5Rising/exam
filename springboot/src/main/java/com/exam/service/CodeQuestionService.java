package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.CodeQuestion;

import java.util.List;

public interface CodeQuestionService {
    List<CodeQuestion> findByIdAndType(Integer paperId);

    IPage<CodeQuestion> findAll(Page<CodeQuestion> page);

    CodeQuestion findOnlyQuestionId();

    int add(CodeQuestion codeQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo);

    CodeQuestion findByQuestionId(Integer questionId);
}

