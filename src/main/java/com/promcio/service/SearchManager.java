package com.promcio.service;

import java.util.ArrayList;
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

	 // @PersistenceContext
	 // EntityManagerFactory emf;
	 @PersistenceContext
	 EntityManager em; // = emf.createEntityManager();

	 @Resource
	 SessionContext sc;

	 // FullTextEntityManager fullTextEntityManager =
	 // Search.getFullTextEntityManager(em);

	 public List<Employee> searchEmployee() {
			String FIRSTNAME = "Michal";
			List<Employee> result = new ArrayList<Employee>();
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);

			// em.getTransaction().begin();

			UserTransaction ut = sc.getUserTransaction();
			try {
				 System.out.println("0. Indexer");
				 fullTextEntityManager.createIndexer().startAndWait();
				 System.out.println("1. In Try");
				 ut.begin();

				 QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Employee.class).get();
				 org.apache.lucene.search.Query query = qb.keyword().onFields("firstname", "surname").matching(FIRSTNAME).createQuery();
				 System.out.println("2. After QueryBuilder");

				 // wrap Lucene query in a javax.persistence.Query
				 javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Employee.class);
				 System.out.println("3. Query transform");

				 // execute search
				 result = persistenceQuery.getResultList();
				 System.out.println("4. Execute Search " + result.size());

				 ut.commit();
				 System.out.println("5. After commit");

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
			} catch (InterruptedException e) {
				 e.printStackTrace();
			}

			return result;
	 }
}