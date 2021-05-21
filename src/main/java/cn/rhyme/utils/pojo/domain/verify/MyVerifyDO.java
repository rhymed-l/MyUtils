package cn.rhyme.utils.pojo.domain.verify;

import cn.rhyme.utils.verfiy.MyVerifyUtils;

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
