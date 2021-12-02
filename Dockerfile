# 功能为指定基础镜像，并且必须是第一条指令。
# 如果不以任何镜像为基础，那么写法为：FROM scratch。这里指定jkd
FROM azul/zulu-openjdk-alpine:8-jre

# 设置变量
# 在docker build创建镜像的时候，使用 --build-arg <varname>=<value>来指定参数
# 如果用户在build镜像时指定了一个参数没有定义在Dockerfile种，那么将有一个Warning
ARG WORK_DIR="app"


# 运行指定的命令,RUN <command> 后边直接跟shell命令;RUN ["executable", "param1", "param2"]  类似于函数调用。
# 在根目录下放置日志文件夹 /app/logs
RUN mkdir /${WORK_DIR} && mkdir /${WORK_DIR}/logs


# 设置工作目录  设置工作目录，对RUN,CMD,ENTRYPOINT,COPY,ADD生效。如果不存在则会创建，也可以设置多次。

WORKDIR /${WORK_DIR}

# 配置字体和时区 docker 镜像中的时区非国内时区，更改为上海时区  RUN书写时的换行符是\
RUN echo "http://mirrors.aliyun.com/alpine/v3.6/main" > /etc/apk/repositories \
    && echo "http://mirrors.aliyun.com/alpine/v3.6/community" >> /etc/apk/repositories \
    && apk update upgrade \
    && apk add --no-cache procps unzip curl bash tzdata \
    && apk add ttf-dejavu \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

# 此变量在--build-arg <varname>=<value>指定,此处指定为模块名称
ARG JAR_FILE

ARG JAR_VERSION="1.0-SNAPSHOT.jar"

# 拼接jar包名称
ENV JAR_NAME=${JAR_FILE}-${JAR_VERSION}

# 拷贝jar包至镜像中,jar在项目的target目录中,此处使用相对路径,拷贝目录下的jar包
ADD ${JAR_FILE}/target/${JAR_NAME} /${WORK_DIR}/application.jar


# 功能为设置环境变量
# 普通参数，如指定启动环境等
ENV PARAMS=""
# JVM参数
ENV JAVA_OPTS=""
# 容器执行的命令
ENTRYPOINT ["sh", "-c", "java -jar $JAVA_OPTS application.jar $PARAMS"]


