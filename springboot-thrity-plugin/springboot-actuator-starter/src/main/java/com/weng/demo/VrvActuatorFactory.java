package com.weng.demo;

import com.alibaba.fastjson.JSON;
import com.vrv.rpc.dubbo.VrvRpcDubboFactory;
import com.vrv.rpc.dubbo.config.VrvConfig;
import com.weng.demo.bean.EndpointsProperties;
import com.weng.demo.bean.RegistryProperties;
import com.weng.demo.bean.VrvActuatorProperties;
import com.weng.demo.bean.ZookeeperProperties;
import com.weng.demo.util.ZookeeperUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.common.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @DATE: 2022/5/11 3:33 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class VrvActuatorFactory {
    private final static Logger logger = LoggerFactory.getLogger(VrvActuatorFactory.class);

    public static void prometheusBuild(ApplicationContext context) {

        synchronized (context) {
            logger.info("prometheus config init build....");
            VrvActuatorProperties properties = context.getBean(VrvActuatorProperties.class);

            WebEndpointProperties webEndpointProperties = context.getBean(WebEndpointProperties.class);

            VrvConfig vrvConfig = context.getBean(VrvConfig.class);

            ServerProperties serverProperties = context.getBean(ServerProperties.class);
            //check config

            checkConfig(properties);

            //统一配置prometheus开放端点、基础路径

            prometheusOpenEndpoints(webEndpointProperties, properties);

            registerPrometheusMetrics(properties, vrvConfig, serverProperties);

            logger.info("prometheus config build finish ....");
        }
    }


    private static void registerPrometheusMetrics(VrvActuatorProperties properties, VrvConfig vrvConfig, ServerProperties serverProperties) {

        String zkBasePath = properties.getRegistry().getZookeeper().getPath();

        String address = properties.getRegistry().getZookeeper().getAddress();

        URL url = UrlUtils.valueOf(address);

        String applicationName = vrvConfig.getApplication();

        String uuid = UUID.randomUUID().toString();

        String path = zkBasePath.concat("/").concat(applicationName).concat("/").concat(uuid);

        Integer port = serverProperties.getPort();

        Map<String, Object> map = new HashMap<>();

        map.put("name", applicationName);

        String bindIp;
        String host = url.getHost();
        if (StringUtils.isBlank(host) || host.equals("127.0.0.1")) {
            bindIp = System.getenv("LOCAL_IP");
        } else {
            bindIp = host;
        }

        map.put("ip", bindIp);
        map.put("protocol", properties.getProtocol());
        map.put("path", properties.getEndpoint().getBasePath().concat(properties.getEndpoint().getBaseExposePath()));
        map.put("port", port);
        logger.info("register zk path :{}  ,nodeValue :{}", path, JSON.toJSONString(map));
        ZookeeperUtils.createEphemeralNode(path, JSON.toJSONString(map));
    }


    private static void prometheusOpenEndpoints(WebEndpointProperties webEndpointProperties, VrvActuatorProperties properties) {
        EndpointsProperties endpoint = properties.getEndpoint();

        webEndpointProperties.setBasePath(endpoint.getBasePath());

        WebEndpointProperties.Exposure exposure = webEndpointProperties.getExposure();

        if (CollectionUtils.isEmpty(endpoint.getExposeInclude())) {
            endpoint.getExposeInclude().add("*");
        }

        exposure.setInclude(endpoint.getExposeInclude());
        exposure.setExclude(endpoint.getExposeExclude());

    }

    private static void checkConfig(VrvActuatorProperties properties) {

        if (null == properties.getProtocol()) {
            properties.setProtocol("http");
        }
        RegistryProperties registry = properties.getRegistry();
        //默认注册配置为zk
        if (null == registry.getModel()) {
            registry.setModel("zookeeper");
        }

        ZookeeperProperties zookeeper = registry.getZookeeper();

        if (null == zookeeper.getAddress()) {
            String zookeeper_url = System.getenv("ZOOKEEPER_URL");
            if (null == zookeeper_url) {
                throw new RuntimeException("prometheus zookeeper register url is empty...");
            }
            zookeeper.setAddress(zookeeper_url);
        }

        if (null == zookeeper.getPath()) {
            String zookeeper_actuator_path = System.getenv("ZOOKEEPER_ACTUATOR_PATH");

            if (null == zookeeper_actuator_path) {
                throw new RuntimeException("prometheus zookeeper register path is empty...");
            }

            zookeeper.setPath(zookeeper_actuator_path);
        }

        logger.info("config init value VrvActuatorProperties :{}", JSON.toJSON(properties));
    }

}
