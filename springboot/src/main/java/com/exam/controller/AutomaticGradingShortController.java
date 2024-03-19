package com.exam.controller;
import com.exam.entity.ApiResult;
import com.exam.util.ApiResultHandler;
import org.apache.commons.lang3.StringUtils;
import com.exam.entity.ShortAnswerQuestion;
import com.exam.serviceimpl.ShortAnswerQuestionServiceImpl;
import com.exam.vo.AutomaticGrading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomaticGradingShortController {

    @Autowired
    private ShortAnswerQuestionServiceImpl shortAnswerQuestionService;
    @PostMapping("/automaticShort")
    public ApiResult automaticGrading(@RequestBody AutomaticGrading automaticGrading){
        System.out.println("后端进入简答题");
        double score = 0;
        Integer[] questionIds = automaticGrading.getQuestionId();
        String[] topicAnswer = automaticGrading.getTopicAnswer();
        for (int i = 0; i < questionIds.length && i < topicAnswer.length; i++) {
            ShortAnswerQuestion shortAnswerQuestion = shortAnswerQuestionService.findByQuestionId(questionIds[i]);
            double jaroWinklerDistance = StringUtils.getJaroWinklerDistance(topicAnswer[i],shortAnswerQuestion.getAnswer());
            score += shortAnswerQuestion.getScore()*jaroWinklerDistance;
        }
        ApiResult success = ApiResultHandler.success(score);
        return success;
    }
}
