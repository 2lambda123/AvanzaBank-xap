/*
 * 
 * Copyright 2005 Sun Microsystems, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package net.jini.core.transaction;


/**
 * Exception thrown when a transaction cannot abort because it has already already decided to
 * commit.
 *
 * @author Sun Microsystems, Inc.
 * @since 1.0
 */
@com.gigaspaces.api.InternalApi
public class CannotAbortException extends TransactionException {
    static final long serialVersionUID = 3597101646737510009L;

    /**
     * Constructs an instance with a detail message.
     *
     * @param desc the detail message
     */
    public CannotAbortException(String desc) {
        super(desc);
    }

    /**
     * Constructs an instance with a detail message.
     *
     * @param desc  the detail message
     * @param cause the cause
     */
    public CannotAbortException(String desc, Throwable cause) {
        super(desc, cause);
    }

    /**
     * Constructs an instance with no detail message.
     */
    public CannotAbortException() {
        super();
    }
}
