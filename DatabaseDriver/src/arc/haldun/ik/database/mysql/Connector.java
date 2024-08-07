package arc.haldun.ik.database.mysql;

import arc.haldun.ik.database.DatabaseConfig;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

    public static Connection connection;

    public static void connect() {

        try {
            DatabaseConfig databaseConfig = DatabaseConfig.load("test-config");

            if (databaseConfig == null) {

                String msg = "Veritabanı bilgileri yüklenemedi.";

                Logger.getGlobal().log(
                        Level.WARNING,
                        msg
                );

                JOptionPane.showMessageDialog(
                        null,
                        msg,
                        "Hata!",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String connectionURL = String.format(
                    "jdbc:mysql://%s/%s?characterEncoding=UTF-8",
                    databaseConfig.getAreaName(),
                    databaseConfig.getDatabaseName()
            );

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    connectionURL,
                    databaseConfig.getUserName(),
                    databaseConfig.getPassword()
            );

        } catch (ClassNotFoundException e) {

            String msg = "Uygulama değiştirilmiş ya da hasar görmüş. " +
                    "Gerekli kütüphaneler bulunamadı. " +
                    "Uygulamayı yeniden indirmeyi deneyin.";

            Logger.getGlobal().log(
                    Level.WARNING,
                    msg
            );

            JOptionPane.showMessageDialog(
                    null,
                    msg,
                    "Hata!",
                    JOptionPane.ERROR_MESSAGE
            );

            System.exit(1);

        } catch (SQLException e) {
            Logger.getGlobal().log(
                    Level.WARNING,
                    e.getMessage()
            );

            System.exit(1);
        }
    }

    public static void disconnect() {
        try {
            if (connection == null || connection.isClosed())
                Logger.getGlobal().log(Level.FINE, "Veritabanına zaten bağlı değilsiniz.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException | NullPointerException e) {
            return false;
        }
    }
}
