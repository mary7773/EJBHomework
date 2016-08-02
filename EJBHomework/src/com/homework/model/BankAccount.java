package com.homework.model;

import javax.ejb.Stateful;

import com.homework.exception.BankOperationExeption;

@Stateful
public class BankAccount implements Account{
	
	private String clientId;
	private double currentAmount;
	private String currency;
		

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	
	@Override
	public void deposit(double amount) throws BankOperationExeption {
		if (amount > 0) {
			this.currentAmount +=amount;
			
		}else {
			throw new BankOperationExeption("The amount is incorrect!");
		}
		
	}

	@Override
	public void withdraw(double amount) throws BankOperationExeption{

		if (amount> 0 && amount < currentAmount) {
				this.currentAmount -=amount;			
		}else {
			throw new BankOperationExeption("The specified amount is incorrect or you are not allowed to withdraw the requested amount!");
		}
		
	}

	public void setCurrency(String currency) {
		 this.currency = currency;
	}
	
	@Override
	public String getCurrency() {
		return this.currency;
	}

}
