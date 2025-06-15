package org.aryak.batch.config;

import org.aryak.batch.model.StudentCsv;
import org.aryak.batch.processors.StudentProcessor;
import org.aryak.batch.writers.StudentWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StudentJob {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;
    private final StudentWriter studentWriter;
    private final StudentProcessor studentProcessor;

    public StudentJob(PlatformTransactionManager platformTransactionManager, JobRepository jobRepository, StudentWriter studentWriter, StudentProcessor studentProcessor) {
        this.platformTransactionManager = platformTransactionManager;
        this.jobRepository = jobRepository;
        this.studentWriter = studentWriter;
        this.studentProcessor = studentProcessor;
    }

    @Bean
    public Job studentFileReadingJob() {
        return new JobBuilder("Student File Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(readFromCsvFileAndPushJsonToQueue())
                .build();
    }

    private Step readFromCsvFileAndPushJsonToQueue() {
        return new StepBuilder("Chunked Step", jobRepository)
                .<StudentCsv, String>chunk(5, platformTransactionManager)
                .reader(studentReader2())
                .processor(studentProcessor)
                .writer(studentWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<StudentCsv> studentReader2() {
        FlatFileItemReader<StudentCsv> reader = new FlatFileItemReader<>();

        reader.setResource(new FileSystemResource("/MOCK_DATA_student.csv"));
        reader.setLinesToSkip(1); // skip header
        reader.setLineMapper(lineMapper());

        return reader;
    }

    private LineMapper<StudentCsv> lineMapper() {
        DefaultLineMapper<StudentCsv> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("id", "Name", "Email Id");

        BeanWrapperFieldSetMapper<StudentCsv> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(StudentCsv.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

}
