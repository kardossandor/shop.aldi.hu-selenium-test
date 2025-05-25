import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("config.properties not found in classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
