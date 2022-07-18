/*
 * Copyright (C) 2022-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huaweicloud.sermant.router.spring.declarer;

import com.huaweicloud.sermant.core.plugin.agent.matcher.ClassMatcher;

/**
 * spring cloud loadbalancer CachingServiceInstanceListSupplier增强类，获取下游实例
 *
 * @author provenceee
 * @since 2022-07-12
 */
public class LoadBalancerDeclarer extends AbstractDeclarer {
    private static final String ENHANCE_CLASS =
        "org.springframework.cloud.loadbalancer.core.CachingServiceInstanceListSupplier";

    private static final String INTERCEPT_CLASS
        = "com.huaweicloud.sermant.router.spring.interceptor.LoadBalancerInterceptor";

    private static final String METHOD_NAME = "get";

    /**
     * 构造方法
     */
    public LoadBalancerDeclarer() {
        super(ENHANCE_CLASS, INTERCEPT_CLASS, METHOD_NAME);
    }

    @Override
    public ClassMatcher getClassMatcher() {
        return ClassMatcher.nameEquals(ENHANCE_CLASS);
    }
}