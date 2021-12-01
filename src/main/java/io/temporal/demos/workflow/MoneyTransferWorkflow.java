package io.temporal.demos.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.demos.activities.AccountServices;
import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class MoneyTransferWorkflow implements MoneyTransfer {

    private final AccountServices accounts =
            Workflow.newActivityStub(
                    AccountServices.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2))
                            .setRetryOptions(RetryOptions.newBuilder()
                                    .setMaximumInterval(Duration.ofSeconds(10)).build()).build());

    @Override
    public void transfer(Account from,
                         Account to,
                         String refId,
                         Amount amount) {

        accounts.withdraw(from, amount, refId);
        accounts.deposit(to, amount, refId);
    }
}
