package org.nocturnum.service;

import org.nocturnum.domain.BoardVO;

import java.util.List;

public interface BoardService {

    void register(BoardVO board);

    BoardVO get(Long bno);

    List<BoardVO> getList();

    boolean modify(BoardVO board);

    boolean remove(Long bno);

}
