package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AnswerMapper {
    @Select("select question, subject, score, section,level, \"选择题\" as type from multi_question  \n" +
            "union select  question, subject, score, section,level, \"判断题\" as type  from judge_question \n" +
            "union select  question, subject, score, section,level, \"填空题\" as type from fill_question\n" +
            "union select  question, subject, score, section,level, \"简答题\" as type from shortanswer_question\n" +
            "union select  question, subject, score, section,level, \"代码题\" as type from code_question")
    IPage<AnswerVO> findAll(Page page);
}
