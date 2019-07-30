package kr.co.crscube.crawler.model;

/**
 * Created by crs32 on 2019-07-26.
 */
public enum Site {

    SNU("http://snuhlab.org/checkup/check_list.aspx?page=%s&ins_class_code=L20&searchfield=TOTAL&searchword="),
    SAH("http://m.amc.seoul.kr/asan/mobile/healthinfo/management/managementMobileSubMain.do"),
    SAH_detail("http://m.amc.seoul.kr/asan/mobile/healthinfo/management/managementDetail.do?managementId=%s"),
    GC("http://www.gclabs.co.kr/testingInfo/testingItem_list"),
    GC_detail("http://www.gclabs.co.kr/testingInfo/testingItemOra_view?code=%s");

    private String url;

    private Site(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
