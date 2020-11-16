package fr.univtln.bruno.samples;

import lombok.extern.java.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Hello world!
 */
@Log
public class App {
    private static Properties properties = new Properties();

    public static void loadProperties(String propFileName) throws IOException {
        InputStream inputstream = App.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputstream == null) throw new FileNotFoundException();
        properties.load(inputstream);
    }

    static {
        try {
            loadProperties("app.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String s) {
        return properties.getProperty(s);
    }

    public static void main(String[] args) throws SQLException, InterruptedException {

        log.info("Starting...");
        Thread.sleep(2000);

        Connection connection = DBCPDataSource.getConnection();
        DatabaseMetaData dbmd = connection.getMetaData();
        log.info("Connected to :"+dbmd.getDatabaseProductName()+" "+dbmd.getDatabaseProductVersion());

    }

}
