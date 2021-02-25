package com.company.project.web;

import com.company.project.model.OCRRequest;
import com.company.project.service.OCRService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/OCR")
public class OCRController {

    @Resource
    private OCRService ocrService;

    @RequestMapping(value = "/getResult", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String getResult(@RequestBody OCRRequest ocrRequest) {
        return ocrService.getOCRResult(ocrRequest.getImgBase64(), ocrRequest.getLangType());
    }
}
