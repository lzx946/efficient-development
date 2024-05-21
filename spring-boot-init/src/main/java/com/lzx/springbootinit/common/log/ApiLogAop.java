package com.lzx.springbootinit.common.log;

import com.lzx.springbootinit.common.constants.SysConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 接口请求响应日志 AOP
 */
@Slf4j
@Aspect
@Component
public class ApiLogAop {

    /**
     * 执行拦截
     */
    @Around("execution(* com.lzx.springbootinit.*.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        // 计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 获取请求路径
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 请求唯一 id
        String traceId = MDC.get(SysConstants.TRACE_ID_NAME);
        String url = request.getRequestURI();
        // 获取请求参数
        Object[] args = point.getArgs();
        String reqParam = "[" + StringUtils.join(args, ", ") + "]";
        // 输出请求日志
        log.info("request start，id: {}, path: {}, ip: {}, params: {}",
                traceId, url, request.getRemoteHost(), reqParam);
        // 执行原方法
        Object result = point.proceed();
        // 输出响应日志
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        log.info("request end, id: {}, cost: {}ms", traceId, totalTimeMillis);
        return result;
    }

}
