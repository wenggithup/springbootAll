package com.weng.demo;


import brave.Span;
import brave.Tracer;
import brave.Tracing;
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
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // 配置reporter，用于控制向zipkin发送span的频率
        //   (the dependency is io.zipkin.reporter2:zipkin-sender-okhttp3)
        Sender sender = OkHttpSender.create("http://192.168.6.84:9411/api/v2/spans");
        AsyncReporter spanReporter = AsyncReporter.create(sender);

        // 创建一个你想在zipkin中看到的服务名称的跟踪组件
        Tracing tracing = Tracing.newBuilder()
                .localServiceName("my-test-service")
                .spanReporter(spanReporter)
                .build();

        // 跟踪公开的可能需要的对象，最重要的时跟踪
        Tracer tracer = tracing.tracer();

        // Failing to close resources can result in dropped spans! When tracing is no
        // longer needed, close the components you made in reverse order. This might be
        // a shutdown hook for some users.
        Span root = tracer.newTrace().name("root-encode")
                .kind(SERVER).tag("POST","/serviceRoot").start();

        Tracing tracingCld = Tracing.newBuilder()
                .localServiceName("my-test-service-A")
                .spanReporter(spanReporter)
                .build();
        Tracer tracerA = tracingCld.tracer();

        Span span = tracerA.newChild(root.context()).kind(SERVER).tag("GET","/serviceA").name("encode").start();
        sleep(500);

        Span span2 = tracerA.newChild(span.context()).kind(SERVER).tag("GET","/serviceA-A").name("encode-A-A").start();
        //sleep(500);
        root.finish();
        span.finish();
        span2.finish();
        tracingCld.close();

        tracing.close();

        spanReporter.close();
        sender.close();
    }

}
