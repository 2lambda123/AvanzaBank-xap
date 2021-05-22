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

package com.gigaspaces.management.space;

import com.gigaspaces.internal.io.IOUtils;
import com.gigaspaces.internal.utils.Textualizable;
import com.gigaspaces.internal.utils.Textualizer;
import com.gigaspaces.management.transport.ConnectionEndpointDetails;
import com.gigaspaces.serialization.SmartExternalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Encapsulates local cache details.
 *
 * @author Niv Ingberg
 * @since 9.5.0
 */
@com.gigaspaces.api.InternalApi
public class LocalCacheDetails implements SmartExternalizable, Textualizable {
    private static final long serialVersionUID = 1L;

    private String _id;
    private ConnectionEndpointDetails _connectionDetails;

    /**
     * Required for Externalizable
     */
    public LocalCacheDetails() {
    }

    public LocalCacheDetails(String id, ConnectionEndpointDetails connectionDetails) {
        this._id = id;
        this._connectionDetails = connectionDetails;
    }

    @Override
    public String toString() {
        return Textualizer.toString(this);
    }

    @Override
    public void toText(Textualizer textualizer) {
        textualizer.append("id", getId());
        textualizer.append("connectionDetails", getConnectionDetails());
    }

    public String getId() {
        return _id;
    }

    public ConnectionEndpointDetails getConnectionDetails() {
        return _connectionDetails;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        IOUtils.writeString(out, _id);
        IOUtils.writeObject(out, _connectionDetails);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this._id = IOUtils.readString(in);
        this._connectionDetails = IOUtils.readObject(in);
    }
}
