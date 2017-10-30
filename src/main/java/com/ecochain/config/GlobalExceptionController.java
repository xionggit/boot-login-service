package com.ecochain.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;

/** 
 * @Description: 统一异常处理
 * @author: xiong  
 * @GitConfig name: xionggit  email: shao200815@163.com
 * @date: 2017-10-30 10:56:34 
 */
@RestController
@ControllerAdvice
public class GlobalExceptionController {

    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private CoreMessageSource messageSource;
    
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto<String> handleIOException(IOException e) {
        log.info("服务器io异常: " + e.getMessage(),e);

        return ResultDto.processError(messageSource.getMessage("ioException"));

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto<String> handleServiceException(RuntimeException e) {
        
        log.info("服务器RuntimeException: " + e.getMessage(),e);

        return ResultDto.processError(messageSource.getMessage("runTimeException"));

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto<String> handleException(Exception e) {
        
        log.info("服务器Exception: " + e.getMessage(),e);

        return ResultDto.processError(messageSource.getMessage("exception"));

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto<String> handleException(MissingServletRequestParameterException e) {
        
        log.info("服务器MissingServletRequestParameterException: " + e.getMessage(),e);

        return ResultDto.processError(messageSource.getMessage("exception"));
    }
    
//    /**  
//     * 权限异常
//     * @Title handleException  
//     * @param e
//     * @return ResultDto<String>   
//     */  
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ResultDto<String> handleException(AccessDeniedException e) {
//        
//        log.info("服务器AccessDeniedException: " + e.getMessage(),e);
//        
//        return ResultDto.processError(e.getMessage());
//    }
//    
//    /**
//     * 认证异常
//     * @Title handleException  
//     * @param e
//     * @return ResultDto<String>
//     */
//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ResultDto<String> handleException(AuthenticationException e) {
//        
//        log.info("服务器AuthenticationException: " + e.getMessage(),e);
//        
//        return ResultDto.processError(e.getMessage());
//    }

    /**  
     * hibernate 参数检查异常
     * @Title processValidationError  
     * @param e
     * @return ResultDto<String>   
     */  
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto<String> processValidationError(MethodArgumentNotValidException e) {
        
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult != null && bindingResult.hasErrors()) {
            for(FieldError error : bindingResult.getFieldErrors()){
                //当多个验证同时成立时
                StringBuilder errorMsg = new StringBuilder();
                for (FieldError er : bindingResult.getFieldErrors(error.getField())) {
                    errorMsg.append(er.getDefaultMessage()+"，");
                }
                
                log.info("服务器MethodArgumentNotValidException: " 
                        + errorMsg.deleteCharAt(errorMsg.length() -1).toString());
                
                if(errorMsg.toString().endsWith("，")){
                    errorMsg.deleteCharAt(errorMsg.length() -1).toString();
                }
                
                return ResultDto.processError(errorMsg.toString());
            }
        }
        log.info("服务器MethodArgumentNotValidException: " 
                + e.getMessage(), e);
        return ResultDto.processError(e.getMessage());
    }
    
    /**
    * 上传文件异常，例如超过上传文件大小限制。 
    * @Title handleMultipartException  
    * @param e
    * @return ResultDto<String>
     */
    @ExceptionHandler(value = {MultipartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto<String> handleMultipartException(MultipartException e) {
        
        log.info("服务器MultipartException: " 
                + e.getMessage(), e );
        
        return ResultDto.processError(messageSource.getMessage("uploadException"));
    }

//    /* 未获取session访问后台url */
//    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
//    public ResultDto<String> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e) {
//        e.printStackTrace();
//        return new ResultDto<>("4300", "session过期或您的账号已在其他地方登录，请及时修改密码");
//
//    }
    

}
