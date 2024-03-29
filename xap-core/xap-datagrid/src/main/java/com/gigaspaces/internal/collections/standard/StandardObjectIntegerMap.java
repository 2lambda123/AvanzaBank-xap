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

package com.gigaspaces.internal.collections.standard;

import com.gigaspaces.internal.collections.ObjectIntegerMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Niv Ingberg
 * @since 12.0
 */
@com.gigaspaces.api.InternalApi
public class StandardObjectIntegerMap<K> implements ObjectIntegerMap<K> {
    private static final long serialVersionUID = -695817013778834645L;
    private final HashMap<K, Integer> map = new HashMap<K, Integer>();

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public int get(K key) {
        return map.get(key);
    }

    @Override
    public void put(K key, int value) {
        map.put(key, value);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void flush(ObjectIntegerMap<K> target) {
        for (Map.Entry<K, Integer> entry : map.entrySet()) {
            target.put(entry.getKey(), entry.getValue());
        }
    }

}
