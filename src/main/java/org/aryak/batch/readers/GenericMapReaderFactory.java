package org.aryak.batch.readers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.config.ClientMetadata;
import org.aryak.batch.model.Client;
import org.aryak.batch.model.InputRecord;
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
@RequiredArgsConstructor
public class GenericMapReaderFactory {

    private final ClientMetadata clientMetadata;

    private final Map<String, FlatFileItemReader<InputRecord>> readerCache = new ConcurrentHashMap<>();

    public FlatFileItemReader<InputRecord> getOrCreateReader(Client config) {
        return readerCache.computeIfAbsent(config.getClientId(), id -> createReader(config));
    }

    private FlatFileItemReader<InputRecord> createReader(Client config) {

        FlatFileItemReader<InputRecord> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(config.getFilePath()));
        reader.setLinesToSkip(config.getLinesToSkip());

        // specify delimiter and excel fields
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(config.getDelimiter());
        tokenizer.setNames(config.getColumnNames().toArray(new String[0]));

        var lineMapper = getInputRecordDefaultLineMapper(config, tokenizer);
        reader.setLineMapper(lineMapper);

        try {
            reader.afterPropertiesSet();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize reader", e);
        }

        return reader;
    }

    private DefaultLineMapper<InputRecord> getInputRecordDefaultLineMapper(Client config, DelimitedLineTokenizer tokenizer) {
        DefaultLineMapper<InputRecord> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);

        lineMapper.setFieldSetMapper(fieldSet -> {

            // mappings get loaded here
            var clientMapping = clientMetadata.getClientMapping(config.getId());

            // file data gets loaded here
            Map<String, String> fileData = new LinkedHashMap<>();
            for (String name : fieldSet.getNames()) {
                fileData.put(name, fieldSet.readString(name));
            }

            InputRecord inputRecord = new InputRecord();
            inputRecord.setClientId(config.getId());
            inputRecord.setData(fileData);
            return inputRecord;
        });
        return lineMapper;
    }
}

