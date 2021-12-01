package io.temporal.demos.worker;

import io.temporal.demos.activities.AccountServicesActivities;
import io.temporal.demos.utils.BankingService;
import io.temporal.demos.workflow.MoneyTransferWorkflow;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import static io.temporal.demos.utils.Utils.client;
import static io.temporal.demos.utils.Utils.workflowTaskQueue;

public class WorkflowWorker {
    public static void main(String[] args) {

        WorkerFactory workerFactory = WorkerFactory.newInstance(client);
        Worker worker = workerFactory.newWorker(workflowTaskQueue);

        // Banking service which is to be passed to activities
        BankingService bankingService = new BankingService();

        // Can be called multiple times
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflow.class);
        worker.registerActivitiesImplementations(new AccountServicesActivities(bankingService));

        workerFactory.start();
    }
}
