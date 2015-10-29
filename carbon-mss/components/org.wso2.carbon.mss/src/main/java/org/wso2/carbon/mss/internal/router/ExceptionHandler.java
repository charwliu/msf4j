/*
 *  Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package org.wso2.carbon.mss.internal.router;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.wso2.carbon.mss.HttpResponder;

/**
 * Handles exceptions and provides a response via the {@link org.wso2.carbon.mss.HttpResponder}.
 */
public class ExceptionHandler {
    public void handle(Throwable t, HttpRequest request, HttpResponder responder) {
        if (t instanceof HandlerException) {
            HandlerException handlerException = (HandlerException) t;
            responder.sendString(handlerException.getFailureStatus(), handlerException.getMessage());
        } else {
            String message = String.format("Exception encountered while processing request : %s", t.getMessage());
            responder.sendString(HttpResponseStatus.INTERNAL_SERVER_ERROR, message);
        }
    }
}
