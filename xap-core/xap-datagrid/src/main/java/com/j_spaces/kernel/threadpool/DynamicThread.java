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

package com.j_spaces.kernel.threadpool;

import com.gigaspaces.internal.utils.concurrent.GSThread;

/**
 * @author kimchy (shay.banon)
 */
@com.gigaspaces.api.InternalApi
public class DynamicThread extends GSThread implements FastContextClassLoaderThread {

    private ClassLoader dynamicContextClassLoader;

    public DynamicThread(ThreadGroup threadGroup, Runnable runnable, String s, long l) {
        super(threadGroup, runnable, s, l);
        dynamicContextClassLoader = super.getContextClassLoader();
    }

    public void setAllThreadContextClassLoader(ClassLoader classLoader) {
        super.setContextClassLoader(classLoader);
        this.dynamicContextClassLoader = classLoader;
    }

    @Override
    public final void setContextClassLoader(ClassLoader classLoader) {
        dynamicContextClassLoader = classLoader;
    }

    @Override
    public final ClassLoader getContextClassLoader() {
        return dynamicContextClassLoader;
    }
}
