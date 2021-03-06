/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2014, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.cache.internal;

import java.io.Serializable;

import org.hibernate.cache.spi.CacheKey;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.Type;

/**
 * Variation of CacheKey to be used when multi-tenancy is applied.
 * The tenantId field was not simply added to the superclass because of performance considerations:
 * CacheKey instances are have a very high allocation frequency and this field would enlarge the
 * size by 50%.
 *
 * @author Sanne Grinovero
 */
public class TenantAwareCacheKey extends CacheKey {

	private final String tenantId;

	public TenantAwareCacheKey(Serializable id, Type type, String entityOrRoleName, SessionFactoryImplementor factory, String tenantId) {
		super( id, type, entityOrRoleName, factory, tenantId );
		this.tenantId = tenantId;
	}

	@Override
	public String getTenantId() {
		return tenantId;
	}

}
