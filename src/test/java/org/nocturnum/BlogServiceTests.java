package org.nocturnum;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nocturnum.blog.domain.BlogVO;
import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class BlogServiceTests {

    @Setter(onMethod_ = @Autowired)
    private BlogService blogService;

    @Test
    public void testExist() {
        log.info(blogService);
        assertNotNull(blogService);
    }

    @Test
    public void testRegister() {
        BlogVO blogVO = new BlogVO();
        blogVO.setTitle("SELECT KEY2234234234");
        blogVO.setContent("TEST CONTENT3463634");
        //blogVO.setWriter("NOC");

        blogService.register(blogVO);

        log.info("NEW REGISTER BNO: " + blogVO.getBno());
    }

    @Test
    public void testGet() {
        log.info(blogService.get(95L));
    }

    @Test
    public void testGetList() {
        //blogService.getList().forEach(log::info);
        blogService.getList(new Criteria(2, 10)).forEach(log::info);
    }

    @Test
    public void testModify() {
        BlogVO board = blogService.get(95L);
        board.setTitle("UPDATE TITLE");

        log.info("MODIFY RESULT: " + blogService.modify(board));
    }


    @Test
    public void testRemove() {
        log.info("REMOVE RESULT: " + blogService.remove(95L));
    }


}
