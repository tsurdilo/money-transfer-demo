package io.temporal.demos.utils;

import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;

import java.time.Duration;

public class BankingService {
    public void performWithdraw(Account account, Amount amount, String refId) {
        // simulate some rest api call...
        Utils.sleep(Duration.ofSeconds(2));
    }

    public void performDeposit(Account account, Amount amount, String refId) {
        // simulate some rest api call...
        Utils.sleep(Duration.ofSeconds(2));
    }

    public void performDepositSimulateError(Account account, Amount amount, String refId) {
        Utils.sleep(Duration.ofSeconds(2));
        throw new NullPointerException("Simulated exception during deposit!");
    }
}
