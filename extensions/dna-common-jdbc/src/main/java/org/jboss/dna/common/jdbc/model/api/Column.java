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
package org.jboss.dna.common.jdbc.model.api;

import java.util.Set;

/**
 * Provides all column specific metadata.
 * 
 * @author <a href="mailto:litsenko_sergey@yahoo.com">Sergiy Litsenko</a>
 */
public interface Column extends DatabaseNamedObject {

    /**
     * Returns owner of ColumnMetaData such as Table, or Stored Procedure, UDT, PK, FK, Index, etc. May return NULL
     * 
     * @return owner of ColumnMetaData such as Table, or Stored Procedure, or UDT, PK, FK, Index, etc. May return NULL
     */
    SchemaObject getOwner();

    /**
     * Sets the owner of ColumnMetaData
     * 
     * @param owner the owner of ColumnMetaData
     */
    void setOwner( SchemaObject owner );

    /**
     * Gets column nullability
     * 
     * @return column nullability
     */
    NullabilityType getNullabilityType();

    /**
     * Sets column nullability
     * 
     * @param nullabilityType the column nullability
     */
    void setNullabilityType( NullabilityType nullabilityType );

    /**
     * Gets SQL type from java.sql.Types
     * 
     * @return SQL type from java.sql.Types
     */
    SqlType getSqlType();

    /**
     * Sets SQL type from java.sql.Types
     * 
     * @param sqlType the SQL type from java.sql.Types
     */
    void setSqlType( SqlType sqlType );

    /**
     * Data source dependent type name. For a UDT, the type name is fully qualified. For a REF, the type name is fully qualified
     * and represents the target type of the reference type.
     * 
     * @return data source dependent type name
     */
    String getTypeName();

    /**
     * Data source dependent type name. For a UDT, the type name is fully qualified. For a REF, the type name is fully qualified
     * and represents the target type of the reference type.
     * 
     * @param typeName data source dependent type name
     */
    void setTypeName( String typeName );

    /**
     * Gets column size. For char or date types this is the maximum number of characters, for numeric or decimal types this is
     * precision. For Stored procedure columns it is length in bytes of data
     * 
     * @return column size
     */
    Integer getSize();

    /**
     * Sets column size. For char or date types this is the maximum number of characters, for numeric or decimal types this is
     * precision. For Stored procedure columns it is length in bytes of data
     * 
     * @param size the column size
     */
    void setSize( Integer size );

    /**
     * Gets precision if applicable otherwise 0. For table columns return the number of fractional digits; for stored procedure
     * column - scale.
     * 
     * @return precision if applicable otherwise 0
     */
    Integer getPrecision();

    /**
     * Sets precision if applicable otherwise 0. For table columns return the number of fractional digits; for stored procedure
     * column - scale.
     * 
     * @param precision the precision if applicable otherwise 0
     */
    void setPrecision( Integer precision );

    /**
     * Gets radix if applicable
     * 
     * @return radix if applicable
     */
    Integer getRadix();

    /**
     * Sets radix if applicable
     * 
     * @param radix if applicable
     */
    void setRadix( Integer radix );

    /**
     * Gets default value (may be <code>null</code>)
     * 
     * @return default value (may be <code>null</code>)
     */
    String getDefaultValue();

    /**
     * Sets default value (may be <code>null</code>)
     * 
     * @param defaultValue the default value (may be <code>null</code>)
     */
    void setDefaultValue( String defaultValue );

    /**
     * Returns index of column starting at 1 - if applicable. Otherwise returns -1.
     * 
     * @return index of column starting at 1 - if applicable. Otherwise returns -1.
     */
    Integer getOrdinalPosition();

    /**
     * Sets index of column starting at 1 - if applicable. Otherwise returns -1.
     * 
     * @param ordinalPosition the index of column starting at 1 - if applicable. Otherwise returns -1.
     */
    void setOrdinalPosition( Integer ordinalPosition );

    /**
     * For char types returns the maximum number of bytes in the column. Otherwise returns -1. May return null.
     * 
     * @return For char types returns the maximum number of bytes in the column. Otherwise returns -1. May return null.
     */
    Integer getCharOctetLength();

    /**
     * For char types sets the maximum number of bytes in the column. Otherwise -1.
     * 
     * @param charOctetLength For char types sets the maximum number of bytes in the column. Otherwise -1.
     */
    void setCharOctetLength( Integer charOctetLength );

    /**
     * Gets table column privileges.
     * 
     * @return set of table column privileges
     */
    Set<Privilege> getPrivileges();

    /**
     * Adds table column priviledge
     * 
     * @param privilege the table column priviledge
     */
    void addPrivilege( Privilege privilege );

    /**
     * Deletes table column priviledge
     * 
     * @param privilege the table column priviledge
     */
    void deletePrivilege( Privilege privilege );

    /**
     * Searches priviledge by name
     * 
     * @param priviledgeName the priviledge name to search
     * @return priviledge if found, otherwise return null
     */
    Privilege findPriviledgeByName( String priviledgeName );
}