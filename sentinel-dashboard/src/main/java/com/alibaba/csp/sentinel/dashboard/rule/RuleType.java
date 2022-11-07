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

package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author FengJianxin
 * @since 1.8.4
 */
public enum RuleType {

    /**
     * 流控规则
     */
    FLOW("flow", FlowRuleEntity.class),
    /**
     * 熔断规则
     */
    DEGRADE("degrade", DegradeRuleEntity.class),
    /**
     * 热点规则
     */
    PARAM_FLOW("param-flow", ParamFlowRuleEntity.class),
    /**
     * 系统规则
     */
    SYSTEM("system", SystemRuleEntity.class),
    /**
     * 授权规则
     */
    AUTHORITY("authority", AuthorityRuleEntity.class),
    /**
     * 网关流控规则
     */
    GW_FLOW("gw-flow", GatewayFlowRuleEntity.class),
    /**
     * api 分组
     */
    GW_API_GROUP("gw-api-group", ApiDefinitionEntity.class);

    /**
     * alias for {@link AbstractRule}.
     */
    private final String name;
    /**
     * encoder
     */
    private final Converter<Object, String> encoder;
    /**
     * decoder
     */
    private final Converter<String, List<? extends RuleEntity>> decoder;

    RuleType(String name, Class<? extends RuleEntity> clazz) {
        this.name = name;
        this.encoder = s -> JSON.toJSONString(s, true);
        this.decoder = s -> JSON.parseArray(s, clazz);
    }

    public String getName() {
        return name;
    }

    public static Optional<RuleType> of(String name) {
        if (StringUtils.isEmpty(name)) {
            return Optional.empty();
        }
        return Arrays.stream(RuleType.values())
                .filter(ruleType -> name.equals(ruleType.getName())).findFirst();
    }

    @SuppressWarnings("unchecked")
    public <T extends RuleEntity> List<T> decode(String rules) {
        return (List<T>) decoder.convert(rules);
    }

    public <T extends RuleEntity> String encode(List<T> rules) {
        return encoder.convert(rules);
    }

}