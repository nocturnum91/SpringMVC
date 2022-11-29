package org.nocturnum;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BlogControllerTests {

    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext ctx;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void registerTest() throws Exception {
        String resultPage = Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders
                        .post("/blog/posting")
                        .param("title", "테스트 제목 입력")
                        .param("content", "테스트 내용 입력")
                        .param("writer", "테스터"))
                .andReturn().getModelAndView()).getViewName();

        log.info(resultPage);
    }

    @Test
    public void getTest() throws Exception {
        log.info(Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders
                        .get("/blog/post")
                        .param("bno", "94"))
                .andReturn().getModelAndView()).getModelMap());
    }

    @Test
    public void listTest() throws Exception {
        log.info(Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders
                        .get("/blog/home")
                        .param("pageNum", "2")
                        .param("amount", "10"))
                .andReturn().getModelAndView()).getModelMap());
    }

    @Test
    public void modifyTest() throws Exception {
        String resultPage = Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders
                        .post("/blog/modify")
                        .param("bno", "95")
                        .param("title", "테스트 제목 수정")
                        .param("content", "테스트 내용 수정")
                        .param("writer", "tester"))
                .andReturn().getModelAndView()).getViewName();

        log.info(resultPage);
    }

    @Test
    public void removeTest() throws Exception {
        String resultPage = Objects.requireNonNull(mockMvc.perform(MockMvcRequestBuilders
                        .post("/blog/remove")
                        .param("bno", "96"))
                .andReturn().getModelAndView()).getViewName();

        log.info(resultPage);
    }


}
