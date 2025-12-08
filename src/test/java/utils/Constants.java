package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Constants {
    //HERE WE ARE GOING TO STORE GLOBAL VARIABLES
    //CONVENTION - whenever u create public static final _VARIABLE_ in java
       public static final String CONFIG_FILE_PATH =System.getProperty("user.dir") +"/src/test/resources/config/config.properties";
       public  static final int EXPLICIT_WAIT=20;
       public static final int IMPLICIT_WAIT=10;
}