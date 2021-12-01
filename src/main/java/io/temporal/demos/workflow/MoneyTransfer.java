package io.temporal.demos.workflow;

import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MoneyTransfer {
    @WorkflowMethod
    void transfer(Account from, Account to, String refId, Amount amount);
}
