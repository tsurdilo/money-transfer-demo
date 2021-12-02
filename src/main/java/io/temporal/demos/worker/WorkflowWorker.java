package io.temporal.demos.worker;

import io.temporal.demos.activities.AccountServicesActivities;
import io.temporal.demos.utils.BankingClient;
import io.temporal.demos.workflow.MoneyTransferWorkflow;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import static io.temporal.demos.utils.Utils.client;
import static io.temporal.demos.utils.Utils.workflowTaskQueue;

public class WorkflowWorker {
    public static void main(String[] args) {

        WorkerFactory workerFactory = WorkerFactory.newInstance(client);
        Worker worker = workerFactory.newWorker(workflowTaskQueue);

        // Banking client which is to be passed to activities
        BankingClient bankingService = new BankingClient();

        // register workflow and activities impls
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflow.class);
        worker.registerActivitiesImplementations(new AccountServicesActivities(bankingService));

        workerFactory.start();
    }
}
