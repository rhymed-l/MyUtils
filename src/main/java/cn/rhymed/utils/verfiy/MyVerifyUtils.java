package cn.rhymed.utils.verfiy;


import cn.rhymed.utils.MyObjectUtils;
import cn.rhymed.utils.MyStringUtils;
import cn.rhymed.utils.annotation.verify.MyNullVerify;
import cn.rhymed.utils.annotation.verify.MyNumberVerify;
import cn.rhymed.utils.annotation.verify.MyStringVerify;

import java.math.BigDecimal;
import java.util.Optional;

public class MyVerifyUtils {

    public static void verify(Object obj){
        MyObjectUtils.getObjectAllField(obj.getClass()).forEach(field -> {
            field.setAccessible(true);
            Optional.ofNullable(field.getAnnotation(MyNullVerify.class)).ifPresent(verify->{
                try {
                    if(verify.notNull()){
                        if(field.get(obj) == null){
                            String msg = verify.nullMsg();
                            msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s必须不能为空",field.getName()) : msg;
                            throw new RuntimeException(msg);
                        }
                    }
                }catch (IllegalAccessException e){

                }
            });
            Optional.ofNullable(field.getAnnotation(MyNumberVerify.class)).ifPresent(verify->{
                try {
                    Object o = field.get(obj);

                    boolean digits = (o == null ? false : MyStringUtils.isDouble(o.toString()));
                    BigDecimal bigDecimal = new BigDecimal("0");
                    if(digits){
                        bigDecimal = new BigDecimal(o.toString());
                    }
                    if(verify.notEmpty()){
                        if(o == null || bigDecimal.compareTo(new BigDecimal("0")) <= 0){
                            String msg = verify.emptyMsg();
                            msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s必须不能为空或者0",field.getName()) : msg;
                            throw new RuntimeException(msg);
                        }
                    }else if(verify.notNull()){
                        if(o == null){
                            String msg = verify.nullMsg();
                            msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s必须不能为空",field.getName()) : msg;
                            throw new RuntimeException(msg);
                        }
                    }
                    if(o != null){
                        if(verify.digits() && !digits){
                            String msg = verify.digitsMsg();
                            msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s必须不是一个有效的数字",field.getName()) : msg;
                            throw new RuntimeException(msg);
                        }
                        if(bigDecimal.compareTo(new BigDecimal(verify.maxVal())) > 0){
                            String msg = verify.maxMsg();
                            msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s不能大于%s",field.getName(),verify.maxVal()) : msg;
                            throw new RuntimeException(msg);
                        }
                        if(bigDecimal.compareTo(new BigDecimal(verify.minVal())) < 0){
                            String msg = verify.minMsg();
                            msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s不能小于%s",field.getName(),verify.minVal()) : msg;
                            throw new RuntimeException(msg);
                        }
                    }

                }catch (IllegalAccessException e){

                }
            });
            Optional.ofNullable(field.getAnnotation(MyStringVerify.class)).ifPresent(verify->{
                try {
                    Object o = field.get(obj);
                    if(verify.notEmpty()){
                        if(o == null || MyStringUtils.isEmpty(o.toString())){
                            String msg = verify.emptyMsg();
                            msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s必须不能为空",field.getName()) : msg;
                            throw new RuntimeException(msg);
                        }
                    }else if(verify.notNull()){
                        if(o == null){
                            String msg = verify.nullMsg();
                            msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s必须不能为空",field.getName()) : msg;
                            throw new RuntimeException(msg);
                        }
                    }
                    if(o != null){
                        String str = o.toString();
                        if(verify.maxVal() >= 0){
                            if(str.length() > verify.maxVal()){
                                String msg = verify.maxMsg();
                                msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s长度必须不能大于%s",field.getName(),verify.maxVal()) : msg;
                                throw new RuntimeException(msg);
                            }
                        }
                        if(verify.minVal() >= 0){
                            if(str.length() > verify.minVal()){
                                String msg = verify.minMsg();
                                msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s长度必须不能小于%s",field.getName(),verify.minVal()) : msg;
                                throw new RuntimeException(msg);
                            }
                        }
                        if(MyStringUtils.isNotEmpty(verify.regexp())){
                            if(!str.matches(verify.regexp())){
                                String msg = verify.regexpMsg();
                                msg = MyStringUtils.isEmpty(msg) ? String.format("字段%s不能满足正则表达式条件",field.getName()) : msg;
                                throw new RuntimeException(msg);
                            }
                        }
                    }
                }catch (IllegalAccessException e){

                }
            });
            field.setAccessible(false);
        });

    }
}
