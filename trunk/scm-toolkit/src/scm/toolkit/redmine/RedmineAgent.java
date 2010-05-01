package scm.toolkit.redmine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.collections.KeyValue;

import scm.toolkit.Constants;
import scm.toolkit.redmine.entity.Issue;

public class RedmineAgent {

	EntityManagerFactory factory;
	EntityManager manager;

	public void init() {
		factory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
		manager = factory.createEntityManager();
	}

	public void shutdown() {
		manager.close();
		factory.close();
	}

	public Issue findIssue(int id) throws Exception {
		return manager.find(Issue.class, id);
	}

	public void persistEntity(Object... entitys) throws Exception {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {

			for (Object object : entitys) {
				manager.persist(object);
			}
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getEntity(Class<T> clzss, String queryName, KeyValue... parameters) {
		Query query = manager.createNamedQuery(queryName);
		for (KeyValue entry : parameters) {
			query.setParameter((String) entry.getKey(), entry.getValue());
		}
		return (T) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getEntitys(Class<T> clzss, String queryName, KeyValue... parameters) {
		Query query = manager.createNamedQuery(queryName);
		for (KeyValue entry : parameters) {
			query.setParameter((String) entry.getKey(), entry.getValue());
		}
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<T>();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getEntityList(Class<T> clzss) {
		return manager.createQuery(
				"SELECT o FROM " + clzss.getSimpleName() + " o")
				.getResultList();
	}
}
