package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author super
 * @date 2022/11/7
 */
@ConfigurationProperties(prefix = "sentinel.nacos")
public class NacosProperties {

    private String serverAddr = "localhost:8080";
    private String namespace = "public";

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
