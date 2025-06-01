package org.aryak.batch.readers;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FirstReader implements ItemReader<Integer> {

    List<Integer> list = Arrays.asList(1, 3, 5, 6, 7, 8, 9, 9);
    int i = 0;

    @Override
    public Integer read() throws Exception {

        if (i < list.size()) {

            var val = list.get(i);
            i++;
            return val;
        }
        i = 0;
        return null;
    }
}
