/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleStore;
import com.alibaba.csp.sentinel.dashboard.rule.RuleType;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Configuration
@EnableConfigurationProperties(NacosProperties.class)
public class NacosConfig {

    @Bean
    public ConfigService nacosConfigService(NacosProperties nacosProperties) throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getServerAddr());
        properties.put(PropertyKeyConst.NAMESPACE, nacosProperties.getNamespace());
        return ConfigFactory.createConfigService(properties);
    }

    @Bean
    public DynamicRuleStore<FlowRuleEntity> flowRuleDynamicRuleStore(ConfigService configService) {
        return new DynamicRuleNacosStore<>(RuleType.FLOW, configService);
    }

    @Bean
    public DynamicRuleStore<DegradeRuleEntity> degradeRuleDynamicRuleStore(ConfigService configService) {
        return new DynamicRuleNacosStore<>(RuleType.DEGRADE, configService);
    }

    @Bean
    public DynamicRuleStore<ParamFlowRuleEntity> paramFlowRuleDynamicRuleStore(ConfigService configService) {
        return new DynamicRuleNacosStore<>(RuleType.PARAM_FLOW, configService);
    }

    @Bean
    public DynamicRuleStore<SystemRuleEntity> systemRuleDynamicRuleStore(ConfigService configService) {
        return new DynamicRuleNacosStore<>(RuleType.SYSTEM, configService);
    }

    @Bean
    public DynamicRuleStore<AuthorityRuleEntity> authorityRuleDynamicRuleStore(ConfigService configService) {
        return new DynamicRuleNacosStore<>(RuleType.AUTHORITY, configService);
    }

    @Bean
    public DynamicRuleStore<GatewayFlowRuleEntity> gatewayFlowRuleDynamicRuleStore(ConfigService configService) {
        return new DynamicRuleNacosStore<>(RuleType.GW_FLOW, configService);
    }

    @Bean
    public DynamicRuleStore<ApiDefinitionEntity> apiDefinitionDynamicRuleStore(ConfigService configService) {
        return new DynamicRuleNacosStore<>(RuleType.GW_API_GROUP, configService);
    }

}
