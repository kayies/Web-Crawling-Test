package kr.co.crscube.crawler.controller;

import kr.co.crscube.crawler.model.CrawlerDataModel;
import kr.co.crscube.crawler.service.CrawlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by crs32 on 2019-08-02.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(CrawlerController.class)
public class CrawlerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CrawlerService crawlerService;

    @Test
    public void doTest() throws Exception {

        // given
        int page = 1;
        List<CrawlerDataModel> crawlerDataModels = new ArrayList<>();

        given(crawlerService.getSNULabData(page)).willReturn(crawlerDataModels);

        // when
        final ResultActions actions = mvc.perform(get("/get-snu-json", page)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // then
        actions.andExpect(status().isOk());
    }


}
