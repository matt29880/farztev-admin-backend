package com.ronvel.farztev.admin.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

/**
 * Check if it's needed to generate an auto-ID. Solution provided here :
 * https://stackoverflow.com/questions/3194721/bypass-generatedvalue-in-hibernate-merge-data-not-in-db
 * 
 * @author mronvel
 *
 */
public class UseIdOrGenerate extends IdentityGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
		Serializable result;
		if (id != null) {
			result = id;
		} else {
			result = super.generate(session, object);
		}
		return result;
	}
}