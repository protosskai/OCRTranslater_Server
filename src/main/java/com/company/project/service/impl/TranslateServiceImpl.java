package com.company.project.service.impl;

import com.baidu.translate.demo.TransApi;
import com.company.project.service.TranslateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TranslateServiceImpl implements TranslateService {

    @Resource
    private TransApi transApi;

    @Override
    public String translate(String query, String from, String to) {
        
        return transApi.getTransResult(query, from, to);
    }
}
