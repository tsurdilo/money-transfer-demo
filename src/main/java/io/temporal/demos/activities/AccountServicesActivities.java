package io.temporal.demos.activities;

import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;
import io.temporal.demos.utils.BankingService;

public class AccountServicesActivities implements AccountServices {

    private final BankingService bankingService;

    public AccountServicesActivities(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @Override
    public void withdraw(Account from, Amount amount, String refId) {
        bankingService.performWithdraw(from, amount, refId);
    }

    @Override
    public void deposit(Account to, Amount amount, String refId) {
        bankingService.performDeposit(to, amount, refId);
    }
}