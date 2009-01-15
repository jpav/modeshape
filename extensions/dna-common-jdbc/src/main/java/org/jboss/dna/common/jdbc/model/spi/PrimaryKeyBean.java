/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008-2009, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.common.jdbc.model.spi;

import java.util.Set;
import java.util.HashSet;
import org.jboss.dna.common.jdbc.model.api.PrimaryKey;
import org.jboss.dna.common.jdbc.model.api.PrimaryKeyColumn;

/**
 * Provides database table's primary key specific metadata.
 * 
 * @author <a href="mailto:litsenko_sergey@yahoo.com">Sergiy Litsenko</a>
 */
public class PrimaryKeyBean extends SchemaObjectBean implements PrimaryKey {
    private static final long serialVersionUID = -6620964715120791723L;
    private Set<PrimaryKeyColumn> columns = new HashSet<PrimaryKeyColumn>();

    /**
     * Default constructor
     */
    public PrimaryKeyBean() {
    }

    /**
     * Retrieves primary key columns
     * 
     * @return primary key columns
     */
    public Set<PrimaryKeyColumn> getColumns() {
        return columns;
    }

    /**
     * Adds PrimaryKeyColumn
     * 
     * @param primaryKeyColumn the PrimaryKeyColumn
     */
    public void addColumn( PrimaryKeyColumn primaryKeyColumn ) {
        columns.add(primaryKeyColumn);
    }

    /**
     * deletes PrimaryKeyColumn
     * 
     * @param primaryKeyColumn the PrimaryKeyColumn
     */
    public void deleteColumn( PrimaryKeyColumn primaryKeyColumn ) {
        columns.remove(primaryKeyColumn);
    }

    /**
     * Returns table column for specified column name or null
     * 
     * @param columnName the name of column
     * @return table column for specified column name or null.
     */
    public PrimaryKeyColumn findColumnByName( String columnName ) {
        for (PrimaryKeyColumn pkc : columns) {
            if (pkc.getName().equals(columnName)) {
                return pkc;
            }
        }
        // return nothing
        return null;
    }

}