import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by crs32 on 2019-07-23.
 */
@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {

        String subResult0 = "";
        String subResult1 = "";
        String subResult2 = "";
        String strResult = "";

        String 맘마미아 = "";
        try {
            // 서울대
            Document doc = Jsoup.connect("http://snuhlab.org/checkup/check_list.aspx?ins_class_code=L20&searchfield=TOTAL&searchword=").get();
            Elements contents = doc.select("div.jw_bh li.fix").eq(5);
            String result = contents.select("tr").eq(4).text();
            subResult0 = result.substring(14, 38);

            // 서울아산병원
            Document doc2 = Jsoup.connect("http://m.amc.seoul.kr/asan/mobile/healthinfo/management/managementDetail.do?managementId=119").get();
            Elements contents2 = doc2.select("div.cont div").eq(2);
            subResult1 = contents2.select("p").text();

            // GC Labs
            Document doc3 = Jsoup.connect("http://www.gclabs.co.kr/testingInfo/testingItemOra_view?code=H105").get();
            Elements content3  = doc3.select("table").eq(2).select("tr").eq(0).select("td");
            subResult2 = content3.text();

            strResult = " 1. 서울대 병원 Hb 정상 수치 \n" + subResult0 + "\n2. 서울아산병원 Hb 정상 수치 \n" + subResult1 + "\n3. GC Labs Hb 정상수치 \n" + subResult2;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResult;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}
