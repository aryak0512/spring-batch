package org.aryak.batch.readers;

import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.model.Client;
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

    public FlatFileItemReader<Map<String, String>> getOrCreateReader(Client config) {
        return readerCache.computeIfAbsent(config.getClientId(), id -> createReader(config));
    }

    private FlatFileItemReader<Map<String, String>> createReader(Client config) {

        FlatFileItemReader<Map<String, String>> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(config.getFilePath()));
        reader.setLinesToSkip(config.getLinesToSkip());

        // specify delimiter and excel fields
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(config.getDelimiter());
        tokenizer.setNames(config.getColumnNames().toArray(new String[0]));

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

        return reader;
    }
}

