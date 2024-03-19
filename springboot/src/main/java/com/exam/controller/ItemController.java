package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.PaperManage;
import com.exam.service.PaperService;
import com.exam.serviceimpl.*;
import com.exam.util.ApiResultHandler;
import com.exam.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    FillQuestionServiceImpl fillQuestionService;

    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    ShortAnswerQuestionServiceImpl shortAnswerQuestionService;

    @Autowired
    CodeQuestionServiceImpl codeQuestionService;

    @Autowired
    PaperService paperService;


    // 对应自动组卷
    @PostMapping("/item")
    public ApiResult ItemController(@RequestBody Item item) {
        // 选择题
        Integer changeNumber = item.getChangeNumber();
        // 填空题
        Integer fillNumber = item.getFillNumber();
        // 判断题
        Integer judgeNumber = item.getJudgeNumber();

        Integer shortanswerNumber = item.getShortanswerNumber();

        Integer codeNumber = item.getCodeNumber();
        // 出卷id
        Integer paperId = item.getPaperId();

        // 选择题数据库获取
        if (changeNumber != 0) {
            List<Integer> changeNumbers = multiQuestionService.findBySubject(item.getSubject(), changeNumber);
            if (changeNumbers.size() == 0) {
                return ApiResultHandler.buildApiResult(400, "题库获取失败,请您手动添加", null);
            }
            for (Integer number : changeNumbers) {
                PaperManage paperManage = new PaperManage(paperId, 1, number);
                int index = paperService.add(paperManage);
                if (index == 0)
                    return ApiResultHandler.buildApiResult(400, "选择题组卷保存失败", null);
            }
        }
        if (fillNumber != 0) {
            // 填空题
            List<Integer> fills = fillQuestionService.findBySubject(item.getSubject(), fillNumber);
            if (fills.size() == 0)
                return ApiResultHandler.buildApiResult(400, "题库获取失败,请您手动添加", null);
            for (Integer fillNum : fills) {
                PaperManage paperManage = new PaperManage(paperId, 2, fillNum);
                int index = paperService.add(paperManage);
                if (index == 0)
                    return ApiResultHandler.buildApiResult(400, "填空题题组卷保存失败", null);
            }
        }
        if (judgeNumber != 0) {
            // 判断题
            List<Integer> judges = judgeQuestionService.findBySubject(item.getSubject(), judgeNumber);
            if (judges.size() == 0)
                return ApiResultHandler.buildApiResult(400, "题库获取失败,请您手动添加", null);
            for (Integer judge : judges) {
                PaperManage paperManage = new PaperManage(paperId, 3, judge);
                int index = paperService.add(paperManage);
                if (index == 0)
                    return ApiResultHandler.buildApiResult(400, "判断题题组卷保存失败", null);
            }
        }
        if (shortanswerNumber != 0) {
            // 简答题
            List<Integer> shorts = shortAnswerQuestionService.findBySubject(item.getSubject(), shortanswerNumber);
            if (shorts.size() == 0)
                return ApiResultHandler.buildApiResult(400, "题库获取失败,请您手动添加", null);
            for (Integer shortNum : shorts) {
                PaperManage paperManage = new PaperManage(paperId, 4, shortNum);
                int index = paperService.add(paperManage);
                if (index == 0)
                    return ApiResultHandler.buildApiResult(400, "简答题题组卷保存失败", null);
            }
        }
        if (codeNumber != 0) {
            // 代码题
            System.out.println(item.getCodeNumber());
            List<Integer> codes = codeQuestionService.findBySubject(item.getSubject(), codeNumber);
            if (codes.size() == 0)
                return ApiResultHandler.buildApiResult(400, "题库获取失败,请您手动添加", null);
            for (Integer codeNum : codes) {
                PaperManage paperManage = new PaperManage(paperId, 5, codeNum);
                int index = paperService.add(paperManage);
                if (index == 0)
                    return ApiResultHandler.buildApiResult(400, "代码题题组卷保存失败", null);
            }
        }
            return ApiResultHandler.buildApiResult(200, "试卷组卷成功", null);
    }

}
