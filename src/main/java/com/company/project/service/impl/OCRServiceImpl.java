package com.company.project.service.impl;

import com.baidu.OCR.OCRApi;
import com.company.project.service.OCRService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OCRServiceImpl implements OCRService {

    @Resource
    private OCRApi ocrApi;

    @Override
    public String getOCRResult(String imgBase64, String langType) {
        return ocrApi.getOCRResult(imgBase64, langType);
    }
}
