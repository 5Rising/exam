package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.FillQuestion;
import com.exam.entity.ShortAnswerQuestion;
import com.exam.serviceimpl.ShortAnswerQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortAnswerQuestionController {
    @Autowired
    private ShortAnswerQuestionServiceImpl shortAnswerQuestionService;

    @PostMapping("/shortanswerQuestion")
    public ApiResult add(@RequestBody ShortAnswerQuestion shortAnswerQuestion) {
        int res = shortAnswerQuestionService.add(shortAnswerQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    //只需要问题的id
    @GetMapping("/shortanswerQuestionId")
    public ApiResult findOnlyQuestionId() {
        ShortAnswerQuestion res = shortAnswerQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }
}
