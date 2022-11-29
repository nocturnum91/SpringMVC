package org.nocturnum.blog.service;

import org.nocturnum.blog.domain.BlogVO;
import org.nocturnum.blog.domain.Criteria;

import java.util.List;

public interface BlogService {

    void register(BlogVO board);

    BlogVO get(Long bno);

//    List<BlogVO> getList();

    List<BlogVO> getList(Criteria cri);

    boolean modify(BlogVO board);

    boolean remove(Long bno);

    Long checkBno();

    int getTotal(Criteria cri);

    Long getPreBno(Criteria cri, Long bno);

    Long getNextBno(Criteria cri, Long bno);

}
