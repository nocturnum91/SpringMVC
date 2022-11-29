package org.nocturnum.blog.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.nocturnum.blog.domain.BlogVO;
import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.mapper.BlogMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j
public class BlogServiceImpl implements BlogService {

    private BlogMapper blogMapper;

    @Override
    public void register(BlogVO board) {
        log.info("REGISTER..." + board);
        blogMapper.insertSelectKey(board);
    }

    @Override
    public BlogVO get(Long bno) {
        log.info("GET..." + bno);
        return blogMapper.read(bno);
    }

//    @Override
//    public List<BlogVO> getList() {
//        log.info("GET LIST...");
//        return blogMapper.getList();
//    }

    @Override
    public List<BlogVO> getList(Criteria cri) {
        log.info("GET LIST..." + cri);
        return blogMapper.getListWithPaging(cri);
    }

    @Override
    public boolean modify(BlogVO board) {
        log.info("MODIFY..." + board);
        return blogMapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long bno) {
        log.info("REMOVE..." + bno);
        return blogMapper.delete(bno) == 1;
    }

    @Override
    public Long checkBno() {
        return blogMapper.checkBno();
    }

    @Override
    public int getTotal(Criteria cri) {
        return blogMapper.getTotalCount(cri);
    }

    @Override
    public Long getPreBno(Criteria cri, Long bno) {
        return blogMapper.getPreBno(cri, bno);
    }

    @Override
    public Long getNextBno(Criteria cri, Long bno) {
        return blogMapper.getNextBno(cri, bno);
    }

}
