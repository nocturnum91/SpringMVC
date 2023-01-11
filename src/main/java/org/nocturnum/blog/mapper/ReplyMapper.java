package org.nocturnum.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.domain.ReplyVO;

import java.util.List;

public interface ReplyMapper {

    int insert(ReplyVO vo);

    ReplyVO read(Long rno); // 특정 댓글 읽기

    List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);

    int update(ReplyVO vo);

    int delete(Long rno);

    int getCountByBno(Long bno);


}
