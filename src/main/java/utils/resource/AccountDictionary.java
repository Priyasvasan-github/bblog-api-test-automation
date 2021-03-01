package utils.resource;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class AccountDictionary extends JsonReader<UserAccount> {
    private static final Logger log = LoggerFactory.getLogger(AccountDictionary.class);
    private static AccountDictionary instance;
    private static final Object lockObject = new Object();

    private AccountDictionary() {
        super("accounts.json");
    }

    public static AccountDictionary getInstance() {
        synchronized(lockObject) {
            if (instance == null) {
                instance = new AccountDictionary();
            }
        }

        return instance;
    }

    public void load(String fileName) throws IOException {
        InputStream in = Configuration.class.getClassLoader().getResourceAsStream(fileName);
        Throwable var3 = null;

        try {
            if (in != null) {
                Gson gson = new Gson();
                List<UserAccount> objects = new ArrayList();
                com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new InputStreamReader(in));
                reader.beginArray();

                while(reader.hasNext()) {
                    UserAccount account = (UserAccount)gson.fromJson(reader, UserAccount.class);
                    objects.add(account);
                }

                this.map = (Map)objects.stream().collect(Collectors.toMap(UserAccount::getId, (e) -> {
                    return e;
                }));
            } else {
                log.error("Unable to load the file '" + fileName + "'! - The file does not exist");
            }
        } catch (Throwable var15) {
            var3 = var15;
            throw var15;
        } finally {
            if (in != null) {
                if (var3 != null) {
                    try {
                        in.close();
                    } catch (Throwable var14) {
                        var3.addSuppressed(var14);
                    }
                } else {
                    in.close();
                }
            }

        }

    }
}

