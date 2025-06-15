package org.aryak.batch.readers;

import org.aryak.batch.model.StudentCsv;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class StudentReader extends FlatFileItemReader<StudentCsv> {

    @Override
    public void setLinesToSkip(int linesToSkip) {
        super.setLinesToSkip(1);
    }

    @Override
    public void setLineMapper(LineMapper<StudentCsv> lineMapper) {

        super.setLineMapper(new DefaultLineMapper<>() {

            @Override
            public void setLineTokenizer(LineTokenizer tokenizer) {

                super.setLineTokenizer(new DelimitedLineTokenizer() {

                    @Override
                    public void setDelimiter(String delimiter) {
                        super.setDelimiter(DELIMITER_COMMA); // default is comma
                    }

                    @Override
                    public void setNames(String... names) {
                        super.setNames("id", "Name", "Email Id"); // specify columns in CSV here
                    }
                });
            }

            @Override
            public void setFieldSetMapper(FieldSetMapper<StudentCsv> fieldSetMapper) {
                super.setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    @Override
                    public void setTargetType(Class<? extends StudentCsv> type) {
                        super.setTargetType(StudentCsv.class);
                    }
                });
            }
        });
    }

    @Override
    public void setResource(Resource resource) {
        super.setResource(new FileSystemResource(Path.of("")) {
        });
    }
}
