package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.ShortAnswerQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ShortAnswerQuestionMapper {
    @Select("select * from shortanswer_question where questionId in (select questionId from paper_manage where questionType = 4 and paperId = #{paperId})")
    List<ShortAnswerQuestion> findByIdAndType(Integer paperId);

    @Select("select * from shortanswer_question")
    IPage<ShortAnswerQuestion> findAll(Page page);

    /**
     * 查询最后一条questionId
     * @return FillQuestion
     */
    @Select("select questionId from shortanswer_question order by questionId desc limit 1")
    ShortAnswerQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into shortanswer_question(subject,question,answer,analysis,level,section) values " +
            "(#{subject,},#{question},#{answer},#{analysis},#{level},#{section})")
    int add(ShortAnswerQuestion shortAnswerQuestion);

    @Select("select questionId from shortanswer_question where subject = #{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);

    @Select("select * from shortanswer_question where questionId = #{questionId}")
    ShortAnswerQuestion findByQuestionId(Integer questionId);
}
