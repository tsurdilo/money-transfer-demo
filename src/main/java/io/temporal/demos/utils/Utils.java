package io.temporal.demos.utils;

import io.temporal.api.history.v1.HistoryEvent;
import io.temporal.api.workflow.v1.WorkflowExecutionInfo;
import io.temporal.api.workflowservice.v1.DescribeWorkflowExecutionRequest;
import io.temporal.api.workflowservice.v1.DescribeWorkflowExecutionResponse;
import io.temporal.api.workflowservice.v1.GetWorkflowExecutionHistoryRequest;
import io.temporal.api.workflowservice.v1.GetWorkflowExecutionHistoryResponse;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.util.Optional;

public class Utils {
    // Initializes all gRPC stubs (connection, blocking, future)
    // Note: by default target set to 127.0.0.1:7233, can change via workflowServiceStubsOptions
    public static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();

    // Client to the Temporal service used to start and query workflows by external processes
    // Note: by default set to "default" namespace, can change via WorkfowClientOptions
    public static final WorkflowClient client = WorkflowClient.newInstance(service);

    // workflow id
    public static final String workflowId = "moneytransferworkflow";

    // task queues (server "end-points") that workers listen to
    public static final String workflowTaskQueue = "moneyTransfer";
    public static final String withdrawTaskQueue = "moneyTransferWithdraw";
    public static final String depositTaskQueue = "moneyTransferDeposit";

    public static String getWorkflowStatus(WorkflowClient client, String workflowId) {
        WorkflowStub existingUntyped = client.newUntypedWorkflowStub(workflowId, Optional.empty(), Optional.empty());
        DescribeWorkflowExecutionRequest describeWorkflowExecutionRequest =
                DescribeWorkflowExecutionRequest.newBuilder()
                        .setNamespace(client.getOptions().getNamespace())
                        .setExecution(existingUntyped.getExecution())
                        .build();

        DescribeWorkflowExecutionResponse resp =
                client.getWorkflowServiceStubs().blockingStub().describeWorkflowExecution(describeWorkflowExecutionRequest);

        WorkflowExecutionInfo workflowExecutionInfo = resp.getWorkflowExecutionInfo();
        return workflowExecutionInfo.getStatus().toString();
    }

    public static void printWorkflowExecutionHistory(String workflowId) {
        System.out.println("> Workflow execution history: \n");
        WorkflowStub existingUntyped = client.newUntypedWorkflowStub(workflowId, Optional.empty(), Optional.empty());

        GetWorkflowExecutionHistoryRequest getWorkflowExecutionHistoryRequest =
                GetWorkflowExecutionHistoryRequest.newBuilder()
                        .setNamespace("default")
                        .setExecution(existingUntyped.getExecution())
                        .build();
        GetWorkflowExecutionHistoryResponse getWorkflowExecutionHistoryResponse =
                service.blockingStub().getWorkflowExecutionHistory(getWorkflowExecutionHistoryRequest);
        for(HistoryEvent historyEvent : getWorkflowExecutionHistoryResponse.getHistory().getEventsList()) {
            System.out.println(historyEvent.getEventId() + ": " + historyEvent.getEventType());
        }
    }
}
