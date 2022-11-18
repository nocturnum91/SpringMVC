package org.nocturnum;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nocturnum.domain.BoardVO;
import org.nocturnum.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;

    @Test
    public void testRead() {
        BoardVO board = boardMapper.read(94L);
        log.info(board);
    }

    @Test
    public void testGetList() {
        boardMapper.getList().forEach(log::info);
    }

    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();
        board.setTitle("HELLO");
        board.setContent("TEST CONTENT");
        board.setWriter("NOC");
        boardMapper.insert(board);

        log.info(board);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO board = new BoardVO();
        board.setTitle("SELECT KEY");
        board.setContent("TEST CONTENT");
        board.setWriter("NOC");
        boardMapper.insertSelectKey(board);

        log.info(board);
    }

    @Test
    public void testUpdate() {
        BoardVO board = boardMapper.read(94L);
        board.setTitle("UPDATE TITLE");

        log.info("UPDATE COUNT: " + boardMapper.update(board));
    }

    @Test
    public void testDelete() {
        log.info("DELETE COUNT: " + boardMapper.delete(95L));
    }


}
