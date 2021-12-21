package bstu.kachanova.javaproject.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AspectLogger {
    private Logger log = Logger.getLogger(getClass().toString());

    @After("execution(* bstu.kachanova.javaproject.demo.rest.MainRestController.*(..))")
    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }
}
