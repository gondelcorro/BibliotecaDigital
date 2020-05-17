package com.fcf.bibliotecadigital;

import com.fcf.bibliotecadigital.batch.RecordatorioDevolucion.RecordatorioDevolucionListener;
import com.fcf.bibliotecadigital.batch.RecordatorioDevolucion.RecordatorioDevolucionProcessor;
import com.fcf.bibliotecadigital.model.Prestamo;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private RecordatorioDevolucionListener listener;
    @Autowired
    private EntityManagerFactory emFactory;

    //Si el itemReader no tiene nada directamente no entra al processor
    @Bean
    public ItemReader<Prestamo> reader() {
        JpaPagingItemReader<Prestamo> iReader = new JpaPagingItemReader<>();
        iReader.setEntityManagerFactory(this.getEmFactory());
        LocalDate currentDate = LocalDate.now().plusDays(1);
        iReader.setQueryString(Prestamo.BUSCAR_PRESTAMOS_A_VENCER_QUERY); //NamedQuery de Prestamo
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fechaActual", currentDate);
        iReader.setParameterValues(parameters);
        iReader.setPageSize(5);
        return iReader;
    }

    @Bean
    public RecordatorioDevolucionProcessor<Prestamo, Prestamo> processor() {
        return new RecordatorioDevolucionProcessor<>();
    }

    @Bean
    public ItemWriter<Prestamo> writer() {
        JpaItemWriter<Prestamo> iwriter = new JpaItemWriter<>();
        iwriter.setEntityManagerFactory(this.getEmFactory());
        return iwriter;
    }

    //A las 9 de la mañ, todos los dias del mes de todos los meses cualq dia de la semana cualq año.
    @Scheduled(cron = "0 0 9 * * ?") // cada minuto  0 */1 * * * ?  (para pruebas)
    public void enviarMailRecordatorioDevibro() throws Exception {
        System.out.println(" Job Started...");
        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(job(), param);
        System.out.println("Job finished with status :" + execution.getStatus());
    }

    public Job job() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Prestamo, Prestamo> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
/*
    @Bean
    public Job recordatorioDevolucionJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
        return jobs.get("recordatorioDevolucionJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory,
                      ItemReader<Prestamo> reader, ItemWriter<Prestamo> writer,
                      ItemProcessor<Prestamo, Prestamo> processor) {
        return stepBuilderFactory.get("step1")
                .<Prestamo, Prestamo> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }*/

    public EntityManagerFactory getEmFactory() {
        return emFactory;
    }

    public void setEmFactory(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

}
