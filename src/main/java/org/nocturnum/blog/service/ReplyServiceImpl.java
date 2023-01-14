package org.nocturnum.blog.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.domain.ReplyPageDTO;
import org.nocturnum.blog.domain.ReplyVO;
import org.nocturnum.blog.mapper.BlogMapper;
import org.nocturnum.blog.mapper.ReplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j
public class ReplyServiceImpl implements ReplyService {

    private ReplyMapper replyMapper;

    private BlogMapper blogMapper;

    @Transactional
    @Override
    public int register(ReplyVO reply) {
        log.info("REGISTER..." + reply);
        blogMapper.updateReplyCnt(reply.getBno(), 1);
        return replyMapper.insert(reply);
    }

    @Override
    public ReplyVO get(Long rno) {
        log.info("GET..." + rno);
        return replyMapper.read(rno);
    }

    @Override
    public List<ReplyVO> getListWithPaging(Criteria cri, Long bno) {
        log.info("GET REPLY LIST..." + cri);
        return replyMapper.getListWithPaging(cri, bno);
    }

    @Override
    public int modify(ReplyVO reply) {
        log.info("MODIFY..." + reply);
        return replyMapper.update(reply);
    }

    @Transactional
    @Override
    public int remove(Long rno) {
        log.info("REMOVE..." + rno);
        ReplyVO reply = replyMapper.read(rno);
        blogMapper.updateReplyCnt(reply.getBno(), -1);
        return replyMapper.delete(rno);
    }

    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno) {
        return new ReplyPageDTO(replyMapper.getCountByBno(bno), replyMapper.getListWithPaging(cri, bno));
    }

//    @Override
//    public Long checkrno() {
//        return replyMapper.checkrno();
//    }
//
//    @Override
//    public int getTotal(Criteria cri) {
//        return replyMapper.getTotalCount(cri);
//    }
//
//    @Override
//    public Long getPrerno(Criteria cri, Long rno) {
//        return replyMapper.getPrerno(cri, rno);
//    }
//
//    @Override
//    public Long getNextrno(Criteria cri, Long rno) {
//        return replyMapper.getNextrno(cri, rno);
//    }

}
