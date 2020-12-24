package com.baidu.shop.global;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.status.HTTPStatus;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName GlobalException
 * @Description: TODO
 * @Author wangyanjun
 * @Date 2020/12/24
 * @Version V1.0
 **/
@RestControllerAdvice //增强restController // springapo通知-->advice 代理
@Slf4j
public class GlobalException {

    @ExceptionHandler(value = RuntimeException.class)
    public Result<JSONObject> testException(RuntimeException  e){

        log.error("code : {} , message :{}", HTTPStatus.ERROR,e.getMessage()); // 打印日志

        return new Result<JSONObject>(HTTPStatus.ERROR,e.getMessage(),null);
    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public List<Result<JsonObject>> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) throws Exception {
//        List<Result<JsonObject>> objects = new ArrayList<>();
//        //按需重新封装需要返回的错误信息
//
//        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
//            Result<JsonObject> jsonObjectResult = new Result<>();
//            jsonObjectResult.setCode(HTTPStatus.PARAMS_VALIDATE_ERROR);
//            jsonObjectResult.setMessage("Field --> " + error.getField() + " : " + error.getDefaultMessage());
//            log.error("Field --> " + error.getField() + " : " + error.getDefaultMessage());
//            objects.add(jsonObjectResult);
//        }
//
//        return objects;
//    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Map<String,String> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) throws Exception {
//        //按需重新封装需要返回的错误信息
//        HashMap<String, String> map = new HashMap<>();
//
//        map.put("code",HTTPStatus.PARAMS_VALIDATE_ERROR + "");
//
//        String message = "";
//
//        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
//            message +="Field --> " + error.getField() + " : " + error.getDefaultMessage();
//            log.error("Field --> " + error.getField() + " : " + error.getDefaultMessage());
//        }
//
//        map.put("message",message);
//        return map;
//    }


//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Map<String,String> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) throws Exception {
//        //按需重新封装需要返回的错误信息
//        HashMap<String, String> map = new HashMap<>();
//
//        map.put("code",HTTPStatus.PARAMS_VALIDATE_ERROR + "");
//
//        String message = "";
//
//        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
//            message +="Field --> " + error.getField() + " : " + error.getDefaultMessage();
//            log.error("Field --> " + error.getField() + " : " + error.getDefaultMessage());
//        }
//
//
//        map.put("message",message);
//        return map;
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String,String> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) throws Exception {
        //按需重新封装需要返回的错误信息
        HashMap<String, String> map = new HashMap<>();

        map.put("code",HTTPStatus.PARAMS_VALIDATE_ERROR + "");

        List<String> msgList = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            msgList.add("Field --> " + error.getField() + " : " + error.getDefaultMessage());
          ///  System.out.println(1111111111);
            log.error("Field --> " + error.getField() + " : " + error.getDefaultMessage());
            //System.out.println(2222222);
        });
        String message = msgList.stream().collect(Collectors.joining());
        //System.out.println(333333);
        map.put("message",message);
       // System.out.println(4444444);
        return map;
    }
}
