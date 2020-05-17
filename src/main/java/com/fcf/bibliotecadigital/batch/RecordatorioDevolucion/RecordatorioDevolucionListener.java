package com.fcf.bibliotecadigital.batch.RecordatorioDevolucion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class RecordatorioDevolucionListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(RecordatorioDevolucionProcessor.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus()== BatchStatus.COMPLETED){
            log.info("Finalizó el proceso batch");
        }else if(jobExecution.getStatus() == BatchStatus.FAILED){
            log.info("Falló la ejecución del proceso batch");
        }
    }
}
