package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.CodeQuestion;
import com.exam.serviceimpl.CodeQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeQuestionController {
    @Autowired
    private CodeQuestionServiceImpl codeQuestionService;

    @PostMapping("/codeQuestion")
    public ApiResult add(@RequestBody CodeQuestion codeQuestion) {
        int res = codeQuestionService.add(codeQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    @GetMapping("/codeQuestionId")
    public ApiResult findOnlyQuestionId() {
        CodeQuestion res = codeQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }
}
