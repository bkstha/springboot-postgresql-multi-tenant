package com.journal.app.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class CORSFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(CORSFilter.class);
    private static final List<String> ALLOWED_ORIGINS = Arrays.asList("http://localhost:4200", "http://localhost:4300");

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Initializing CORSFilter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String incomingOrigin = request.getHeader("Origin");
        if (ALLOWED_ORIGINS.contains(incomingOrigin)) {
            System.out.println("setting header: " + incomingOrigin);
            //url to allow
            response.setHeader("Access-Control-Allow-Origin", incomingOrigin);
            //request method to allow GET, POST etc
            response.setHeader("Access-Control-Allow-Methods", "*");
            // request headers type: Origin, Content-Type Accept, x-Auth-Token, csrf-Token etc
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Credentials", "false");
            response.setHeader("Access-Control-Max=Age", "3600");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
