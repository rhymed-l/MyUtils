# MyUtils是什么?
这是一个JAVA开发者的工具类,里面涵盖了字符串,日期,文件,图片,网络请求等等,目前还在不断扩展中,工具类已经上传至maven中央仓库,并不断支持更新中.

# 原则
MyUtils遵循最少引用原则,且希望不引入任何其它的依赖包就能直接使用,但是某些方法必须要引用其它的依赖,则使用它的时候必须要引入它.

# 使用方式

直接通过maven中央仓库拉取项目代码,即可使用,当前最新版本maven坐标.

	<!-- https://mvnrepository.com/artifact/cn.rhymed.common/my-utils -->
    <dependency>
        <groupId>cn.rhymed.common</groupId>
        <artifactId>my-utils</artifactId>
        <version>2.0.0-RELEASE</version>
    </dependency>

如果需要查看历史以往记录.请访问下面链接
https://mvnrepository.com/artifact/cn.rhymed.common/my-utils

#Build
```
mvn clean deploy -P sonatype-oss-release
```