package org.nocturnum.blog.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.nocturnum.blog.domain.BlogVO;
import org.nocturnum.blog.domain.Criteria;
import org.nocturnum.blog.domain.PageDTO;
import org.nocturnum.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/blog/*")
@AllArgsConstructor
@Log4j
public class BlogController {

    private BlogService blogService;

    @GetMapping("/posting")
    public void posting(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + "/posting");
    }

    @PostMapping("/posting")
    public String posting(BlogVO blogVO, RedirectAttributes rttr, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + "/posting " + blogVO);
        blogService.register(blogVO);
        rttr.addFlashAttribute("result", blogVO.getBno());
        return "redirect:/blog/home";
    }

    @GetMapping("/post")
    public void post(@RequestParam(value = "bno", required = false) Long bno, @ModelAttribute("cri") Criteria cri, Model model, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + "/post");

        if (bno == null) {
            bno = blogService.checkBno();
        }

        model.addAttribute("blog", blogService.get(bno));
        model.addAttribute("preBno", blogService.getPreBno(cri, bno));
        model.addAttribute("nextBno", blogService.getNextBno(cri, bno));

    }

    @GetMapping("/home")
    public void home(Criteria cri, Model model, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + "/home" + cri);
//        model.addAttribute("list", blogService.getList());

        int total = blogService.getTotal(cri);
        model.addAttribute("list", blogService.getList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

    @GetMapping("/modify")
    public void modify(@RequestParam(value = "bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + "/modify " + bno);
        model.addAttribute("blog", blogService.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BlogVO blogVO, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + "/modify " + blogVO);
        if (blogService.modify(blogVO)) {
            rttr.addFlashAttribute("method", "modify");
            rttr.addFlashAttribute("result", "success");
        }

        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/blog/home";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        log.info("ipAddress: " + ipAddress + "/remove" + bno);
        if (blogService.remove(bno)) {
            rttr.addFlashAttribute("method", "remove");
            rttr.addFlashAttribute("result", "success");
        }

        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/blog/home";
    }


}
