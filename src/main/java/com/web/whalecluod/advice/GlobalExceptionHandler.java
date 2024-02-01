package com.web.whalecluod.advice;

import com.web.whalecluod.exception.CustomerException;
import com.web.whalecluod.exception.CustomizeErrorCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundError(HttpServletRequest request) {
        return "error";
    }

    @ExceptionHandler(CustomerException.class)
    public ModelAndView handleCustomizeError(HttpServletRequest request, CustomerException ex, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleOtherError(HttpServletRequest request, Exception ex, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", CustomizeErrorCode.SYS_ERROR);
        mav.setViewName("error");
        return mav;
    }
}
