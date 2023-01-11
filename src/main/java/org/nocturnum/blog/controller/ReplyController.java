package org.nocturnum.blog.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.domain.ReplyPageDTO;
import org.nocturnum.blog.domain.ReplyVO;
import org.nocturnum.blog.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/replies/")
@AllArgsConstructor
@Log4j
public class ReplyController {

    private ReplyService replyService;

    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + " /new... " + replyVO);

        int insertCount = replyService.register(replyVO);
        log.info("REPLY INSERT COUNT: " + insertCount);

        return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + " get... " + rno);

        return new ResponseEntity<>(replyService.get(rno), HttpStatus.OK);
    }

    @GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + " /pages/" + bno + "/" + page);

        Criteria cri = new Criteria();

        if(page == -1) {
            cri.setPageNum(0);
            cri.setAmount(0);
        } else {
            cri.setPageNum(page);
            cri.setAmount(10);
        }


        return new ResponseEntity<>(replyService.getListPage(cri, bno), HttpStatus.OK);
    }

    @RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH}, consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + " modify... " + rno + " / " + vo);

        vo.setRno(rno);

        return replyService.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + " remove... " + rno);

        return replyService.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
