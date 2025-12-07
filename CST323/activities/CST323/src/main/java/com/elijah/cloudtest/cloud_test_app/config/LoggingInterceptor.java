package com.elijah.cloudtest.cloud_test_app.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        logger.info("Entering handler: {} at {} | Method={} URI={}",
                handler,
                LocalDateTime.now(),
                request.getMethod(),
                request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.debug("PostHandle for {} {} | Handler={}",
                request.getMethod(),
                request.getRequestURI(),
                handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        if (ex != null) {
            logger.error("Exception in handler: {} at {} - {}",
                    handler,
                    LocalDateTime.now(),
                    ex.getMessage(), ex);
        }
        // getStatus() is available in Servlet 3.0+ (Jakarta EE)
        logger.info("Exiting handler: {} at {} | Status={}",
                handler,
                LocalDateTime.now(),
                response.getStatus());
    }
}