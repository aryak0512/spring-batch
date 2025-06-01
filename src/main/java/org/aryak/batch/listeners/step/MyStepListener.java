package org.aryak.batch.listeners.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class MyStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

        log.info("Before : {}", stepExecution.getExecutionContext());
        // grabbing the step execution context and updating it
        stepExecution.getExecutionContext().put("test", "123");

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        log.info("After : {}", stepExecution.getExecutionContext());
        return null;
    }
}
