package com.homework.model;

import javax.ejb.Local;

import com.homework.exception.BankOperationExeption;

@Local
public interface Account {
	
	String getClientId();
	double getCurrentAmount();
	String getCurrency();
	void deposit(double amount) throws BankOperationExeption;
	void withdraw(double amount) throws BankOperationExeption;
	void setClientId(String clientID);
	void setCurrentAmount(double newAmount);
	void setCurrency(String currency);

}
