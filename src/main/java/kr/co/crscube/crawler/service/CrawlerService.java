package kr.co.crscube.crawler.service;


import kr.co.crscube.crawler.model.CrawlerDataModel;

import java.util.List;

/**
 * Created by crs32 on 2019-07-24.
 */
public interface CrawlerService {

    List<CrawlerDataModel> getSNULabData();
    String getSAHLabData();
    List<CrawlerDataModel> getGCLabData();
}
