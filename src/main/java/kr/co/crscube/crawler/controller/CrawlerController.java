package kr.co.crscube.crawler.controller;

import kr.co.crscube.crawler.model.CrawlerDataModel;
import kr.co.crscube.crawler.model.PaginationModel;
import kr.co.crscube.crawler.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by crs32 on 2019-07-23.
 */
@Controller
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;


    @GetMapping(value = "/snu", produces = MediaType.TEXT_HTML_VALUE )
    public String snu(Model model,
                      @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                      @RequestParam(value = "range", required = false, defaultValue = "1") Integer range) throws Exception {

        List<CrawlerDataModel> dataList = crawlerService.getSNULabData(pageNo);

        model.addAttribute("SNU", dataList);

        int listCnt = dataList.get(0).getListCnt();

        PaginationModel paginationModel = new PaginationModel();
        paginationModel.pageInfo(pageNo, range, listCnt);

        model.addAttribute("pagination", paginationModel);

        return "crawler/snu";
    }

    @GetMapping(value = "/sah", produces = MediaType.TEXT_HTML_VALUE)
    public String sah(Model model,
                      @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                      @RequestParam(value = "range", required = false, defaultValue = "1") Integer range) throws Exception {

        List<CrawlerDataModel> dataList = crawlerService.getSAHLabData(pageNo);

        model.addAttribute("SAH", dataList);

        int listCnt = dataList.get(0).getListCnt();

        PaginationModel paginationModel = new PaginationModel();
        paginationModel.pageInfo(pageNo, range, listCnt);

        model.addAttribute("pagination", paginationModel);


        return "crawler/sah";
    }

    @GetMapping(value = "/gc", produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model,
                       @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                       @RequestParam(value = "range", required = false, defaultValue = "1") Integer range) throws Exception {

        List<CrawlerDataModel> dataList = crawlerService.getGCLabData(pageNo);

        model.addAttribute("GC", dataList);

        int listCnt = dataList.get(0).getListCnt();

        PaginationModel paginationModel = new PaginationModel();
        paginationModel.pageInfo(pageNo, range, listCnt);

        model.addAttribute("pagination", paginationModel);

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
