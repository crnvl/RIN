package dev.angelsflyinhell.rin.tools.props;

import java.io.*;
import java.util.Properties;

public class QHSave {

    public static Properties properties;
    static File file = new File("quickhomes.rin");

    public static void init(){
        properties = new Properties();
        try {
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            fileNotFoundAction(file);
        }
    }

    public static boolean propExist(String key) {
        if (properties.getProperty(key) == null) {
            return false;
        } else {
            return true;
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    public static void addKey(String hash, String name) {
        properties.put(hash, name);
        try {
            properties.store(new FileOutputStream(file), "quickhomes savefile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteKey(String hash) {
        properties.remove(hash);
    }

    private static void fileNotFoundAction(File f){
        try {
            properties.store(new FileOutputStream(f), "quickhomes savefile");
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    public static int getKeySize() {
        return properties.size();
    }
}
