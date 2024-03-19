package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.CodeQuestion;
import com.exam.mapper.CodeQuestionMapper;
import com.exam.service.CodeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CodeQuestionServiceImpl implements CodeQuestionService {
    @Autowired
    private CodeQuestionMapper codeQuestionMapper;

    @Override
    public List<CodeQuestion> findByIdAndType(Integer paperId) {
        return codeQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public IPage<CodeQuestion> findAll(Page<CodeQuestion> page) {
        return codeQuestionMapper.findAll(page);
    }

    @Override
    public CodeQuestion findOnlyQuestionId() {
        return codeQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(CodeQuestion codeQuestion) {
        return codeQuestionMapper.add(codeQuestion);
    }

    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return codeQuestionMapper.findBySubject(subject,pageNo);
    }

    @Override
    public CodeQuestion findByQuestionId(Integer questionId) {
        return codeQuestionMapper.findByQuestionId(questionId);
    }
}
