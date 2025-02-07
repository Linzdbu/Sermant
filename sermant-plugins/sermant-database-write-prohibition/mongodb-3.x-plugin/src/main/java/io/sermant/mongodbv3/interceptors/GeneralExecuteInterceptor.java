/*
 *  Copyright (C) 2024-2024 Huawei Technologies Co., Ltd. All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package io.sermant.mongodbv3.interceptors;

import com.mongodb.ServerAddress;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.WriteBinding;
import com.mongodb.connection.ServerDescription;

import io.sermant.core.plugin.agent.entity.ExecuteContext;
import io.sermant.database.constant.DatabaseType;
import io.sermant.database.entity.DatabaseInfo;
import io.sermant.database.handler.DatabaseHandler;
import io.sermant.database.interceptor.AbstractMongoDbInterceptor;

import java.util.Optional;

/**
 * CommandOperationHelper executeCommand、executeRetryableCommand Method Interceptor
 *
 * @author daizhenyu
 * @since 2024-01-18
 **/
public class GeneralExecuteInterceptor extends AbstractMongoDbInterceptor {
    /**
     * No-argument constructor
     */
    public GeneralExecuteInterceptor() {
    }

    /**
     * Parametric constructor
     *
     * @param handler write operation handler
     */
    public GeneralExecuteInterceptor(DatabaseHandler handler) {
        this.handler = handler;
    }

    @Override
    protected void createAndCacheDatabaseInfo(ExecuteContext context) {
        DatabaseInfo info = new DatabaseInfo(DatabaseType.MONGODB);
        context.setLocalFieldValue(DATABASE_INFO, info);
        info.setDatabaseName((String) context.getArguments()[1]);
        ServerAddress serverAddress = Optional.ofNullable((WriteBinding) context.getArguments()[0])
                .map(WriteBinding::getWriteConnectionSource)
                .map(ConnectionSource::getServerDescription)
                .map(ServerDescription::getAddress)
                .orElse(null);
        if (serverAddress != null) {
            info.setHostAddress(serverAddress.getHost());
            info.setPort(serverAddress.getPort());
        }
    }
}
