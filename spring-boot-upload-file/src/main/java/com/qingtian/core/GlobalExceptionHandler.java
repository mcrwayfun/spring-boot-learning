package com.qingtian.core;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 全局异常处理器
 * @date Created in 2018/7/31
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获上传文件过大异常
     * @param e
     * @param redirectAttributes
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public String handleUpLoadFileError(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }

    /**
     * 常见异常捕获
     * @param e
     * @param redirectAttributes
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }
}
