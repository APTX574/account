package com.web.account.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Time: 2021/1/24
 * Description: 核心跨域配置？
 * @author chenyang
 */
@WebFilter(urlPatterns = "/*",filterName = "Filter2")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse rs = (HttpServletResponse) response;

        rs.setHeader("Access-Control-Allow-Origin", "*");

        rs.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");

        rs.setHeader("Access-Control-Max-Age", "3600");

        rs.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
        rs.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
