package com.exam;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.exam.complier.complier_c.CComplier;
import com.exam.entity.CodeQuestion;
import com.exam.mapper.CodeQuestionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamsystemApplicationTests {

    @Autowired
    public CodeQuestionMapper codeQuestionMapper;

    @Test
    public void contextLoads() {




    }

}

