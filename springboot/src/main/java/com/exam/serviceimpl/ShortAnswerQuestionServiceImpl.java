package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ShortAnswerQuestion;
import com.exam.mapper.ShortAnswerQuestionMapper;
import com.exam.service.ShortAnswerQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShortAnswerQuestionServiceImpl implements ShortAnswerQuestionService {
    @Autowired
    private ShortAnswerQuestionMapper shortAnswerQuestionMapper;

    @Override
    public List<ShortAnswerQuestion> findByIdAndType(Integer paperId) {
        return shortAnswerQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public IPage<ShortAnswerQuestion> findAll(Page<ShortAnswerQuestion> page) {
        return shortAnswerQuestionMapper.findAll(page);
    }

    @Override
    public ShortAnswerQuestion findOnlyQuestionId() {
        return shortAnswerQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(ShortAnswerQuestion shortAnswerQuestion) {
        return shortAnswerQuestionMapper.add(shortAnswerQuestion);
    }

    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return shortAnswerQuestionMapper.findBySubject(subject,pageNo);
    }

    @Override
    public ShortAnswerQuestion findByQuestionId(Integer questionId) {
        return shortAnswerQuestionMapper.findByQuestionId(questionId);
    }
}
