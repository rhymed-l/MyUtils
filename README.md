# MyUtils是什么?
这是一个JAVA开发者的工具类,里面涵盖了字符串,日期,文件,图片,网络请求等等,目前还在不断扩展中,工具类已经上传至maven中央仓库,并不断支持更新中.

# 使用方式

直接通过maven中央仓库拉取项目代码,即可使用,当前最新版本maven坐标.

	<!-- https://mvnrepository.com/artifact/cn.rhymed.common/my-utils -->
    <dependency>
        <groupId>cn.rhymed.common</groupId>
        <artifactId>my-utils</artifactId>
        <version>2.0.0-RELEASE</version>
    </dependency>



如果需要查看历史以往记录.请访问下面链接
https://mvnrepository.com/artifact/ltd.liuzhi.rhyme/my_utils

#Build
```
mvn clean deploy -P sonatype-oss-release
```