package org.nocturnum.sample.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {

    //http://localhost:8080/SpringMVC/sample/sendModel?id=1&name=test&age=a&date=2022-11-02&page=3
    @ExceptionHandler(Exception.class)
    public String except(Exception e, Model model) {
        log.error("Exception: " + e.getMessage());
        model.addAttribute("exception", e);
        log.error(model);
        return "error_page";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException e) {
        log.error("Exception: " + e.getMessage());
        return "custom404";
    }

}
