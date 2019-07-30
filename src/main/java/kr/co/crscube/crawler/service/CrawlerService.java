package kr.co.crscube.crawler.service;


import kr.co.crscube.crawler.model.CrawlerDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs32 on 2019-07-24.
 */
public interface CrawlerService {

    List<CrawlerDataModel> getSNULabData(Integer pageNo) throws Exception;
    List<CrawlerDataModel> getSAHLabData(Integer pageNo) throws Exception ;
    List<CrawlerDataModel> getGCLabData(Integer pageNo) throws Exception;

}
