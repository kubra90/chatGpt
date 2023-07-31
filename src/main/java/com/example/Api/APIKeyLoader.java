package com.example.Api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

    public class APIKeyLoader {
        public static String getAPIKey() {
            Properties prop = new Properties();
            try (InputStream input = new FileInputStream("application.properties")) {
                prop.load(input);
                return prop.getProperty("API_KEY");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
