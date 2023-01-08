package org.nocturnum.blog.service;

import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    int register(ReplyVO reply);

    ReplyVO get(Long rno);

    List<ReplyVO> getList(Criteria cri, Long bno);

    int modify(ReplyVO reply);

    int remove(Long rno);

//    Long checkBno();
//
//    int getTotal(Criteria cri);
//
//    Long getPreBno(Criteria cri, Long bno);
//
//    Long getNextBno(Criteria cri, Long bno);

}
