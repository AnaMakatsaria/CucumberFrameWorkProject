package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //this method will read the data from any file, by providing the path in parameters
    public static String read(String key,String path) throws IOException {
        FileInputStream fis=new FileInputStream(path);
        Properties properties= new Properties();
        properties.load(fis);
        return properties.getProperty(key);
    }
    //this overloaded method will only read the data from config.properties,
    // and it can get the  path of the config.properties we've stored as global variables in ConstantsClass
    //so in case the path changes we can change it in only one place
    //and instead of repeating the same code we can use read method here, we are calling method inside the method
    public static String read(String key) throws IOException {
        return read(key,Constants.CONFIG_FILE_PATH);
    }
    }
