/*
 * Copyright (C) 2023-2023 Huawei Technologies Co., Ltd. All rights reserved.
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

package io.sermant.backend.common.conf;

import io.sermant.backend.dao.DatabaseType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Backend event config
 *
 * @author xuezechao
 * @since 2023-03-02
 */
@Component
@Configuration
public class BackendConfig {
    /**
     * database type
     */
    @Value("${database.type}")
    private String database;

    /**
     * database address
     */
    @Value("${database.address}")
    private String url;

    @Value("${database.user}")
    private String user;

    @Value("${database.password}")
    private String password;

    @Value("${database.event.expire}")
    private int eventExpire;

    @Value("${database.field.expire}")
    private int fieldExpire;

    @Value("${webhook.eventpush.level}")
    private String webhookPushEventThreshold;

    @Value("${database.version}")
    private String version;

    @Value("${database.max.total}")
    private String maxTotal;

    @Value("${database.max.idle}")
    private String maxIdle;

    @Value("${database.timeout}")
    private String timeout;

    @Value("${session.expire}")
    private int sessionTimeout;

    @Value("${database.filter.thread.num}")
    private String filterThreadNum;

    @Value("${max.effective.time:60000}")
    private long heartbeatEffectiveTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DatabaseType getDatabase() {
        return DatabaseType.valueOf(database.toUpperCase(Locale.ROOT));
    }

    public void setDatabase(String db) {
        this.database = db;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getEventExpire() {
        return eventExpire;
    }

    public void setEventExpire(int eventExpire) {
        this.eventExpire = eventExpire;
    }

    public int getFieldExpire() {
        return fieldExpire;
    }

    public void setFieldExpire(int fieldExpire) {
        this.fieldExpire = fieldExpire;
    }

    public long getHeartbeatEffectiveTime() {
        return heartbeatEffectiveTime;
    }

    public void setHeartbeatEffectiveTime(long heartbeatEffectiveTime) {
        this.heartbeatEffectiveTime = heartbeatEffectiveTime;
    }

    public void setWebhookPushEventThreshold(String webhookPushEventThreshold) {
        this.webhookPushEventThreshold = webhookPushEventThreshold;
    }

    public String getWebhookPushEventThreshold() {
        return webhookPushEventThreshold;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(String maxTotal) {
        this.maxTotal = maxTotal;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getFilterThreadNum() {
        return filterThreadNum;
    }

    public void setFilterThreadNum(String filterThreadNum) {
        this.filterThreadNum = filterThreadNum;
    }
}
