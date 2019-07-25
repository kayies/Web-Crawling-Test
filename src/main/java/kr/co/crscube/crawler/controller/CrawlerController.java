package kr.co.crscube.crawler.controller;

import kr.co.crscube.crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * Created by crs32 on 2019-07-23.
 */
@Controller
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String snu(Model model) {

        model.addAttribute("SNU", crawlerService.getSNULabData());

        return "crawler/snu";
    }

    @GetMapping(value = "/sah", produces = MediaType.TEXT_HTML_VALUE)
    public String sah(Model model) {

        model.addAttribute("SAH", crawlerService.getSAHLabData());

        return "crawler/sah";
    }

    @GetMapping(value = "/gc", produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model) {

        model.addAttribute("GC", crawlerService.getGCLabData());


        return "crawler/gc";
    }

}
