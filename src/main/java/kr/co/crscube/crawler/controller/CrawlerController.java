package kr.co.crscube.crawler.controller;

import kr.co.crscube.crawler.model.CrawlerDataModel;
import kr.co.crscube.crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by crs32 on 2019-07-23.
 */
@Controller
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;

    @GetMapping(value = "/snu", produces = MediaType.TEXT_HTML_VALUE )
    public String snu(Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo) {


        String type = "SNU";
        model.addAttribute("SNU", crawlerService.getSNULabData(pageNo));

        model.addAttribute("pageArr", crawlerService.getPageList(type));



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


    /*
    @RequestMapping(value = "/get-snu-json", method = RequestMethod.GET)
    @ResponseBody
    public List<CrawlerDataModel> getSNUjsonData(Model model) {
        List<CrawlerDataModel> crawlerDataModelArrayList = crawlerService.getSNULabData();

        return crawlerDataModelArrayList;
    }

    @RequestMapping(value = "/get-sah-json", method = RequestMethod.GET)
    @ResponseBody
    public List<CrawlerDataModel> getSAHjsonData(Model model) {
        List<CrawlerDataModel> crawlerDataModelArrayList = crawlerService.getSAHLabData();
        return crawlerDataModelArrayList;
    }

    @RequestMapping(value = "/get-gc-json", method = RequestMethod.GET)
    @ResponseBody
    public List<CrawlerDataModel> getGCjsonData(Model model) {
        List<CrawlerDataModel> crawlerDataModelArrayList = crawlerService.getGCLabData();
        return crawlerDataModelArrayList;
    }*/

}
