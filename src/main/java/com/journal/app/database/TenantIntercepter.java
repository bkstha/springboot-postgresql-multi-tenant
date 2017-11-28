//package com.journal.app.database;
//
//import com.journal.app.security.JwtTokenUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class TenantIntercepter extends HandlerInterceptorAdapter {
//
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Value("${jwt.header}")
//    private String tokenHeader;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        System.out.println("#############################################");
//        System.out.println(this.tokenHeader);
//        String authToken = request.getHeader(this.tokenHeader);
//        System.out.println(authToken);
//        String tenantId = jwtTokenUtil.getUsernameFromToken(authToken);
//        System.out.println(tenantId);
//        TenantContext.setCurrentTenant(tenantId);
//        return true;
//    }
//
//    @Override
//    public void postHandle(
//            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
//            throws Exception {
//        TenantContext.clear();
//    }
//
//}
