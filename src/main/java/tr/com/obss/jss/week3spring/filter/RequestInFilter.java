package tr.com.obss.jss.week3spring.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestInFilter extends CommonsRequestLoggingFilter {

    private static final Logger LOGGLER = LoggerFactory.getLogger(RequestInFilter.class);

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        String pathInfo = request.getRequestURI();
        return pathInfo.contains("/api/");
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        LOGGLER.info("request Filter basladi {} {}", request.getRequestURI(), request.getMethod());
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        LOGGLER.info("request Filter bitti {} {}", request.getRequestURI(), request.getMethod());
    }
}
