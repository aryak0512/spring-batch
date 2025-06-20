package org.aryak.batch.utils;

import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.model.Client;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Component
@Slf4j
public class Util {

    public static List<Client> getClientConfigs() {

        var client1 = new Client(
                1L,
                "Client 1",
                "c1",
                "/Users/aryak/Downloads/batch-processor/MOCK_DATA.csv",
                ",",
                List.of("id", "first_name", "last_name", "email", "gender", "ip_address"),
                1,
                3,
                2,
                2
        );
        var client2 = new Client(
                2L,
                "Client 2",
                "c2",
                "/Users/aryak/Downloads/batch-processor/MOCK_DATA_student.csv",
                ",",
                List.of("id", "Name", "Email id"),
                1,
                3,
                2,
                2);

        return List.of(client1, client2);
    }

    public static Client getTestClient() {
        Client config = new Client();
        config.setFilePath("/Users/aryak/Downloads/batch-processor/MOCK_DATA_student.csv");
        config.setColumnNames(List.of("id", "Name", "Email id"));
        return config;
    }

    public int computeSkipLimit(Client client) {
        int lines = getLinesFromFile(client.getFilePath());
        if (lines > 0)
            return (int) ((client.getSkipLimit() / 100.0) * lines);
        return client.getSkipLimit();
    }

    public int getLinesFromFile(String pathOfFile) {
        try {
            return (int) Files.lines(new File(pathOfFile).toPath()).count();
        } catch (Exception e) {
            log.error("Exception occurred : ", e);
            return 0;
        }
    }
}
