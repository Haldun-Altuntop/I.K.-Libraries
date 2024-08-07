package arc.haldun.ik.database;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConfig implements Serializable {

    private static final String FILE_NAME = "external/config";

    private String areaName;
    private String databaseName;
    private String userName;
    private String password;

    public DatabaseConfig(String areaName, String databaseName, String userName, String password) {
        this.areaName = areaName;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Gets the Database Config from default location. (Resources - external/config)
     * @return Database Config with values in the file.
     */
    public static DatabaseConfig load() {

        InputStream fis;
        DatabaseConfig databaseConfig;

        fis = DatabaseConfig.class.getClassLoader().getResourceAsStream(DatabaseConfig.FILE_NAME);

        if (fis == null) {
            Logger.getGlobal().log(
                    Level.WARNING,
                    "Uygulama değiştirilmiş ya da hasar görmüş. Uygulamayı yeniden indirmeyi deneyin."
            );

            return null;
        }

        databaseConfig = load(fis);

        try {
            fis.close();
        } catch (IOException e) {
            Logger.getGlobal().log(
                    Level.WARNING,
                    String.format("Dosya akışı kapatılamadı. (Kaynak: %s)",FILE_NAME)
            );
        }

        return databaseConfig;
    }

    public static DatabaseConfig load (InputStream configFileInput) {

        ObjectInputStream objectInputStream;
        DatabaseConfig databaseConfig = null;

        try {

            objectInputStream = new ObjectInputStream(configFileInput);

            databaseConfig = (DatabaseConfig) objectInputStream.readObject();

            objectInputStream.close();

        } catch (IOException e) {
            Logger.getGlobal().log(
                    Level.WARNING,
                    e.getMessage()
            );
        } catch (ClassNotFoundException e) {
            Logger.getGlobal().log(
                    Level.WARNING,
                    "Uygulama değiştirilmiş ya da hasar görmüş. Uygulamayı yeniden indirmeyi deneyin."
            );
        }

        return databaseConfig;
    }

    public static DatabaseConfig load(String configFilePath) {

        InputStream inputStream;
        DatabaseConfig databaseConfig = null;

        try {

            inputStream = new FileInputStream(configFilePath);
            databaseConfig = load(inputStream);

            inputStream.close();

        } catch (IOException e) {
            Logger.getGlobal().log(
                    Level.WARNING,
                    e.getMessage()
            );
        }
        return databaseConfig;
    }

    public void save(String outputFileName) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);

            objectOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void save() {
        save("config");
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
