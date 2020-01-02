package nju.software.baseframework.configuration;

import nju.software.baseframework.data.dataobject.Yhb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * 拦截器
 */
@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter {
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/static/**");
        addInterceptor.excludePathPatterns("/Files/**");
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/getLogin");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/show");
        addInterceptor.excludePathPatterns("/getScreenStatus");
        addInterceptor.excludePathPatterns("/showggList");
        addInterceptor.excludePathPatterns("/UpdateScreenStatus");
        addInterceptor.excludePathPatterns("/TransYyxx");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            String url = "";
            Yhb yh = (Yhb) session.getAttribute(SESSION_KEY);
            String tips = (String) session.getAttribute("msg");
            String fydm = (String)session.getAttribute("fydm");
            if (yh != null) return true;
            else {
                if (tips != null) {
                    url = "/getLogin?msg=" + URLEncoder.encode(tips, "UTF-8");
                } else {
                    url = "/getLogin";
                    if(!session.isNew()){
                        if(fydm==null){
                            tips = "您已长时间未活动，请重新登录！";
                            url = "/getLogin?msg=" + URLEncoder.encode(tips, "UTF-8");
                        }
                    }
                }
            }

            // 跳转登录
            response.sendRedirect(url);
            return false;
        }
    }

}
