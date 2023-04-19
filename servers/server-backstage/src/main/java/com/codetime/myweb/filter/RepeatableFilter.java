package com.codetime.myweb.filter;

import com.codetime.common.utils.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 */
public class RepeatableFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // json的重复
        if (StringUtils.startsWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            ServletRequest requestWrapper = new RepeatableRequestWrapper((HttpServletRequest) request, response);
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
