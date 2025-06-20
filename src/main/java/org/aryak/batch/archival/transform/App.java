package org.aryak.batch.archival.transform;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aryak.batch.archival.model.OutputRecord;
import org.aryak.batch.archival.transform.engine.MappingEngine;
import org.aryak.batch.archival.transform.engine.MappingRule;
import org.aryak.batch.archival.transform.impl.ConditionalReplaceTransform;
import org.aryak.batch.archival.transform.impl.DateFormatTransform;
import org.aryak.batch.archival.transform.utils.JooqUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        OutputRecord outputRecord = new OutputRecord();
        JooqUtil.setNestedProperty(outputRecord, "nested1.a", "42");

        JooqUtil.setNestedProperty(outputRecord, "val1", "243434255");

        System.out.println(JooqUtil.getNestedProperty(outputRecord,
                "nested1.a"
        ));
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(outputRecord));

        var dobRule = new MappingRule(
                "dob",
                "nested1.a",
                List.of(
                        new DateFormatTransform("dd-MM-yyyy", "yyyy-MM-dd")
                )
        );

        var statusRule = new MappingRule(
                "status",
                "person.status",
                List.of(
                        new ConditionalReplaceTransform(
                                ctx -> "active".equals(ctx.get("status_code")), "Active"
                        ),
                        new ConditionalReplaceTransform(
                                ctx -> "inactive".equals(ctx.get("status_code")), "Inactive"
                        )
                )
        );


        // config defined
        List<MappingRule> rules = List.of(dobRule, statusRule);

        // test data prepared
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("dob", "05-12-1999");
        inputMap.put("status", "active");


        MappingEngine engine = new MappingEngine(rules);
        engine.map(inputMap, outputRecord);

        System.out.println(outputRecord);

    }


}
