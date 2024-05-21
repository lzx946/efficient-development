package com.lzx.springbootinit.common.trace;

import com.lzx.springbootinit.common.constants.SysConstants;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class ApiTraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 生成请求唯一 id
        String traceId = UUID.randomUUID().toString();
        log.info("生成 X-TRACE-ID: {}", traceId);
        MDC.put(SysConstants.TRACE_ID_NAME, traceId);

        // 继续执行
        filterChain.doFilter(servletRequest, servletResponse);

        MDC.remove(SysConstants.TRACE_ID_NAME);
        log.info("清除 X-TRACE-ID: {}", traceId);
    }
}
