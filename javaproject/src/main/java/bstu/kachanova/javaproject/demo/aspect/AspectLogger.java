package bstu.kachanova.javaproject.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
//Идея АОП заключается в выделении так называемой сквозной функциональности

@Aspect
@Component
public class AspectLogger {
    //логирование
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AspectLogger.class);

    //сопоставления точе соединения
    //Средства для соответствия всем методам в пакете
    @Pointcut("execution(* bstu.kachanova.javaproject.demo.rest.MainRestController.*(..))")
    public void calledAtMainREstController(){}

    //запускается после всех методов в pointcut
    @After("calledAtMainREstController()")
    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* bstu.kachanova.javaproject.demo.rest.BookRestController.*(..))")
    public void calledAtScooterRestController(){}

    @After("calledAtScooterRestController()")
    public void log2(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }

    @Pointcut("execution(* bstu.kachanova.javaproject.demo.rest.UserRentRestController.*(..))")
    public void calledAtUserRentRestController(){}

    @After("calledAtUserRentRestController()")
    public void log3(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }
}
