package com.promcio.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.promcio.domain.Account;
import com.promcio.domain.Company;

@Stateless
public class AccountManager {

	 @PersistenceContext
	 EntityManager em;

	 public void addAccount(String login, String password) {
			Account account = new Account();
			account.setLogin(login);
			account.setPassword(password);

			em.persist(account);
	 }

	 public void removeAccount(String login) {
			Account account = em.find(Account.class, login);

			em.remove(account);
	 }

	 public void joinAccountWithCompany(String accountId, long companyId) {
			Account account = em.find(Account.class, accountId);
			Company company = em.find(Company.class, companyId);

			account.setCompany(company);

			// em.persist(account)
	 }

	 public boolean signIn(String login, String password) {
			Account account = em.find(Account.class, login);

			if (account != null && account.getPassword().equals(password)) {
				 return true;
			} else {
				 return false;
			}
	 }

	 public boolean changePassword(String login, String password, String newPassword) {
			Account account = em.find(Account.class, login);

			if (account != null && account.getPassword().equals(password)) {
				 account.setPassword(newPassword);
				 return true;
			} else {
				 return false;
			}
	 }

	 public Account getAccount(String login) {
			return em.find(Account.class, login);
	 }
}