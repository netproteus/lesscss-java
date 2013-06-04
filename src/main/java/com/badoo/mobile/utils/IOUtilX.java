// $Id$
// Copyright 2012 Badoo Ltd
package com.badoo.mobile.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class IOUtilX {

    public static String loadFromStream(final InputStream stream) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }

            return builder.toString();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return "";
    }    
    
    public static String loadResource(String name) {
        return loadResource(name, null);
    }
    
    public static String loadResource(String name, Class<?> relative) {
        if (relative == null) {
            relative = IOUtilX.class;
        }
        
        return loadFromStream(relative.getResourceAsStream(name));
    }
    
    public static String loadFile(File f) throws FileNotFoundException {
        return loadFromStream(new FileInputStream(f));
    }
    
    public static void writeToFile(File f, String output) throws IOException {
        if (!f.getParentFile().isDirectory()) {
            f.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(f);
        try {
            fos.write(output.getBytes(Charset.forName("UTF-8")));
            fos.flush();
        }
        finally {
            fos.close();
        }
    }
}
