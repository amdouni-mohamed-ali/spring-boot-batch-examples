package org.spring.tutorial.examples.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.tutorial.examples.batch.constants.ApplicationConstants;
import org.spring.tutorial.examples.batch.entities.OrderItem;
import org.spring.tutorial.examples.batch.entities.OrderItemTotal;
import org.spring.tutorial.examples.batch.generic.AbstractStep;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessDataStep extends AbstractStep {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataStep.class);
    private List<OrderItem> orderItems;
    private List<OrderItemTotal> orderItemTotals;

    @Override
    public void beforeStep(StepExecution stepExecution) {

        super.beforeStep(stepExecution);
        ExecutionContext context = stepExecution.getJobExecution().getExecutionContext();
        orderItems = (List<OrderItem>) context.get(ApplicationConstants.ORDERS_KEY);
        orderItemTotals = new ArrayList<>();
    }

    @Override
    protected void executeJob(StepContribution stepContribution, ChunkContext chunkContext) {

        //process data , calculate the price of each order
        orderItems.forEach(orderItem -> {
            orderItemTotals.add(
                    new OrderItemTotal()
                            .setOrderItemId(orderItem.getOrderItemId())
                            .setOrderItemSubTotal(orderItem.getOrderItemQuantity() * orderItem.getOrderItemProdcutPrice())
            );
        });
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put(ApplicationConstants.ORDERS_PRICE, orderItemTotals);
        LOGGER.debug("orders prices was successfully saved to the batch context");
        return super.afterStep(stepExecution);
    }

    @Override
    protected String getStepName() {
        return "ProcessDataStep";
    }
}
