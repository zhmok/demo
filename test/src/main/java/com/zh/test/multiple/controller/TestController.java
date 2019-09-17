//package com.zh.test.controller;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.async.DeferredResult;
//
//import java.util.concurrent.Callable;
//
////@Log4j2
////@Log4j
//@Slf4j
////@CommonsLog
//@RestController
//public class TestController {
//    @GetMapping("/test")
//    public Callable<String> test() throws InterruptedException {
//        log.info("start p");
//        Callable<String> callable = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                LoggerFactory.getLogger(TestController.class).info("start");
////        log.info("test");
//                System.out.println(log);
//                Thread.sleep(1000);
//                LoggerFactory.getLogger(TestController.class).info("end");
//                return "aaa";
//            }
//        };
//        log.info("start end");
//        return callable;
//    }
//
//    @GetMapping("/test2")
//    public DeferredResult<String> test2() throws InterruptedException {
//        log.info("start p");
//        DeferredResult<String> result = new DeferredResult<String>();
//
//
//        result.onCompletion(() -> {
//            LoggerFactory.getLogger(TestController.class).info("start");
////        log.info("test");
//            System.out.println(log);
//
//            LoggerFactory.getLogger(TestController.class).info("end");
//        });
//
//
//        log.info("start end");
//        new Thread(() -> {
//            log.info("start bus");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            result.setResult("aasdasd ");
//
//            log.info("start bus");
//        }
//
//        ).start();
//        return result;
//    }
//
//    @RequestMapping(value = "/test2", method = RequestMethod.HEAD)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public String test3() {
//        log.info("11");
//
//        return "11";
//    }
//
//}
