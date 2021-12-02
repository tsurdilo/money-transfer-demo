package io.temporal.demos.utils;

import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;

public class BankingClient {
    public void performWithdraw(Account account, Amount amount, String refId) {
        // simulate some rest api call...
        System.out.println("> BankingClient: refId: " + refId + " withdrawing " + amount.getAmount() + " from: " + account.getId());
        System.out.println("> BankingClient: completed withdraw");
    }

    public void performDeposit(Account account, Amount amount, String refId) {
        // simulate some rest api call...
        System.out.println("> BankingClient: refId: " + refId + " depositing " + amount.getAmount() + " from: " + account.getId());
        System.out.println("> BankingClient: completed deposit");
    }

    public void performDepositSimulateError(Account account, Amount amount, String refId) {
        System.out.println("> BankingClient: refId: " + refId + " depositing " + amount.getAmount() + " from: " + account.getId());
        throw new NullPointerException("Simulated exception during deposit!");
    }
}
