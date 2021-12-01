package io.temporal.demos.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.demos.activities.BankingServices;
import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class MoneyTransferWorkflow implements MoneyTransfer {

    private final BankingServices activities =
            Workflow.newActivityStub(
                    BankingServices.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5))
                            .setRetryOptions(
                                    RetryOptions.newBuilder()
                                            // setting backoff coefficient only for demo purposes
                                            // to not have to wait for a long time after a number of retries
                                            .setBackoffCoefficient(1).build()
                            ).build());

    @Override
    public void transfer(Account from, Account to, String refId, Amount amount) {
        activities.withdraw(from, amount, refId);
        activities.deposit(to, amount, refId);
    }
}
