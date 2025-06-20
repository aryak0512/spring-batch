package org.aryak.batch.archival;

import org.aryak.batch.archival.model.StudentCsv;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class SomeUsefulCode {

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
