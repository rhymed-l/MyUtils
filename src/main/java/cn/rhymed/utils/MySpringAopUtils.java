package cn.rhymed.utils;


import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * Spring的工具包(需要引入spring才能使用)
 * @author LiuZhi
 * @Date 2020-05-15 11:21
 * @Version V1.0
 */
public class MySpringAopUtils
{
    /**
     * 获取代理对象的真实对象
     * @param proxy 代理对象
     * @return 返回一个真实对象
     */
    public static Object getProxyTargetObject(Object proxy)
    {
        if (!AopUtils.isAopProxy(proxy))
        { //判断是否是代理类
            return proxy;
        }
        try {
            if (AopUtils.isJdkDynamicProxy(proxy))
            {//如果是jdk动态代理的
                Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
                h.setAccessible(true);
                AopProxy aopProxy = (AopProxy) h.get(proxy);
                Field advised = aopProxy.getClass().getDeclaredField("advised");
                advised.setAccessible(true);
                return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
            } else
            { //cglib
                Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
                h.setAccessible(true);
                Object dynamicAdvisedInterceptor = h.get(proxy);
                Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
                advised.setAccessible(true);
                return ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
