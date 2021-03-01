package utils.resource;


import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class JsonReader<T> {
    private static final Logger log = LoggerFactory.getLogger(JsonReader.class);
    protected Map<String, T> map = new HashMap();

    public JsonReader(String fileName) {
        try {
            if (!FilenameUtils.isExtension(fileName, "json")) {
                throw new IOException("Cannot load " + fileName + "! The file is not a valid json file");
            }

            this.load(fileName);
        } catch (Exception var3) {
            log.error("[Exception caught " + var3.getClass().getName() + "] " + var3.getMessage());
        }

    }

    public abstract void load(String var1) throws IOException;

    public T get(String key) {
        return this.map.containsKey(key) ? this.map.get(key) : null;
    }

}

