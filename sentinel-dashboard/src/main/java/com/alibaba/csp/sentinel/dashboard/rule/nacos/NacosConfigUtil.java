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

import com.alibaba.csp.sentinel.dashboard.rule.RuleType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
public final class NacosConfigUtil {

    public static final String GROUP_ID = "SENTINEL_GROUP";

    public static final String FLOW_DATA_ID_POSTFIX = "-flow-rules";
    public static final String DEGRADE_DATA_ID_POSTFIX = "-degrade-rules";
    public static final String PARAM_FLOW_DATA_ID_POSTFIX = "-param-rules";
    public static final String SYSTEM_DATA_ID_POSTFIX = "-system-rules";
    public static final String AUTHORITY_DATA_ID_POSTFIX = "-authority-rules";
    public static final String GW_FLOW_DATA_ID_POSTFIX = "-gw-flow-rules";
    public static final String GW_API_GROUP_DATA_ID_POSTFIX = "-gw-api-group-rules";

    private static final Map<RuleType, String> RULE_DATA_ID_SUFFIX_MAP = new ConcurrentHashMap<>();

    static {
        RULE_DATA_ID_SUFFIX_MAP.put(RuleType.FLOW, FLOW_DATA_ID_POSTFIX);
        RULE_DATA_ID_SUFFIX_MAP.put(RuleType.DEGRADE, DEGRADE_DATA_ID_POSTFIX);
        RULE_DATA_ID_SUFFIX_MAP.put(RuleType.PARAM_FLOW, PARAM_FLOW_DATA_ID_POSTFIX);
        RULE_DATA_ID_SUFFIX_MAP.put(RuleType.SYSTEM, SYSTEM_DATA_ID_POSTFIX);
        RULE_DATA_ID_SUFFIX_MAP.put(RuleType.AUTHORITY, AUTHORITY_DATA_ID_POSTFIX);
        RULE_DATA_ID_SUFFIX_MAP.put(RuleType.GW_FLOW, GW_FLOW_DATA_ID_POSTFIX);
        RULE_DATA_ID_SUFFIX_MAP.put(RuleType.GW_API_GROUP, GW_API_GROUP_DATA_ID_POSTFIX);
    }

    private NacosConfigUtil() {
    }

    public static String getDataIdPostfix(RuleType rule) {
        return RULE_DATA_ID_SUFFIX_MAP.get(rule);
    }

}
