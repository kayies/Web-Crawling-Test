package kr.co.crscube.crawler.service.impl;

import kr.co.crscube.crawler.model.CrawlerDataModel;
import kr.co.crscube.crawler.model.PaginationModel;
import kr.co.crscube.crawler.model.Site;
import kr.co.crscube.crawler.service.CrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by crs32 on 2019-07-24.
 */

@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Override
    public List<CrawlerDataModel> getSNULabData(Integer pageNo) throws Exception {

        List<CrawlerDataModel> dataList = new ArrayList<>();

        // 서울대
        pageNo = (pageNo == null) ? 1 : pageNo;

        String snuUrl = String.format(Site.SNU.getUrl(), pageNo);
        Document SNUDoc = null;
        try {
            SNUDoc = Jsoup.connect(snuUrl).get();
        } catch (IOException e) {
            throw new Exception();
        }

        Elements labelElements = SNUDoc.select("div.jw_bh li.fix strong");
        Elements rangeElements = SNUDoc.select("div.jw_bh li.fix");
        Elements unitElements = SNUDoc.select("div.jw_bh li.fix");

        for (Element element : labelElements) {

        }

        if (!CollectionUtils.isEmpty(labelElements)) {

        }

        labelElements.stream().forEach(element -> {

        });

        for(Element element : labelElements) {

        }


        for(int i = 0 ; i < labelElements.size(); i++) {



            CrawlerDataModel crawlerDataModel = new CrawlerDataModel();

            String label = labelElements.eq(i).text();
            String range = rangeElements.eq(i).select("tr td").eq(4).text();

            if(range.contains("성인")) {
                range = range.substring(8, range.indexOf("소아"));
            }

            String unit = unitElements.eq(i).select("tr td").eq(5).text();

            crawlerDataModel.setLabel(label);
            crawlerDataModel.setRange(range);
            crawlerDataModel.setUnit(unit);

            dataList.add(crawlerDataModel);
        }

        return dataList;
    }

    @Override
    public List<CrawlerDataModel> getSAHLabData() {

        List<CrawlerDataModel> dataList = new ArrayList<>();

        try {
            // 서울아산병원
            Document SAHDoc = Jsoup.connect("http://m.amc.seoul.kr/asan/mobile/healthinfo/management/managementMobileSubMain.do").get();
            Elements keyElement = SAHDoc.select("li#A000013 a.ty_wrap");

            for(int i=0; i<10; i++) {
                /*for(int i=0; i<keyElement.size(); i++) {*/

                CrawlerDataModel crawlerDataModel = new CrawlerDataModel();

                String SAHKey = ((keyElement.eq(i)).attr("href").split("managementId=")[1]);

                String detailUrl = "http://m.amc.seoul.kr/asan/mobile/healthinfo/management/managementDetail.do?managementId=" + SAHKey;
                Document detailDoc = Jsoup.connect(detailUrl).get();

                String label = detailDoc.select("div.notice_wrap p.tit").text();
                String rangeAndUnit = detailDoc.select("div.notice_wrap div.cont div").eq(2).text();

                crawlerDataModel.setLabel(label);
                crawlerDataModel.setRange(rangeAndUnit);

                dataList.add(crawlerDataModel);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    @Override
    public List<CrawlerDataModel> getGCLabData() {

        List<CrawlerDataModel> dataList = new ArrayList<>();

        try {
            // GC Labs
            int pageNo = 1;
            boolean pageYn = true;

            while(pageYn) {


                Map<String, String> postData = new HashMap<>();
                postData.put("currentPage", pageNo+"");
                postData.put("srchInitial", "");
                postData.put("srchDetcd", "29");
                postData.put("srchKey", "");
                postData.put("srchWord", "");

                Document doc = Jsoup.connect("http://www.gclabs.co.kr/testingInfo/testingItem_list").data(postData).post();
                Elements labelElements = doc.select("table.type1 tbody tr");
                String noDataMsg = labelElements.select("p").text();

                if(!noDataMsg.equals("") &&  noDataMsg != null) {
                    pageYn = false;
                    break;
                }

               for (int i = 0 ; i < labelElements.size(); i++ ) {

                    CrawlerDataModel crawlerDataModel = new CrawlerDataModel();
                    String label = labelElements.eq(i).select("td").eq(4).text();

                    crawlerDataModel.setLabel(label);
                    String hrefValue = labelElements.eq(i).select("td").eq(4).select("a").attr("href");

                    String[] split = hrefValue.split("code=");
                    String testKey = split[1].substring(0, split[1].indexOf("'"));

                    Map<String, String> rangeAndUnit = getGCRangeAndUnit(testKey);

                    crawlerDataModel.setRange(rangeAndUnit.get("range"));

                    dataList.add(crawlerDataModel);
                }
                pageNo++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }



    private Map<String, String> getGCRangeAndUnit(String testKey) {
        Map<String, String> rangeAndUnit = null;
        try {
            rangeAndUnit = new HashMap<>();
            String gcUrl = "http://www.gclabs.co.kr/testingInfo/testingItemOra_view?code=" + testKey;
            Document doc = Jsoup.connect(gcUrl).get();
            Elements rangeElements = doc.select("table.type3").eq(2).select("tbody tr td").eq(0);
            String range = rangeElements.text();

            rangeAndUnit.put("range", range);
            rangeAndUnit.put("unit", range);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rangeAndUnit;
    }



    private String getTotalSize(String url) {

        String str = "";
        int totalSize = 0;
        try {
            Document document = Jsoup.connect(url).get();

            // GCU <a href="javascript:goPage(276);" class="last"><span>마지막</span></a>
            Elements lastPageBtn = document.select("a.last");

            if( lastPageBtn == null ) {

            }

            str = lastPageBtn.text();




        } catch (IOException e) {

            e.printStackTrace();
        }

        return str;
    }


    @Override
    public ArrayList<Integer> getPageList(String type) {


        ArrayList<Integer> page = new ArrayList<>();

        switch (type) {
            case  "SNU" :

                boolean pageYn = true;
                String url = "http://snuhlab.org/checkup/check_list.aspx?ins_class_code=L20&searchfield=TOTAL&searchword=";

                try {

                    Document document = Jsoup.connect(url).get();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


        }

        return page;
    }
}
