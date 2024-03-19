package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.CodeQuestion;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CodeQuestionMapper {
    @Select("select * from code_question where questionId in (select questionId from paper_manage where questionType = 5 and paperId = #{paperId})")
    List<CodeQuestion> findByIdAndType(Integer paperId);

    @Select("select * from code_question")
    IPage<CodeQuestion> findAll(Page page);

    /**
     * 查询最后一条questionId
     * @return FillQuestion
     */
    @Select("select questionId from code_question order by questionId desc limit 1")
    CodeQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into code_question(subject,question,answer,analysis,level,section) values " +
            "(#{subject,},#{question},#{answer},#{analysis},#{level},#{section})")
    int add(CodeQuestion codeQuestion);

    @Select("select questionId from code_question where subject = #{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);

    @Select("select * from code_question where questionId = #{questionId}")
    CodeQuestion findByQuestionId(Integer questionId);


}
