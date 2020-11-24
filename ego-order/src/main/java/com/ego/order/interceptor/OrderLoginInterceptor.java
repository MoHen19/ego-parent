package com.ego.order.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.pojo.Admin;
import com.ego.common.util.CookieUtil;
import com.ego.common.util.JsonUtil;
import com.ego.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 订单系统登录拦截器
 *
 * @author zhoubin
 * @create 2020/1/2
 * @since 1.0.0
 */
@Component
public class OrderLoginInterceptor implements HandlerInterceptor {

    @Reference(interfaceClass = SSOService.class)
    private SSOService ssoService;

    @Value("${user.ticket}")
    private String userTicket;

    @Value("${ego.portal.url}")
    private String portalUrl;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 请求处理之前的拦截器，false直接返回，true继续执行下一个拦截器或者目标程序
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获取票据信息
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        //如果存在票据，根据票据信息从单点登录系统里获取用户信息
        if (!StringUtils.isEmpty(ticket)) {
            Admin admin = ssoService.validate(ticket);
            //如果用户信息不为空
            if (null != admin) {
                //将用户信息放入session中，用户页面显示
                request.getSession().setAttribute("user", admin);
                //重新设置redis的失效时间
                ValueOperations<String, String> stringObjectValueOperations = redisTemplate.opsForValue();
                stringObjectValueOperations.set(userTicket + ":" + ticket, JsonUtil.object2JsonStr(admin), 30,TimeUnit.MINUTES);
                //放行
                return true;
            }
        }
        //如果票据信息为空，跳转至登录页面(重定向到前台门户系统去登录)
        response.sendRedirect(portalUrl+"login?redirectUrl="+request.getRequestURL());
        return false;
    }

    /**
     * 请求处理之后的拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 处理后执行清理工作
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

    }
}