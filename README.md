# some demo for verification



### dockerfile编排
具体命令见Dockfile

##### 遇到的问题
1. 拷贝路径问题
> 拷贝jar包至镜像中,jar在项目的target目录中,此处使用相对路径.
> 拷贝target目录下的jar包至 docker工作目录下的 application.jar，
> 这里也是要启动application.jar的

```
ADD ${JAR_FILE}/target/${JAR_NAME} /${WORK_DIR}/application.jar
```
2. 镜像无法启动问题

如果配置了数据库等第三方需要ip的软件，则需要配置公网地址或者将宿主机与容器之间的通信；

此处不能配置localhost： 除非三方软件就在编排目录中，如果/src/nginx等；

这里的localhost是容器内部的地址，所以无法访问到宿主机或者其他容器的地址；
