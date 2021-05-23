package com.oizovita.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.InputStream;
import java.util.Properties;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start program");

        Properties property = new Properties();

        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("properties");
            property.load(in);

            String username = property.getProperty("username", "oizovita");
            boolean xml = Boolean.parseBoolean(property.getProperty("xml"));

            ObjectMapper mapper = xml ? new XmlMapper() : new JsonMapper();

            ObjectNode data = mapper.createObjectNode();

            data.put("message", "Hi " + username + "!");

            String result = mapper.writeValueAsString(data);

            System.out.println(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        logger.info("End program");
    }
}
