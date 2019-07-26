package kr.co.crscube.crawler.model;

/**
 * Created by crs32 on 2019-07-26.
 */
public enum Site {

    SNU("http://snuhlab.org/checkup/check_list.aspx?page=%s&ins_class_code=L20&searchfield=TOTAL&searchword="),
    SAH(""),
    GC("");

    private String url;

    private Site(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
