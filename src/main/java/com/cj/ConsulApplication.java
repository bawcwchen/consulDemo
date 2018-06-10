package com.cj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.annotation.Resource;

/**
 * consul包含eureka,config,bus这3个组件的功能
 *
 * @author wb-cj189958
 * @date 2018/6/9 20:53
 */
@SpringBootApplication
@EnableDiscoveryClient //注册服务和发现服务
@RestController
@RefreshScope      //刷新配置项
public class ConsulApplication {

    //注意，以下配置项需要在consul上配置

    @Value("${firstname}")
    private String firstname;

    @Value("${year}")
    private Integer year;

    @Value("${name}")
    private String name;

    @Value("${address}")
    private String address;

    @Value("${nickname}")
    private String nickname;

    @Resource
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }

    /**
     * 从consul服务器取服务实例
     *
     * @return
     */
    @RequestMapping("/getInstances")
    public List<ServiceInstance> getInstances() {
        return discoveryClient.getInstances("app");
    }

   /* *//**
     * 从consul服务器取key/Value配置
     *
     * @return
     */
    @RequestMapping("/keyValuePair")
    public String keyValuePair() {
        return firstname + ":" + year;
    }

    /**
     * 从consul服务器取YAML配置
     *
     * @return
     */
    @RequestMapping("/YAML")
    public String YAML() {
        return name + ":" + address;
    }

    /**
     * 从consul服务器取YAML配置
     *
     * @return
     */
    @RequestMapping("/PROPERTIES")
    public String PROPERTIES() {
        return nickname;
    }

}
