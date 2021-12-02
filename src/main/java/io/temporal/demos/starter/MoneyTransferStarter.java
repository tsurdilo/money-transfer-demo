package io.temporal.demos.starter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.demos.model.Account;
import io.temporal.demos.model.Amount;
import io.temporal.demos.workflow.MoneyTransfer;

import java.time.Duration;
import java.util.UUID;

import static io.temporal.demos.utils.Utils.*;


public class MoneyTransferStarter {

    public static void main(String[] args) {

        // "generate" a refId for transaction
        String refId = UUID.randomUUID().toString();
        // create some demo accounts and amount to be transferred
        Account fromAccount = new Account(1, "John");
        Account toAccount = new Account(2, "Merry");
        Amount amount = new Amount("USD", 100);

        // Create workflow stub and set options
        MoneyTransfer workflow = client.newWorkflowStub(
                MoneyTransfer.class,
                WorkflowOptions.newBuilder()
                        // set business-level id
                        .setWorkflowId(workflowId)
                        // set the task queue for the workflow execution
                        .setTaskQueue(workflowTaskQueue)
                        // can set workflow execution timeouts, by default its "infinite"
                        //.setWorkflowExecutionTimeout(Duration.ofMinutes(5))
                        .build()
        );

        // start workflow execution (dont wait for results)
        WorkflowClient.start(workflow::transfer, fromAccount, toAccount, refId, amount);
        System.out.println("> Workflow " + workflowId + " started. ");
        printWorkflowStatus();

        // Connect to WF execution and get result using untyped stub
        WorkflowStub untyped = WorkflowStub.fromTyped(workflow);
        untyped.getResult(Void.class);

        System.out.println("> Workflow " + workflowId + " completed. ");
        printWorkflowStatus();
    }

    private static void printWorkflowStatus() {
        System.out.println("> Workflow status: " + getWorkflowStatus(client, workflowId));
    }
}
