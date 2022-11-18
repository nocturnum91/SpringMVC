package org.nocturnum.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.nocturnum.domain.BoardVO;
import org.nocturnum.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService{

    private BoardMapper boardMapper;

    @Override
    public void register(BoardVO board) {
        log.info("REGISTER..." + board);
        boardMapper.insertSelectKey(board);
    }

    @Override
    public BoardVO get(Long bno) {
        log.info("GET..." + bno);
        return boardMapper.read(bno);
    }

    @Override
    public List<BoardVO> getList() {
        log.info("GET LIST...");
        return boardMapper.getList();
    }

    @Override
    public boolean modify(BoardVO board) {
        log.info("MODIFY..." + board);
        return boardMapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long bno) {
        log.info("REMOVE..." + bno);
        return boardMapper.delete(bno) == 1;
    }

}
