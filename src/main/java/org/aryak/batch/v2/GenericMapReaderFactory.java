package org.aryak.batch.v2;

import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.model.ClientConfig;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class GenericMapReaderFactory {

    private final Map<String, FlatFileItemReader<Map<String, String>>> readerCache = new ConcurrentHashMap<>();

    public FlatFileItemReader<Map<String, String>> getOrCreateReader(ClientConfig config) {
        return readerCache.computeIfAbsent(config.clientId(), id -> createReader(config));
    }

    private FlatFileItemReader<Map<String, String>> createReader(ClientConfig config) {

        FlatFileItemReader<Map<String, String>> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(config.filePath()));
        reader.setLinesToSkip(config.linesToSkip());

        // specify delimiter and excel fields
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(config.delimiter());
        tokenizer.setNames(config.columnNames().toArray(new String[0]));

        DefaultLineMapper<Map<String, String>> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);

        lineMapper.setFieldSetMapper(fieldSet -> {
            Map<String, String> map = new LinkedHashMap<>();
            for (String name : fieldSet.getNames()) {
                map.put(name, fieldSet.readString(name));
            }
            return map;
        });

        reader.setLineMapper(lineMapper);
        try {
            reader.afterPropertiesSet();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize reader", e);
        }

        reader.setSkippedLinesCallback(line -> {
            log.info("Skipping line : {}", line);
        });
        return reader;
    }
}

