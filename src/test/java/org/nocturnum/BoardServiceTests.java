package org.nocturnum;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nocturnum.domain.BoardVO;
import org.nocturnum.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class BoardServiceTests {

    @Setter(onMethod_ = @Autowired)
    private BoardService boardService;

    @Test
    public void testExist() {
        log.info(boardService);
        assertNotNull(boardService);
    }

    @Test
    public void testRegister() {
        BoardVO board = new BoardVO();
        board.setTitle("SELECT KEY");
        board.setContent("TEST CONTENT");
        board.setWriter("NOC");

        boardService.register(board);

        log.info("NEW REGISTER BNO: " + board.getBno());
    }

    @Test
    public void testGet() {
        log.info(boardService.get(95L));
    }

    @Test
    public void testGetList() {
        boardService.getList().forEach(log::info);
    }

    @Test
    public void testModify() {
        BoardVO board = boardService.get(95L);
        board.setTitle("UPDATE TITLE");

        log.info("MODIFY RESULT: " + boardService.modify(board));
    }


    @Test
    public void testRemove() {
        log.info("REMOVE RESULT: " + boardService.remove(95L));
    }




}
