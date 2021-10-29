//package cn.rhymed.utils.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * 用来自动缓存的切面
// * @author LiuZhi
// * @Date 2020-10-23 13:56
// * @Version V1.0
// */
//@Aspect
//@Component
//public class AutoCacheAspect {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private final RedisUtils redisUtils;
//
//    public AutoCacheAspect(RedisUtils redisUtils) {
//        this.redisUtils = redisUtils;
//    }
//
//    @Around("@annotation(cn.com.connext.qywechat.core.annotation.AutoCache)")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        List<Object> args = null;
//        if(point.getArgs() != null){
//             args = Arrays.asList(point.getArgs());
//        }
//
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = signature.getMethod();
//        AutoCache autoCache = method.getAnnotation(AutoCache.class);
//        String cacheKey = autoCache.key();
//
//        StringBuffer stringBuffer;
//        if(MyStringUtils.isEmpty(cacheKey)){
//            //生成key
//            stringBuffer = new StringBuffer(point.getTarget().getClass().toString()).append(":").append(method.getName()).append(":");
//            if(args != null){
//                for(Object object : args){
//                    stringBuffer.append(object.toString()).append("_");
//                }
//            }
//        }else {
//            stringBuffer = new StringBuffer(cacheKey);
//            for(Object object : args){
//                stringBuffer.append(object.toString()).append("_");
//            }
//        }
//        cacheKey = stringBuffer.toString();
//
//        if(redisUtils.exists(cacheKey)){
//            return redisUtils.getAutoCache(cacheKey, method.getReturnType());
//        }
//        Object result = point.proceed();
//        if(result != null){
//            redisUtils.setAutoCache(cacheKey,result,autoCache.expired(),autoCache.timeUnit());
//        }
//        return result;
//    }
//}
//
