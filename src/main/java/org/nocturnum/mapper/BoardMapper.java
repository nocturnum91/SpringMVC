package org.nocturnum.mapper;

import org.nocturnum.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

    void insert(BoardVO vo);

    void insertSelectKey(BoardVO vo);

    BoardVO read(Long bno);

    List<BoardVO> getList();

    int update(BoardVO vo);

    int delete(Long bno);

}
