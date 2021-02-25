package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Test;
import com.company.project.model.TranslateRequest;
import com.company.project.service.TranslateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/translate")
public class TranslateController {

    @Resource
    private TranslateService translateService;

    @RequestMapping(value = "/getResult", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String getResult(@RequestBody TranslateRequest translateRequest) {
        String res = translateService.translate(translateRequest.getQuery(), translateRequest.getFrom(), translateRequest.getTo());
        return res;
    }

}
