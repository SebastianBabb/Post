package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Logger provides a variety of methods to output information to the screen
 * or file.
 * 
 * @author Gary Ng
 */
public abstract class Logger {

    public abstract void output(String output);

    public void outputToConsole(String output) {
        System.out.println(output);
    }

    public void outputToFile(String path, String output) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path, true));
            out.write(output);
            out.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
