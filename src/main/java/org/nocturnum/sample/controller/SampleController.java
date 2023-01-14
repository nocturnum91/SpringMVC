package org.nocturnum.sample.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.nocturnum.sample.domain.SampleDTO;
import org.nocturnum.sample.domain.SampleDTOList;
import org.nocturnum.sample.service.SampleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/sample/*")
@AllArgsConstructor
@Log4j
public class SampleController {

    /*
     Return Type
     String: jsp 파일의 경로와 파일 이름
     void: 호출하는 URL과 동일한 이름의 jsp
     vo, DTO: 주로 JSON 타입의 데이터를 만들어 반환
     ResponseEntity: response 할 때 Http Header 정보와 내용을 가공하는 용도
     HttpHeaders: 응답에 내용 없이 Http Header 메시지만 전달하는 용도
    */

    private SampleService sampleService;

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    public void get() {
        log.info("#################### GET MAPPING");
    }

    @GetMapping("/get2")
    public void get2() {
        log.info("#################### ONLY GET MAPPING");
    }

    // http://localhost:8080/SpringMVC/sample/getDTO?id=1&name=sample&age=10
    @RequestMapping(value = "/getDTO", method = {RequestMethod.GET})
    public void getDTO(SampleDTO dto) {
        log.info("#################### GET DTO : " + dto);
    }

    // http://localhost:8080/SpringMVC/sample/getDTO2?id=1&name=sample&age=10
    @RequestMapping(value = "/getDTO2", method = {RequestMethod.GET})
    public void getDTO2(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("age") int age) {
        log.info("#################### GET DTO");
        log.info("#################### id : " + id);
        log.info("#################### name : " + name);
        log.info("#################### age : " + age);
    }

    // http://localhost:8080/SpringMVC/sample/getList?ids=111&ids=222
    @RequestMapping(value = "/getList", method = {RequestMethod.GET})
    public void getList(@RequestParam("ids") ArrayList<String> ids) {
        log.info("#################### GET LIST : " + ids);
    }

    // http://localhost:8080/SpringMVC/sample/getArray?ids=111&ids=222
    @RequestMapping(value = "/getArray", method = {RequestMethod.GET})
    public void getArray(@RequestParam("ids") String[] ids) {
        log.info("#################### GET ARRAY : " + Arrays.toString(ids));
    }


    // http://localhost:8080/SpringMVC/sample/getDTOList?list%5B0%5D.id=aaa&list%5B1%5D.id=bbb
    @RequestMapping(value = "/getDTOList", method = {RequestMethod.GET})
    public void getDTOList(SampleDTOList list) {
        log.info("#################### GET DTO LIST : " + list);
    }


    /* DTO에서 @DateTimeFormat 사용하는 방법도 있음 */
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//    }

    // http://localhost:8080/SpringMVC/sample/getDate?date=2022-11-18
    @RequestMapping(value = "/getDate", method = {RequestMethod.GET})
    public void getDate(SampleDTO dto) {
        log.info("#################### GET DTO LIST : " + dto);
    }

    //http://localhost:8080/SpringMVC/sample/sendModel?id=1&name=sample&age=10&date=2022-11-18&page=3
    @RequestMapping(value = "/sendModel", method = {RequestMethod.GET})
    public String sendModel(SampleDTO dto, @ModelAttribute("page") int page, Model model) {
        log.info("#################### SEND MODEL");
        log.info("#################### DTO : " + dto);
        log.info("#################### PAGE : " + page);
        String dateTime = sampleService.getTime();
        log.info("#################### TIME : " + dateTime);
        model.addAttribute("time", dateTime);
        return "/sample/sample";
    }

    @RequestMapping(value = "/redirect", method = {RequestMethod.GET})
    public String redirect(RedirectAttributes rttr){
        log.info("#################### REDIRECT");
        rttr.addFlashAttribute("page", "3");

        return "redirect:/sample/sendModel";
    }

    @RequestMapping(value = "/sendObject", method = {RequestMethod.GET})
    public @ResponseBody SampleDTO sendObject(){
        log.info("#################### SEND OBJECT");
        SampleDTO dto = new SampleDTO();
        dto.setId("1");
        dto.setName("sample");
        dto.setAge(3);
        dto.setDate(new Date());
        log.info(dto);

        return dto;
    }

    @RequestMapping(value = "/sendResponseEntity", method = {RequestMethod.GET})
    public ResponseEntity<String> sendResponseEntity(){
        log.info("#################### SEND RESPONSE ENTITY");

        String rtnMsg = "{\"name\": \"sample\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(rtnMsg, headers, HttpStatus.OK);
    }

}
