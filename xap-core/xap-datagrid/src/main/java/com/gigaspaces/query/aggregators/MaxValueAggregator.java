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


package com.gigaspaces.query.aggregators;

import java.io.Serializable;

/**
 * @author Niv Ingberg
 * @since 10.0
 */

public class MaxValueAggregator<T extends Serializable & Comparable> extends AbstractPathAggregator<T> {

    private static final long serialVersionUID = 1L;

    private transient T result;

    @Override
    public String getDefaultAlias() {
        return "max(" + getPath() + ")";
    }

    @Override
    public void aggregate(SpaceEntriesAggregatorContext context) {
        T value = (T) getPathValue(context);
        if (value != null)
            result = result == null || result.compareTo(value) < 0 ? value : result;
    }

    @Override
    public void aggregateIntermediateResult(T partitionResult) {
        result = result == null || result.compareTo(partitionResult) < 0 ? partitionResult : result;
    }

    @Override
    public T getIntermediateResult() {
        return result;
    }

    @Override
    public String getName() {
        return "MAX";
    }
}
