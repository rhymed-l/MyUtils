package cn.rhymed.utils.pojo.domain.verify;

import cn.rhymed.utils.verfiy.MyVerifyUtils;

/**
 * 实体参数校验类
 */
public interface MyVerifyDO
{
    /** 校验参数 **/
    default void verify(){
        MyVerifyUtils.verify(this);
    }
}
