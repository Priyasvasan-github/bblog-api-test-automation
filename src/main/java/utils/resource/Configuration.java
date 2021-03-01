package utils.resource;

public final class Configuration extends PropertiesReader {
    private static Configuration instance;
    private static final Object lockObject = new Object();

    private Configuration() {
        super("app.properties");
    }

    public static Configuration getInstance() {
        synchronized(lockObject) {
            if (instance == null) {
                instance = new Configuration();
            }
        }

        return instance;
    }
}
