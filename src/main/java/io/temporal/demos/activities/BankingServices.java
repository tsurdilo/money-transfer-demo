package io.temporal.demos.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;

@ActivityInterface
public interface BankingServices {
    void withdraw(Account from, Amount amount, String refId);

    void deposit(Account to, Amount amount, String refId);
}
