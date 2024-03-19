package com.exam.controller;
import com.exam.entity.ApiResult;
import com.exam.entity.CodeQuestion;
import com.exam.serviceimpl.CodeQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.vo.AutomaticGrading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomaticGradingCodeController {

    @Autowired
    private CodeQuestionServiceImpl codeQuestionService;
    @PostMapping("/automaticCode")
    public ApiResult automaticGrading(@RequestBody AutomaticGrading automaticGrading){
        System.out.println("后端进入代码题");
        double score = 0;
        Integer[] questionIds = automaticGrading.getQuestionId();
        String[] topicAnswer = automaticGrading.getTopicAnswer();
        for (int i = 0; i < questionIds.length && i < topicAnswer.length; i++) {
            CodeQuestion codeQuestion = codeQuestionService.findByQuestionId(questionIds[i]);
            if (topicAnswer != null && topicAnswer[i] != null){
                if (codeQuestion.getAnswer().replaceAll("\\s+","").equals(topicAnswer[i].replaceAll("\\s+",""))){
                    score += codeQuestion.getScore();
                }
            }
        }
        ApiResult success = ApiResultHandler.success(score);
        return success;
    }
}
