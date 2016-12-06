package agh.cs.konstytucja;

import java.io.*;
import java.nio.file.Path;

/**
 * Created by Michał Zakrzewski on 2016-12-01.
 */
public abstract class AbstractParse {
    String fileName = new String("konstytucja.txt");

    public int parse(int lineNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                // process the line.
            }
        } catch (IOException e) {
            System.out.println("Problem z odczytaniem pliku " + e.getMessage());
        } finally {
        }
        return lineNumber;
    }
}
