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

//
package com.j_spaces.core.cache.offHeap.sadapter;

import com.gigaspaces.datasource.DataIterator;
import com.gigaspaces.internal.server.space.SpaceEngine;
import com.gigaspaces.internal.server.storage.IEntryHolder;
import com.gigaspaces.server.blobstore.BlobStoreGetBulkOperationResult;
import com.gigaspaces.server.blobstore.BlobStoreObjectType;
import com.j_spaces.core.cache.EntryCacheInfoFactory;
import com.j_spaces.core.cache.offHeap.IOffHeapEntryHolder;
import com.j_spaces.core.cache.offHeap.OffHeapEntryLayout;
import com.j_spaces.core.sadapter.ISAdapterIterator;
import com.j_spaces.core.sadapter.SAException;

/**
 * off-heap storage adapter data iterator to be used in recovery
 *
 * @author yechiel
 * @since 10.0
 */

@com.gigaspaces.api.InternalApi
public class OffHeapDataIterator implements ISAdapterIterator<IEntryHolder> {
    private final SpaceEngine _engine;
    private final DataIterator<BlobStoreGetBulkOperationResult> _iter;

    public OffHeapDataIterator(SpaceEngine engine) {
        _engine = engine;
        _iter = _engine.getCacheManager().getBlobStoreStorageHandler().iterator(BlobStoreObjectType.DATA);

    }

    @Override
    public IEntryHolder next() throws SAException {
        // TODO Auto-generated method stub
        if (_iter == null)
            return null;

        BlobStoreGetBulkOperationResult res = null;
        if (_iter.hasNext())
            res = _iter.next();
        if (res == null)
            return null;
        IEntryHolder eh = ((OffHeapEntryLayout) res.getData()).buildOffHeapEntryHolder(_engine.getCacheManager());
        EntryCacheInfoFactory.createOffHeapEntryCacheInfo(eh);
        IOffHeapEntryHolder oeh = (IOffHeapEntryHolder) eh;
        oeh.getOffHeapResidentPart().setOffHeapPosition(res.getPosition());
        return eh;
    }

    @Override
    public void close() throws SAException {
        // TODO Auto-generated method stub
        if (_iter != null)
            _iter.close();

    }

}
