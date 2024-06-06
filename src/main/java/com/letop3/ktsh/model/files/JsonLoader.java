package com.letop3.ktsh.model.files;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonLoader implements Closeable {
    private final Map<?, ?> JSONDATA;
    private final File FILE;

    public JsonLoader(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FILE = new File(filePath);
        JSONDATA = mapper.readValue(FILE, Map.class);
    }

    public int[] getIntArray(String key) throws ClassCastException {
        if (JSONDATA == null || JSONDATA.isEmpty()) {
            return new int[0];
        }
        Object data = JSONDATA.get(key);
        if (data instanceof List<?> dataList) {
            return dataList.stream()
                    .mapToInt(i -> (Integer) i)
                    .toArray();
        } else {
            throw new ClassCastException("Les données associées à la clé '" + key + "' ne sont pas un tableau d'entiers.");
        }
    }

    @Override
    public void close() {
        JSONDATA.clear();
    }
}
