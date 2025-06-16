package org.aryak.batch.v2.utils;

import org.aryak.batch.model.ClientConfig;

import java.util.List;

public class Util {

    public static List<ClientConfig> getClientConfigs() {

        var client1 = new ClientConfig(
                "c1",
                "/Users/aryak/Downloads/batch-processor/MOCK_DATA.csv",
                ",",
                List.of("id", "first_name", "last_name", "email", "gender", "ip_address"),
                1,
                3);
        var client2 = new ClientConfig(
                "c2",
                "/Users/aryak/Downloads/batch-processor/MOCK_DATA_student.csv",
                ",",
                List.of("id", "Name", "Email id"),
                1,
                3);

        return List.of(client1, client2);
    }
}
