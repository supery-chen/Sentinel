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

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleStore;
import com.alibaba.csp.sentinel.dashboard.rule.RuleType;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FengJianxin
 * @since 2022/7/29
 */
public class DynamicRuleNacosStore<T extends RuleEntity> extends DynamicRuleStore<T> {

    private final ConfigService configService;

    public DynamicRuleNacosStore(final RuleType ruleType,
                                 final ConfigService configService) {
        super.ruleType = ruleType;
        this.configService = configService;
    }

    @Override
    public List<T> getRules(final String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.getDataIdPostfix(ruleType),
                NacosConfigUtil.GROUP_ID, 3000);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return ruleType.decode(rules);
    }

    @Override
    public void publish(final String app, final List<T> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + NacosConfigUtil.getDataIdPostfix(ruleType),
                NacosConfigUtil.GROUP_ID, ruleType.encode(rules));
    }

}