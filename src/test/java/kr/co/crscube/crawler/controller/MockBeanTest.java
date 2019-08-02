package kr.co.crscube.crawler.controller;

import kr.co.crscube.crawler.model.CrawlerDataModel;
import kr.co.crscube.crawler.service.CrawlerService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by crs32 on 2019-08-01.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MockBeanTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    CrawlerService crawlerService;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void doTest() throws Exception{

        List<CrawlerDataModel> cd = new ArrayList<>();

        when(crawlerService.getGCLabData(1)).thenReturn(cd);

        List result = testRestTemplate.getForObject("/get-snu-json", List.class);
        assertThat(result).isEqualTo(cd);



    }
}
