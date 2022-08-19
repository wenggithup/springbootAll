package com.vrv.im.factory;

import com.alibaba.fastjson.JSON;

import com.vrv.im.properties.PromethenusRegistryProperties;
import com.vrv.im.properties.VrvPromethenusProperties;
import com.vrv.im.util.ZookeeperUtils;
import com.vrv.rpc.dubbo.config.VrvConfig;
import com.vrv.im.constant.ExposePath;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.context.ApplicationContext;
import com.vrv.im.properties.ZookeeperProperties;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type.NODE_REMOVED;


/**
 * @DATE: 2022/5/12 4:57 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class VrvPromethenusFactory {

    private final static Logger logger = LoggerFactory.getLogger(VrvPromethenusFactory.class);
    private AtomicBoolean flag;

    public static void prometheusBuild(ApplicationContext context){

        synchronized (context){
            logger.info("prometheus config init build....");
            VrvPromethenusProperties properties = context.getBean(VrvPromethenusProperties.class);

            WebEndpointProperties webEndpointProperties = context.getBean(WebEndpointProperties.class);

            VrvConfig vrvConfig = context.getBean(VrvConfig.class);

            ManagementServerProperties serverProperties = context.getBean(ManagementServerProperties.class);

            checkConfig(properties);

            prometheusOpenEndpoints(webEndpointProperties,properties);

            registerPrometheusMetrics(properties,vrvConfig,serverProperties,webEndpointProperties);

            logger.info("prometheus config build finish ....");
        }
    }



    private static void registerPrometheusMetrics(VrvPromethenusProperties properties, VrvConfig vrvConfig,ManagementServerProperties serverProperties,
                                                  WebEndpointProperties webEndpointProperties) {


        String zkBasePath = properties.getRegistry().getZookeeper().getPath();

        String applicationName = vrvConfig.getApplication();

        String uuid = UUID.randomUUID().toString();

        String namespace = zkBasePath.concat("/").concat(applicationName);

        String path = namespace.concat("/").concat(uuid);

        System.out.println(JSON.toJSONString(serverProperties));

        if (null == serverProperties){
            throw new RuntimeException("check management server config is null....");
        }

        Integer port = serverProperties.getPort();

        if (null == port){
            throw new RuntimeException("check management server port config is null....");
        }

        InetAddress inetAddress = serverProperties.getAddress();
        String address;
        if (null == inetAddress){
            address = System.getenv("LOCAL_IP");
        }else {
            address=inetAddress.getHostAddress();
        }
        Map<String,Object> map = new HashMap<>(10);

        map.put("name",applicationName);

        map.put("ip",address);
        map.put("protocol",properties.getProtocol());
        map.put("path",webEndpointProperties.getBasePath().concat(ExposePath.EXPOSE_PROMETHEUS));
        map.put("port",port);
        logger.info("register zk path :{}  ,nodeValue :{}",path, JSON.toJSONString(map));
        ZookeeperUtils.createEphemeralNode(path, JSON.toJSONString(map));
        //注册监听事件
        CuratorFramework client = ZookeeperUtils.getCuratorFramework();

        CuratorCache cache = CuratorCache.build(client, namespace);
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forTreeCache(client, (curatorClient, event) -> {
                    ChildData data = event.getData();
                    String changePath = "";
                    if (null != data) {
                        changePath = data.getPath();
                    }

                    switch (event.getType()) {

                        case NODE_REMOVED:
                            if (changePath.length() <= namespace.length()) {
                                return;
                            }
                            logger.info("zk node removed,nodePath :{}",changePath);
                            String uid = UUID.randomUUID().toString();
                            ZookeeperUtils.createEphemeralNode(namespace.concat("/").concat(uid), JSON.toJSONString(map));
                            logger.info("register zk path :{}  ,nodeValue :{}",namespace.concat("/").concat(uid), JSON.toJSONString(map));
                        default:
                            break;
                    }

                }).build();
        cache.listenable().addListener(listener);
        cache.start();
        //关闭客户端链接
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                cache.listenable().removeListener(listener);
                client.close();
                logger.info("delete cache over");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }


    private static void prometheusOpenEndpoints(WebEndpointProperties webEndpointProperties,VrvPromethenusProperties properties) {


        WebEndpointProperties.Discovery discovery = webEndpointProperties.getDiscovery();

        discovery.setEnabled(true);

        WebEndpointProperties.Exposure exposure = webEndpointProperties.getExposure();


        logger.info("exposing endpoints info exposure :{}", JSON.toJSONString(exposure));

    }

    private static void checkConfig(VrvPromethenusProperties properties) {

        if (null == properties.getProtocol() || "".equals(properties.getProtocol())){
            properties.setProtocol("http");
        }
        PromethenusRegistryProperties registry = properties.getRegistry();
        //默认注册配置为zk
        if (null == registry.getModel() || "".equals(registry.getModel())){
            registry.setModel("zookeeper");
        }

        ZookeeperProperties zookeeper = registry.getZookeeper();

        if  (null == zookeeper.getAddress()){
            String zookeeperUrl = System.getenv("ZOOKEEPER_URL");
            if (null == zookeeperUrl){
                throw new RuntimeException("prometheus zookeeper register url is empty...");
            }
            zookeeper.setAddress(zookeeperUrl);
        }

        if (null == zookeeper.getPath()){
            String zookeeperActuatorPath = System.getenv("ZOOKEEPER_ACTUATOR_PATH");

            if (null == zookeeperActuatorPath){
                throw new RuntimeException("prometheus zookeeper register path is empty...");
            }

            zookeeper.setPath(zookeeperActuatorPath);
        }

        logger.info("config init value VrvActuatorProperties :{}",JSON.toJSON(properties));
    }
}
