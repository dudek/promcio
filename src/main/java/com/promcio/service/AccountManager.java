package com.promcio.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.promcio.domain.Account;
import com.promcio.domain.Company;
import com.promcio.domain.Role;
import com.promcio.util.MD5;

@Stateless
public class AccountManager {

	 @PersistenceContext
	 EntityManager em;

	 public boolean addAccount(String login, String password, long type) {
			Account account = em.find(Account.class, login);

			if (account == null) {
				 account = new Account();
				 account.setLogin(login);
				 account.setPassword(MD5.encodeString(login + password));
				 account.setRole(em.find(Role.class, (long) type));
				 
				 em.persist(account);
				 return true;
			} else {
				 return false;
			}
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

			if (account != null && account.getPassword().equals(MD5.encodeString(login + password))) {
				 return true;
			} else {
				 return false;
			}
	 }

	 public boolean changePassword(String login, String password, String newPassword) {
			Account account = em.find(Account.class, login);

			if (account != null && account.getPassword().equals(MD5.encodeString(login + password))) {
				 account.setPassword(MD5.encodeString(login + newPassword));
				 return true;
			} else {
				 return false;
			}
	 }

	 public Account getAccount(String login) {
			return em.find(Account.class, login);
	 }
}