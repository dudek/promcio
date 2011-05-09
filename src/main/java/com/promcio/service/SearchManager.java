package com.promcio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import com.promcio.domain.Employee;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SearchManager {

	 @PersistenceContext
	 EntityManager em;

	 @Resource
	 SessionContext sc;

	 public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
			List<T> r = new ArrayList<T>(c.size());
			for (Object o : c)
				 r.add(clazz.cast(o));
			return r;
	 }

	 public void indexing() {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
			try {
				 System.out.println("0. Indexer");
				 fullTextEntityManager.createIndexer().startAndWait();
			} catch (InterruptedException e) {
				 e.printStackTrace();
			}
	 }

	 public List<Employee> easySearchEmployee(String value) {
			List<Employee> result = new ArrayList<Employee>();
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

			UserTransaction ut = sc.getUserTransaction();
			try {
				 ut.begin();

				 QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Employee.class).get();
				 org.apache.lucene.search.Query query = qb.keyword().onFields("firstname", "surname").matching(value).createQuery();

				 // wrap Lucene query in a javax.persistence.Query
				 javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Employee.class);

				 // execute search
				 result = castList(Employee.class, persistenceQuery.getResultList());

				 ut.commit();

				 // WTF! OMG! LOL! xD
			} catch (NotSupportedException e) {
				 e.printStackTrace();
			} catch (SystemException e) {
				 e.printStackTrace();
			} catch (SecurityException e) {
				 e.printStackTrace();
			} catch (IllegalStateException e) {
				 e.printStackTrace();
			} catch (RollbackException e) {
				 e.printStackTrace();
			} catch (HeuristicMixedException e) {
				 e.printStackTrace();
			} catch (HeuristicRollbackException e) {
				 e.printStackTrace();
			}

			return result;
	 }
	 
	 //TODO zaawansowane wyszukiwanie (by City)
	 public List<Employee> advancedSearchEmployee(String value) {
			List<Employee> result = new ArrayList<Employee>();
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

			UserTransaction ut = sc.getUserTransaction();
			try {
				 ut.begin();

				 QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Employee.class).get();
				 org.apache.lucene.search.Query query = qb.keyword().onFields("details.city").matching(value).createQuery();

				 // wrap Lucene query in a javax.persistence.Query
				 javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Employee.class);

				 // execute search
				 result = castList(Employee.class, persistenceQuery.getResultList());

				 ut.commit();

				 // WTF! OMG! LOL! xD
			} catch (NotSupportedException e) {
				 e.printStackTrace();
			} catch (SystemException e) {
				 e.printStackTrace();
			} catch (SecurityException e) {
				 e.printStackTrace();
			} catch (IllegalStateException e) {
				 e.printStackTrace();
			} catch (RollbackException e) {
				 e.printStackTrace();
			} catch (HeuristicMixedException e) {
				 e.printStackTrace();
			} catch (HeuristicRollbackException e) {
				 e.printStackTrace();
			}
			
			return result;
	 }
}