package io.temporal.demos.activities;

import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;
import io.temporal.demos.utils.BankingClient;

public class AccountServicesActivities implements AccountServices {

    private final BankingClient bankingClient;

    public AccountServicesActivities(BankingClient bankingClient) {
        this.bankingClient = bankingClient;
    }

    @Override
    public void withdraw(Account from, Amount amount, String refId) {
        bankingClient.performWithdraw(from, amount, refId);
    }

    @Override
    public void deposit(Account to, Amount amount, String refId) {
        bankingClient.performDepositSimulateError(to, amount, refId);
    }
}
