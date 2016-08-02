package com.homework.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homework.currency.CurrencyConverter;
import com.homework.currency.YahooCurrencyConverter;
import com.homework.exception.BankOperationExeption;
import com.homework.model.Account;
import com.homework.model.BankAccount;

@WebServlet("/BankOperation")
public class BankOperation extends HttpServlet {

	private static final long serialVersionUID = -7162102217348541398L;

	@EJB
	private Account account;
	@EJB
	private List<Account> accounts;
	
	private CurrencyConverter currencyConverter;

	private double checkAmount = 0.0;
	private double balance = 0.0;

	public BankOperation() {
		super();

		accounts = new ArrayList<>();
		currencyConverter = new YahooCurrencyConverter();

		BankAccount ba = new BankAccount();
		ba.setClientId("1234");
		ba.setCurrentAmount(12300);
		ba.setCurrency("BGN");

		accounts.add(ba);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String clientID = request.getParameter("clientId");
		double newAmount = Double.parseDouble(request.getParameter("amount"));
		String operation = request.getParameter("operation");
		String currency = request.getParameter("currency");

		boolean hasAccount = false;

		for (Account acc : accounts) {

			if (acc.getClientId().equals(clientID)) {

				if (!acc.getCurrency().equals(currency)) {
					
					try {
						newAmount = newAmount*currencyConverter.convert(currency, acc.getCurrency());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (operation.equals("Deposit")) {
					try {
						acc.deposit(newAmount);
					} catch (BankOperationExeption e) {
						e.printStackTrace();
					}
				} else {
					try {

						if (this.balance == 0.0) {
							this.balance = acc.getCurrentAmount();
						}
						checkAmount += newAmount;

						if (checkAmount < balance / 2) {
							acc.withdraw(newAmount);
							
						}else {
							
							response.sendRedirect("bank.jsp");						
							checkAmount = 0;
							return;
						}

						System.out.println("Balance " +this.balance);
						System.out.println("Checked amount: " + checkAmount);

					} catch (BankOperationExeption e) {
						e.printStackTrace();
					}
				}

				hasAccount = true;
				break;

			}
		}

		if (!hasAccount) {
			account.setClientId(clientID);
			account.setCurrentAmount(newAmount);
			account.setCurrency(currency);
			accounts.add(account);

		}

		request.setAttribute("balance", account.getCurrentAmount());
		request.setAttribute("client", account.getClientId());

		System.out.println("ClientID: " + clientID + " amount: " + newAmount + " operation: " + operation);

		request.getRequestDispatcher("bank.jsp").forward(request, response);

	}

}
