package org.nocturnum.sample.controller;

import lombok.extern.log4j.Log4j;
import org.nocturnum.sample.domain.SampleVO;
import org.nocturnum.sample.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample/rest")
@Log4j
public class SampleRestController {

    /*
     @RequestController : Controller가 REST 방식을 처리하기 위한 것임을 명시
     @ResponseBody : 일반적인 JSP와 같은 뷰로 전달되는게 아니라 데이터 자체를 전달하기 위한 용도
     @PathVariable : URL 경로에 있는 값을 파라미터로 추출하려고 할 때 사용
     @CrossOrigin : Ajax의 크로스 도메인 문제를 해결해주는 어노테이션
     @RequestBody : JSON 데이터를 원하는 타입으로 바인딩 처리
    */


    // http://localhost:8080/SpringMVC/sample/rest/getText
    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText() {
        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
        return "안녕하세요";
    }

    //http://localhost:8080/SpringMVC/sample/rest/getSample
    //http://localhost:8080/SpringMVC/sample/rest/getSample.json
    @GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample() {
        return new SampleVO(11, "Nocturnum", "Lee");
    }

    @GetMapping(value = "/getSample2")
    public SampleVO getSample2() {
        return new SampleVO(12, "Nocturnum2", "Lee2");
    }

    @GetMapping(value = "/getList")
    public List<SampleVO> getList() {
        return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, "First" + i, "Last" + i)).collect(Collectors.toList());
    }

    @GetMapping(value = "/getMap")
    public Map<String, SampleVO> getMap() {
        Map<String, SampleVO> map = new HashMap<>();
        map.put("Key", new SampleVO(111, "Nocturnum", "Lee"));
        return map;
    }

    //http://localhost:8080/SpringMVC/sample/rest/check.json?height=140&weight=60
    //http://localhost:8080/SpringMVC/sample/rest/check.json?height=160&weight=60
    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight) {
        SampleVO vo = new SampleVO(0, "" + height, "" + weight);
        ResponseEntity<SampleVO> result = null;

        if (height < 150) {
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }
        return result;
    }

    //http://localhost:8080/SpringMVC/sample/rest/product/bags/325
    @GetMapping("/product/{category}/{pid}")
    public String[] getPath(@PathVariable("category") String category, @PathVariable("pid") Integer pid) {
        return new String[]{"category: " + category, "productId: " + pid};
    }

    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket){
        log.info("convert... " + ticket);
        return ticket;
    }



}
