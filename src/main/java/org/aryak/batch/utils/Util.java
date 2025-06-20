package org.aryak.batch.utils;

import org.aryak.batch.model.ClientConfig;

import java.util.List;

public class Util {

    public static List<ClientConfig> getClientConfigs() {

        var client1 = new ClientConfig(
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
        var client2 = new ClientConfig(
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

    public static ClientConfig getTestClient() {
        ClientConfig config = new ClientConfig();
        config.setFilePath("/Users/aryak/Downloads/batch-processor/MOCK_DATA_student.csv");
        config.setColumnNames(List.of("id", "Name", "Email id"));
        return config;
    }
}
