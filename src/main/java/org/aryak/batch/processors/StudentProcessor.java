package org.aryak.batch.processors;

import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.model.StudentCsv;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StudentProcessor implements ItemProcessor<StudentCsv, String> {

    @Override
    public String process(StudentCsv item) throws Exception {

        log.info("I'm simply printing it : {}", item);
        return item.toString();
    }
}
