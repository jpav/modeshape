/*
 * JBoss DNA (http://www.jboss.org/dna)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * See the AUTHORS.txt file in the distribution for a full listing of 
 * individual contributors.
 *
 * JBoss DNA is free software. Unless otherwise indicated, all code in JBoss DNA
 * is licensed to you under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * 
 * JBoss DNA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.graph.search;

import org.jboss.dna.graph.ExecutionContext;
import org.jboss.dna.graph.observe.Observer;
import org.jboss.dna.graph.property.DateTime;
import org.jboss.dna.graph.request.processor.RequestProcessor;
import org.jboss.dna.graph.search.AbstractSearchEngine.Workspaces;

/**
 * The processor that is created by the provider whenever a logical set of activities needs to be performed.
 * 
 * @param <WorkspaceType> the type of workspace
 */
public abstract class SearchEngineProcessor<WorkspaceType extends SearchEngineWorkspace> extends RequestProcessor {

    protected boolean rollback = false;
    protected final Workspaces<WorkspaceType> workspaces;

    /**
     * @param sourceName
     * @param context
     * @param workspaces
     * @param observer
     * @param now
     */
    protected SearchEngineProcessor( String sourceName,
                                     ExecutionContext context,
                                     Workspaces<WorkspaceType> workspaces,
                                     Observer observer,
                                     DateTime now ) {
        super(sourceName, context, observer, now);
        this.workspaces = workspaces;
        assert this.workspaces != null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.graph.request.processor.RequestProcessor#close()
     */
    @Override
    public void close() {
        try {
            if (rollback) rollback();
            else commit();
        } finally {
            // publish any changes to the observer ...
            super.close();
        }
    }

    /**
     * Subclasses should implement this method to throw away any work that has been done with this processor.
     */
    protected abstract void rollback();

    /**
     * Subclasses should implement this method to commit and save any work that has been done with this processor.
     */
    protected abstract void commit();
}