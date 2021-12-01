package io.temporal.demos.starter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;
import io.temporal.demos.workflow.MoneyTransfer;

import java.util.UUID;

import static io.temporal.demos.utils.Utils.*;


public class MoneyTransferStarter {

    public static void main(String[] args) {

        // "generate" a refId for transaction
        String refId = UUID.randomUUID().toString();
        Account fromAccount = new Account(1, "John");
        Account toAccount = new Account(2, "Merry");
        Amount amount = new Amount("USD", 100);

        MoneyTransfer workflow = client.newWorkflowStub(
                MoneyTransfer.class,
                WorkflowOptions.newBuilder()
                        .setWorkflowId(workflowId)
                        .setTaskQueue(workflowTaskQueue)
                        .build()
        );

        WorkflowClient.start(workflow::transfer, fromAccount, toAccount, refId, amount);

        System.out.println("> Workflow " + workflowId + " started. ");
        printWorkflowStatus();

        // still can connect to WF and get result using untyped:
        WorkflowStub untyped = WorkflowStub.fromTyped(workflow);
        untyped.getResult(Void.class);

        System.out.println("> Workflow " + workflowId + " completed. ");
        printWorkflowStatus();
        printWorkflowExecutionHistory(workflowId);
    }

    private static void printWorkflowStatus() {
        System.out.println("> Workflow status: " + getWorkflowStatus(client, workflowId));
    }
}
