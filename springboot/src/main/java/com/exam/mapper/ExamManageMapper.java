package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExamManageMapper {

    @Select("select * from exam_manage where type = \"测试\"")
    List<ExamManage> findAll();

    @Select("select * from exam_manage ")
    IPage<ExamManage> findAll(Page page);


    //查找考试类型为测试的试卷


    @Select("select * from exam_manage ")
    IPage<ExamManage> findAlls(Page page);

    @Select("select * from exam_manage where examCode = #{examCode}")
    ExamManage findById(Integer examCode);

    @Delete("delete from exam_manage where examCode = #{examCode}")
    int delete(Integer examCode);

    @Update("update exam_manage set description = #{description},source = #{source},paperId = #{paperId}," +
            "examDate = #{examDate},grade = #{grade},term = #{term}," +
            "major = #{major},institute = #{institute},totalScore = #{totalScore}," +
            "type = #{type},tips = #{tips} where examCode = #{examCode}")
    int update(ExamManage exammanage);

    @Options(useGeneratedKeys = true,keyProperty = "examCode")
    @Insert("insert into exam_manage(description,source,paperId,examDate,grade,term,major,institute,totalScore,type,tips)" +
            " values(#{description},#{source},#{paperId},#{examDate},#{grade},#{term},#{major},#{institute},#{totalScore},#{type},#{tips})")
    int add(ExamManage exammanage);

    /**
     * 查询最后一条记录的paperId,返回给前端达到自增效果
     * @return paperId
     */
    @Select("select paperId from exam_manage order by paperId desc limit 1")
    ExamManage findOnlyPaperId();

    @Select("select examCode,description,source,paperId,examDate,grade,term,major,institute,totalScore,type,tips from exam_manage where (institute = #{institute} AND type=\"期末考试\") or (institute = '全部学院' AND type=\"期末考试\") order by examCode desc")
    IPage<ExamManage> findAllByInstitute(Page<?> page,String institute);

    @Select("select examCode,description,source,paperId,examDate,grade,term,major,institute,totalScore,type,tips from exam_manage where institute = #{institute} order by examCode desc")
    List<ExamManage> findAllByInstitute(String institute);
}
