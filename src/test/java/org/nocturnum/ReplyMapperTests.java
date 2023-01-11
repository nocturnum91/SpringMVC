package org.nocturnum;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.domain.ReplyVO;
import org.nocturnum.blog.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class ReplyMapperTests {

    private final Long[] bnoArr = {2424819L, 2424817L, 2424816L, 2424815L, 2424814L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    @Test
    public void testMapper() {
        log.info(replyMapper);
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO vo = new ReplyVO();
            vo.setBno(bnoArr[i % 5]);
            vo.setReply("댓글 테스트 " + i);
            vo.setReplier("Nocturnum " + i);

            replyMapper.insert(vo);
        });
    }

    @Test
    public void testRead() {
        Long targetRno = 7L;
        ReplyVO vo = replyMapper.read(targetRno);

        log.info(vo);
    }

    @Test
    public void testList() {
        Criteria cri = new Criteria();
        List<ReplyVO> replyVOList = replyMapper.getListWithPaging(cri, 2424817L);

        replyVOList.forEach(log::info);
    }

    @Test
    public void testUpdate() {
        Long targetRno = 7L;
        ReplyVO vo = replyMapper.read(targetRno);
        vo.setReply("Update Reply");
        int count = replyMapper.update(vo);

        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testDelete() {
        Long targetRno = 7L;
        replyMapper.delete(targetRno);
    }

}
