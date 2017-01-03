/*
 * Copyright (c) 2008-2016, GigaSpaces Technologies, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gigaspaces.internal.cluster.node.impl.backlog.globalorder;


import com.gigaspaces.internal.cluster.node.impl.backlog.AbstractSingleFileGroupBacklog;

@com.gigaspaces.api.InternalApi
public class GlobalOrderConfirmationHolder extends AbstractSingleFileConfirmationHolder {
    private boolean _hadAnyHandshake = false;
    private long _lastConfirmedKey = -1;

    public boolean hadAnyHandshake() {
        return _hadAnyHandshake;
    }

    public void setLastConfirmedKey(long lastConfirmedKey, String memberName, AbstractSingleFileGroupBacklog groupBacklog) {
        _hadAnyHandshake = true;
        groupBacklog.decreaseWeight(memberName, _lastConfirmedKey, lastConfirmedKey, this);
        _lastConfirmedKey = lastConfirmedKey;
    }

    public void setLastConfirmedKey(long lastConfirmedKey, long weight) {
        _hadAnyHandshake = true;
        _lastConfirmedKey = lastConfirmedKey;
        setWeight(weight);
    }

    @Override
    public long getLastConfirmedKey() {
        return _lastConfirmedKey;
    }

    @Override
    public String toString() {
        return "hadAnyHandshake="
                + _hadAnyHandshake + ", lastConfirmedKey=" + _lastConfirmedKey +
                ", " + super.toString();
    }

}
