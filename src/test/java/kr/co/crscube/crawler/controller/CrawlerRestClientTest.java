package kr.co.crscube.crawler.controller;

import kr.co.crscube.crawler.model.CrawlerDataModel;
import kr.co.crscube.crawler.service.CrawlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by crs32 on 2019-08-02.
 */

@RunWith(SpringRunner.class)
@RestClientTest(CrawlerController.class)
public class CrawlerRestClientTest {

    @Autowired
    CrawlerService crawlerService;

    @Autowired
    MockRestServiceServer server;

    @Test
    public void doTest() throws Exception {


        server.expect(requestTo("/get-snu-json"))
                .andRespond(withSuccess(
                        new ClassPathResource("/test.json",
                                               getClass()),
                                               MediaType.APPLICATION_JSON));

        List<CrawlerDataModel> list = crawlerService.getSNULabData(1);
        String listSize = list.size()+"";

        assertThat(listSize).isEqualTo("10");

    }





}
