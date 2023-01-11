package org.nocturnum.blog.service;

import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.domain.ReplyPageDTO;
import org.nocturnum.blog.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    int register(ReplyVO reply);

    ReplyVO get(Long rno);

    List<ReplyVO> getListWithPaging(Criteria cri, Long bno);

    int modify(ReplyVO reply);

    int remove(Long rno);

    ReplyPageDTO getListPage(Criteria cri, Long bno);

//    Long checkBno();
//
//    int getTotal(Criteria cri);
//
//    Long getPreBno(Criteria cri, Long bno);
//
//    Long getNextBno(Criteria cri, Long bno);

}
