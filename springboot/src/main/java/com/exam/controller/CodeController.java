package com.exam.controller;
import com.exam.complier.complier_c.CComplier;
import com.exam.complier.complier_cplusplus.CplusplusComplier;
import com.exam.complier.complier_java.JavaComplier;
import com.exam.entity.ApiResult;
import com.exam.util.ApiResultHandler;
import com.exam.util.FileUtil;
import com.exam.vo.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CodeController {

    @Autowired
    private CComplier cComplier;

    @Autowired
    private CplusplusComplier cplusplusComplier;

    @Autowired
    private JavaComplier javaComplier;

    @PostMapping("/javaComplier")
    public ApiResult javaCom(@RequestParam String code){
        FileUtil.writeFile(code,"D:\\GraduationDesign\\code\\springboot\\javaCode\\Main.java");
        javaComplier.complier();
        if(javaComplier.getOutputData() == null) {
            return ApiResultHandler.buildApiResult(400,"运行失败",javaComplier.getErrorMsg());
        }else {
            return ApiResultHandler.buildApiResult(200,"运行成功",javaComplier.getOutputData());
        }
    }
    @PostMapping("/cComplier")
    public ApiResult cCom(@RequestParam String code){
        FileUtil.writeFile(code,"D:\\GraduationDesign\\code\\springboot\\cCode\\main.c");
        cComplier.complier();
        if(cComplier.getOutputData() == null) {
            return ApiResultHandler.buildApiResult(200,"运行失败",cComplier.getErrorMsg());
        }else {
            return ApiResultHandler.buildApiResult(400,"运行成功",cComplier.getOutputData());
        }
    }
    @PostMapping("/cpComplier")
    public ApiResult cpCom(@RequestParam String code){
        FileUtil.writeFile(code,"D:\\GraduationDesign\\code\\springboot\\c++Code\\main.cpp");
        cplusplusComplier.complier();
        if(cplusplusComplier.getOutputData() == null) {
            return ApiResultHandler.buildApiResult(400,"运行失败",cplusplusComplier.getErrorMsg());
        }else {
            return ApiResultHandler.buildApiResult(200,"运行成功",cplusplusComplier.getOutputData());
        }
    }


}
