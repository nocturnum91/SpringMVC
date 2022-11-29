package org.nocturnum;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nocturnum.blog.domain.BlogVO;
import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class BlogMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BlogMapper blogMapper;

    @Test
    public void testInsertSelectKey() {
        BlogVO blogVO = new BlogVO();
        blogVO.setTitle("SELECT KEY");
        blogVO.setContent("TEST CONTENT");
        //blogVO.setWriter("NOC");
        blogMapper.insertSelectKey(blogVO);

        log.info(blogVO);
    }

    @Test
    public void testRead() {
        BlogVO board = blogMapper.read(94L);
        log.info(board);
    }

    @Test
    public void testGetList() {
        blogMapper.getList().forEach(log::info);
    }

    @Test
    public void testInsert() {
        BlogVO blogVO = new BlogVO();
        blogVO.setTitle("HELLO");
        blogVO.setContent("TEST CONTENT");
        //blogVO.setWriter("NOC");
        blogMapper.insert(blogVO);

        log.info(blogVO);
    }

    @Test
    public void testUpdate() {
        BlogVO blogVO = blogMapper.read(94L);
        blogVO.setTitle("UPDATE TITLE");

        log.info("UPDATE COUNT: " + blogMapper.update(blogVO));
    }

    @Test
    public void testDelete() {
        log.info("DELETE COUNT: " + blogMapper.delete(95L));
    }


    @Test
    public void testPaging() {
        Criteria cri = new Criteria();
        cri.setPageNum(3);
        cri.setAmount(10);

        log.info(cri);

        blogMapper.getListWithPaging(cri).forEach(log::info);
    }

    @Test
    public void testSearch() {
        Criteria cri = new Criteria();
        cri.setKeyword("zz");

        blogMapper.getListWithPaging(cri).forEach(log::info);
    }

    @Test
    public void textPreBno() {

        Criteria cri = new Criteria();
        cri.setPageNum(1);
        cri.setAmount(10);
        cri.setKeyword("zz");

        log.info(cri);
        blogMapper.getPreBno(cri, 25L);
    }

    @Test
    public void textNextBno() {

        Criteria cri = new Criteria();
        cri.setPageNum(1);
        cri.setAmount(10);
        cri.setKeyword("zz");

        log.info(cri);
        blogMapper.getNextBno(cri, 25L);
    }

}
