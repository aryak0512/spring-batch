package org.aryak.batch.listeners.skip;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;

import java.util.Map;

@Slf4j
public class MySkipListener implements SkipListener<Map<String, String>, String> {

    @Override
    public void onSkipInRead(Throwable t) {
        log.error("Skipped during READ. Exception was : ", t);
    }

    @Override
    public void onSkipInWrite(String item, Throwable t) {
        log.error("Skipped during WRITE. Item was : {} Exception was : ", item, t);

    }

    @Override
    public void onSkipInProcess(Map<String, String> item, Throwable t) {
        log.error("Skipped during PROCESS. Item was : {} Exception was : ", item, t);

    }
}
