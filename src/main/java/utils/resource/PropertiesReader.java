package utils.resource;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public abstract class PropertiesReader {
    private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);
    protected Properties properties = new Properties();

    public PropertiesReader(String fileName) {
        try {
            this.load(fileName);
        } catch (Exception var3) {
            log.error("[Exception caught " + var3.getClass().getName() + "] " + var3.getMessage());
        }

    }

    public void load(String propertiesFile) throws IOException {
        if (!FilenameUtils.isExtension(propertiesFile, "properties")) {
            throw new IOException("Cannot load " + propertiesFile + "! The file is not a valid properties file");
        } else {
            InputStream in = Configuration.class.getClassLoader().getResourceAsStream(propertiesFile);
            Throwable var3 = null;

            try {
                if (in != null) {
                    this.properties.load(in);
                } else {
                    log.error("Unable to load the file '" + propertiesFile + "'! - The file does not exist");
                }
            } catch (Throwable var12) {
                var3 = var12;
                throw var12;
            } finally {
                if (in != null) {
                    if (var3 != null) {
                        try {
                            in.close();
                        } catch (Throwable var11) {
                            var3.addSuppressed(var11);
                        }
                    } else {
                        in.close();
                    }
                }

            }

        }
    }

    public String getProperty(String key) {
        return this.properties.containsKey(key) ? this.properties.getProperty(key) : null;
    }

    public <T> T getProperty(String key, Class<T> type) {
        if (this.properties.containsKey(key)) {
            return type == Boolean.class ? type.cast(Boolean.valueOf(this.properties.getProperty(key))) : type.cast(this.properties.getProperty(key));
        } else {
            return null;
        }
    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public boolean containsKey(String key) {
        return this.properties.containsKey(key);
    }

    public boolean containsValue(String value) {
        return this.properties.contains(value);
    }


    public Map<String, Object> toMap() {
        return (Map)this.properties.entrySet().stream().collect(Collectors.toMap((e) -> {
            return e.getKey().toString();
        }, (e) -> {
            return e.getValue().toString();
        }));
    }
}

