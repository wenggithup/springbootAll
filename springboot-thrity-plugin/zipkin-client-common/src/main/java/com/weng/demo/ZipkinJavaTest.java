package com.weng.demo;


import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.propagation.CurrentTraceContext;
import brave.propagation.ThreadLocalCurrentTraceContext;
import com.sun.net.httpserver.Filter;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.io.IOException;

import static brave.Span.Kind.SERVER;
import static java.lang.Thread.sleep;

/**
 * @DATE: 2022/1/10 11:07 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class ZipkinJavaTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // for (int i = 0; i < 500; i++) {


        // 配置reporter，用于控制向zipkin发送span的频率
        //   (the dependency is io.zipkin.reporter2:zipkin-sender-okhttp3)
        Sender sender = OkHttpSender.create("http://192.168.6.84:9411/api/v2/spans");
        //定义reporter，异步发送
        AsyncReporter spanReporter = AsyncReporter.create(sender);

        CurrentTraceContext build = ThreadLocalCurrentTraceContext.newBuilder().build();
        // 创建一个你想在zipkin中看到的服务名称的跟踪组件
        Tracing tracing = Tracing.newBuilder()
                .currentTraceContext(build)
                //展示的服务名称
                .localServiceName("====my-test-service-root====")
                .spanReporter(spanReporter)
                .build();

        // 创建trance 对象
        Tracer tracer = tracing.tracer();

        if (null != tracer.currentSpan()) {

        }

        // 定义span内容
        Span root = tracer.newTrace().name("root-encode")
                .kind(SERVER).tag("POST", "/serviceRoot").start();

        root.finish();

/*        spanReporter.close();
        sender.close();*/
        System.out.println(tracing.currentTraceContext());
        tracing.close();


        Tracing tracingCld = Tracing.newBuilder()
                .localServiceName("====my-test-service-child====")
                .spanReporter(spanReporter)
                .currentTraceContext(build)
                .build();
        Tracer tracerA = tracingCld.tracer();

        System.out.println(tracerA.currentSpan());
        //定义父节点为root span
        Span span = tracerA.newChild(root.context()).kind(SERVER).tag("GET", "/serviceA").name("encode").start();

        //定义父节点为 A pan
        Span span2 = tracerA.newChild(span.context()).kind(SERVER).tag("GET", "/serviceA-A").name("encode-A-A").start();
        //sleep(500);


        span.finish();
        span2.finish();
        sleep(3000);

    }
}

//}
