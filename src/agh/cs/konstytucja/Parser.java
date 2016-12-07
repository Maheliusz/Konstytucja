package agh.cs.konstytucja;

import java.io.*;
import java.nio.file.Path;

/**
 * Created by Michał Zakrzewski on 2016-12-01.
 */
public class Parser {
    Constitution constitution;
    String path = "konstytucja.txt";
    String prevLine = new String();
    Chapter currentChapter;
    Article currentArticle;

    public Parser(Constitution constitution){
        this.constitution = constitution;
    }

    public Constitution getConstitution(){
        return this.constitution;
    }

    public void check(String line){
        if(prevLine.startsWith("Rozdział")){

        }
        if(line.startsWith("Rozdział")){
            currentChapter = new Chapter();
            currentChapter.number = getLineNum(line);
        }
        if(line.startsWith("Art. ")){

        }

        line = prevLine;
    }

    private int getLineNum(String line) {
        char c;
        String resArt = new String();
        String resChap = new String();
        for (int i = 0; i < line.length(); i++){
            c = line.charAt(i);
            if((int)c>=0&&(int)c<=9){
                resArt+=c;
            }
        }
        for (int i = 0; i < line.length(); i++){
            c = line.charAt(i);
            if(i>8){
                resChap+=c;
            }
        }

        return resArt!=null ? Integer.parseInt(resArt) : toArabic(resChap);
    }

    private int toArabic(String resChap) {
        char c;
        char b;
        int res=0;
        for (int i = 0; i < resChap.length(); i++){
            c = resChap.charAt(i);
            b=c;
        }
        return res;
    }

    public void parse() {
        int lineNum=0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String line; (line = br.readLine()) != null; lineNum++) {
                check(line);
            }
        } catch (IOException e) {
            System.out.println("Problem z odczytaniem pliku " + e.getMessage());
        }
    }

}
