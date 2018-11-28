package org.spring.tutorial.examples.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.tutorial.examples.batch.constants.ApplicationConstants;
import org.spring.tutorial.examples.batch.dao.IOrderItem;
import org.spring.tutorial.examples.batch.entities.OrderItem;
import org.spring.tutorial.examples.batch.generic.AbstractStep;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectDataStep extends AbstractStep {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectDataStep.class);
    private final IOrderItem iOrderItem;
    private List<OrderItem> orderItems;

    public CollectDataStep(IOrderItem iOrderItem) {
        this.iOrderItem = iOrderItem;
    }

    @Override
    protected void executeJob(StepContribution stepContribution, ChunkContext chunkContext) {

        //collect all data from database
        orderItems = iOrderItem.findAll();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put(ApplicationConstants.ORDERS_KEY, orderItems);
        LOGGER.debug("orders was successfully saved to the batch context");
        return super.afterStep(stepExecution);
    }

    @Override
    protected String getStepName() {
        return "CollectDataStep";
    }
}
