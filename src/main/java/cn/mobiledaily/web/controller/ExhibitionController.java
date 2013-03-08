package cn.mobiledaily.web.controller;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("exhibitions")
public class ExhibitionController {
    @Autowired
    private ExhibitionService exhibitionService;

    @ResponseBody
    @RequestMapping("{code}")
    public Exhibition getByCode(@PathVariable String code) {
        return exhibitionService.findByCode(code);
    }
}
