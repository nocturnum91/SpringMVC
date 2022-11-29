package org.nocturnum.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.nocturnum.blog.domain.BlogVO;
import org.nocturnum.blog.domain.Criteria;

import java.util.List;

public interface BlogMapper {

    void insert(BlogVO vo);

    void insertSelectKey(BlogVO vo);

    BlogVO read(Long bno);

    List<BlogVO> getList();

    List<BlogVO> getListWithPaging(@Param("cri") Criteria cri);

    int update(BlogVO vo);

    int delete(Long bno);

    Long checkBno();

    Long getPreBno(@Param("cri") Criteria cri, @Param("bno") Long bno);

    Long getNextBno(@Param("cri") Criteria cri, @Param("bno") Long bno);

    int getTotalCount(Criteria cri);

}
