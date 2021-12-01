package io.temporal.demos.utils;

import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;

import java.time.Duration;

public class BankingClient {
    public void performWithdraw(Account account, Amount amount, String refId) {
        // simulate some rest api call...
        // log
    }

    public void performDeposit(Account account, Amount amount, String refId) {
        // simulate some rest api call...
        // log
    }

    public void performDepositSimulateError(Account account, Amount amount, String refId) {
        // log ...
        throw new NullPointerException("Simulated exception during deposit!");
    }
}
