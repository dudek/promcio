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
	
	public void addAccount(String login, String password ){
			Account account = new Account();
			account.setLogin(login);
			account.setPassword(password);
			
			em.persist(account);
	}
	
	public void removeAccount(long id){
			Account account = em.find(Account.class, id);
			
			em.remove(account);
	}
	
	public void joinAccountWithCompany(long accountId, long companyId){
			Account account = em.find(Account.class, accountId);
			Company company = em.find(Company.class, companyId);
			
			account.setCompany(company);
	
			//em.persist(account)
	}
	
	public AccountManager() {
	}

}
