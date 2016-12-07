package agh.cs.konstytucja;

import javax.sound.sampled.Line;
import java.io.*;
import java.nio.file.Path;

/**
 * Created by Michał Zakrzewski on 2016-12-01.
 */
public class Parser {

    public void check(String line){
        if(line.startsWith("Rozdział")){

        }
        if(line.startsWith("Art. ")){

        }
    }
    String path = "konstytucja.txt";
    //File fileName = new File("konstytucja.txt");


    public void parse() {
        int lineNum=0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String line; (line = br.readLine()) != null; lineNum++) {
                //check(line);
            }
        } catch (IOException e) {
            System.out.println("Problem z odczytaniem pliku " + e.getMessage());
        } finally {
        }
    }

}
