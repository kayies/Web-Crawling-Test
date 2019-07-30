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
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by crs32 on 2019-07-24.
 */

@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Override
    public List<CrawlerDataModel> getSNULabData(Integer pageNo) throws Exception {

        List<CrawlerDataModel> dataList = new ArrayList<>();

        String snuUrl = String.format(Site.SNU.getUrl(), pageNo);
        Document SNUDoc = null;

        try {
            SNUDoc = Jsoup.connect(snuUrl).get();
        } catch (IOException e) {
            throw new Exception();
        }

        Elements elements = SNUDoc.select("div.jw_bh li.fix");
        CrawlerDataModel crawlerDataModel = null;

        int idx = 0;
        for ( Element element : elements) {
            crawlerDataModel = new CrawlerDataModel();

            String label = element.select("strong").text();

            String range = element.select("tr td").eq(4).text();
            if(range.contains("성인")) {
                range = range.substring(8, range.indexOf("소아"));
            }

            String unit = element.select("tr td").eq(5).text();

            crawlerDataModel.setLabel(label);
            crawlerDataModel.setRange(range);
            crawlerDataModel.setUnit(unit);

            if(idx == 0) {
                Elements pageArrElements = SNUDoc.select("div.paging_list li.paging_next").next("li");
                String pageHrefAttr = pageArrElements.select("a").attr("href");

                if(!"".equals(pageHrefAttr) && pageHrefAttr != null) {
                    String lastPage = pageHrefAttr.split("page=")[1];
                    lastPage = lastPage.substring(0, lastPage.indexOf("&ins_class_code"));
                    crawlerDataModel.setListCnt(getSNUTotalSize(lastPage));
                }else {
                    String lastPage = SNUDoc.select("div.paging_list ul.fix li.li_num").size()+"";
                    crawlerDataModel.setListCnt(getSNUTotalSize(lastPage));
                }
            }
            dataList.add(crawlerDataModel);
            idx ++;
        }
        return dataList;
    }

    private int getSNUTotalSize(String lastPage) throws Exception {

        String snuUrlForTotalSize = String.format(Site.SNU.getUrl(), lastPage);
        Document SNUDocForTotalSize = null;
        try {
            SNUDocForTotalSize = Jsoup.connect(snuUrlForTotalSize).get();
        } catch (IOException e) {
            throw new Exception();
        }
        Elements currentRow = SNUDocForTotalSize.select("div.jw_bh ul li.fix");;
        int lastPageSize = currentRow.size();
        int totalSize = (((Integer.parseInt(lastPage) - 1) * 10)) + lastPageSize;

        return totalSize;
    }

    @Override
    public List<CrawlerDataModel> getSAHLabData(Integer pageNo) throws Exception{

        List<CrawlerDataModel> dataList = new ArrayList<>();


        // 서울아산병원
        String SAHUrl = Site.SAH.getUrl();
        Document SAHDoc = null;
        try {
            SAHDoc = Jsoup.connect(SAHUrl).get();
        } catch (IOException e) {
            throw new Exception();
        }

        Elements elements = SAHDoc.select("li#A000013 a.ty_wrap");

        // paging처리
        int startPage = (pageNo - 1) * 10;
        int endPage = pageNo * 10;

        List<Element> ss = elements.stream()
                /*.map(Element::text)*/
                .filter(Element -> Element.text().contains("ABO"))
                .collect(Collectors.toList());


        for(int i = startPage; i < endPage; i++) {

            // 데이터가 없을경우의 방어코드
            if(!(elements.get(i)).equals("")) {
                CrawlerDataModel crawlerDataModel = new CrawlerDataModel();
                String SAHKey = (elements.get(i).attr("href").split("managementId=")[1]);

                String detailUrl = String.format(Site.SAH_detail.getUrl(), SAHKey);
                Document detailDoc = null;

                try {
                    detailDoc = Jsoup.connect(detailUrl).get();
                } catch (IOException e) {
                    throw new Exception();
                }

                String label = detailDoc.select("div.notice_wrap p.tit").text();
                String rangeAndUnit = detailDoc.select("div.notice_wrap div.cont div").eq(2).text();

                crawlerDataModel.setLabel(label);
                crawlerDataModel.setRange(rangeAndUnit);

                if(i == startPage) {
                    crawlerDataModel.setListCnt(elements.size());
                }
                dataList.add(crawlerDataModel);
            }
        }
        return dataList;
    }

    @Override
    public List<CrawlerDataModel> getGCLabData(Integer pageNo) throws Exception {

        List<CrawlerDataModel> dataList = new ArrayList<>();

        // GC Labs
        Map<String, String> postData = new HashMap<>();
        postData.put("currentPage", pageNo+"");
        postData.put("srchInitial", "");
        postData.put("srchDetcd", "29");
        postData.put("srchKey", "");
        postData.put("srchWord", "");

        String gcUrl = Site.GC.getUrl();
        Document GCDoc = null;
        try {
            GCDoc = Jsoup.connect(gcUrl).data(postData).post();
        } catch (IOException e) {
            throw new Exception();
        }

        Elements elements = GCDoc.select("form#frmInfo table.board tbody tr");

        int idx = 0;
        for (Element element : elements) {

           CrawlerDataModel crawlerDataModel = new CrawlerDataModel();
           String label = element.select("td").eq(4).text();

           crawlerDataModel.setLabel(label);
           String hrefValue = element.select("td").eq(4).select("a").attr("href");

           String[] split = hrefValue.split("code=");
           String testKey = split[1].substring(0, split[1].indexOf("'"));

           Map<String, String> rangeAndUnit = getGCRangeAndUnit(testKey);

           crawlerDataModel.setRange(rangeAndUnit.get("range"));

           if( idx == 0 ) {
               Elements pageArrElements = GCDoc.select("p.paging a.last");
               String pageHrefAttr = pageArrElements.attr("href");
               String lastPage = pageHrefAttr.replaceAll("[^0-9]", "");

               crawlerDataModel.setListCnt(getGCTotalSize(lastPage));
           }

           dataList.add(crawlerDataModel);
           idx++;
        }

        return dataList;
    }

    private int getGCTotalSize(String lastPage) throws Exception {

        Map<String, String> postData = new HashMap<>();
        postData.put("currentPage", lastPage+"");
        postData.put("srchInitial", "");
        postData.put("srchDetcd", "29");
        postData.put("srchKey", "");
        postData.put("srchWord", "");

        String snuGCForTotalSize = Site.GC.getUrl();
        Document GCDocForTotalSize = null;
        try {
            GCDocForTotalSize = Jsoup.connect(snuGCForTotalSize).data(postData).post();
        } catch (IOException e) {
            throw new Exception();
        }
        Elements currentRow = GCDocForTotalSize.select("form#frmInfo table.board tbody tr");
        int lastPageSize = currentRow.size();
        int totalSize = (((Integer.parseInt(lastPage) - 1) * 10)) + lastPageSize;

        return totalSize;
    }


    private Map<String, String> getGCRangeAndUnit(String testKey) throws Exception {

        Map<String, String> rangeAndUnit = new HashMap<>();

        String gcUrl = String.format(Site.GC_detail.getUrl(), testKey);

        Document doc = null;

        try {
            doc = Jsoup.connect(gcUrl).get();
        } catch (IOException e) {
            throw new Exception();
        }

        Elements rangeElements = doc.select("table.type3").eq(2).select("tbody tr td").eq(0);
        String range = rangeElements.text();

        rangeAndUnit.put("range", range);
        rangeAndUnit.put("unit", range);

        return rangeAndUnit;
    }
}
