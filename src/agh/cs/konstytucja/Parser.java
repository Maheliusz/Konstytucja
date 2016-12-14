package agh.cs.konstytucja;

import java.io.*;

/**
 * Created by Michał Zakrzewski on 2016-12-01.
 */
public class Parser {
    Constitution constitution;
    String folder = "C://Documents/Moje/Java/Konstytucja/src/agh/cs/konstytucja/";
    String path = "konstytucja.txt";
    String prevLine = new String();
    Chapter currentChapter;
    Article currentArticle;
    int chapNum = 1;
    int artNum = 1;

    public Parser(Constitution constitution) {
        this.constitution = constitution;
    }

    public Constitution getConstitution() {
        return this.constitution;
    }

    public void check(String line) {
        if (line.startsWith("Rozdział")) {
            currentChapter = new Chapter();
            //currentChapter.number = getLineNum(line);
            currentChapter.number = chapNum++;
            constitution.chapters.add(currentChapter);
        } else if (line.startsWith("Art.")) {
            currentArticle = new Article();
            //currentArticle.number = getLineNum(line);
            currentArticle.number = artNum++;
            currentChapter.articles.add(currentArticle);
            constitution.articles.add(currentArticle);

        } else if (prevLine.startsWith("Rozdział")) {
            currentChapter.title = line;
        } else if (line.startsWith("\u00a9")                       //copyright
                || prevLine.startsWith("\u00a9")                 //linijka ponizej copyrightu
                || line.toUpperCase().equals(line)
                || currentChapter == null) {                    //srodtytuly rozdzialow
        }                                                       //ignorujemy
        else {
            currentArticle.text.add(line);
        }

        prevLine = line;
    }

    private int getLineNum(String line) {
        char c;
        int resArt = 0;
        String resChap = new String();
        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);
            if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9 && c != '.') {
                resArt *= 10;
                resArt += (int) c;
            } else if (c == '.' && resArt != 0) break;
        }
        for (int i = 9; i < line.length(); i++) {
            c = line.charAt(i);
            resChap += c;
        }

        return resArt != 0 ? resArt : toArabic(resChap);
    }

    private int toArabic(String resChap) {
        char c = ' ';
        char b = ' ';
        int res = 0;
        for (int i = 0; i < resChap.length(); i++) {
            c = resChap.charAt(i);
            if (c == 'M' && b == 'C')
                res += 800;
            else if (c == 'D' && b == 'C')
                res += 300;
            else if (c == 'C' && b == 'X')
                res += 80;
            else if (c == 'L' && b == 'x')
                res += 30;
            else if (c == 'X' && b == 'I')
                res += 8;
            else if (c == 'V' && b == 'I')
                res += 3;
            else if (c == 'M')
                res += 1000;
            else if (c == 'D')
                res += 500;
            else if (c == 'C')
                res += 100;
            else if (c == 'L')
                res += 50;
            else if (c == 'X')
                res += 10;
            else if (c == 'V')
                res += 5;
            else if (c == 'I')
                res += 1;

            b = c;
        }
        return res;
    }

    public void parse() {
        try (BufferedReader br = new BufferedReader(new FileReader(folder + path))) {
            for (String line; (line = br.readLine()) != null; ) {
                check(line);
            }
        } catch (IOException e) {
            System.out.println("Problem z odczytaniem pliku " + e.getMessage());
        }
        constitution.articles.forEach(this::joinWordPartsRunner);
    }

    private void joinWordPartsRunner(Article article) {
        String firstline;
        String secondline;
        firstline = article.text.get(0);
        try {
            secondline = article.text.get(1);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        if (firstline.endsWith("-\n"))
            joinWordParts(firstline, secondline);
        //article.text.set(0, firstline);
        //article.text.set(1, secondline);
        firstline = secondline;
        for (int i = 2; i < article.text.size(); i++) {
            secondline = article.text.get(i);
            if (firstline.endsWith("-\n"))
                joinWordParts(firstline, secondline);
            //article.text.set(i - 1, firstline);
            //article.text.set(i, secondline);
            firstline = secondline;
        }
    }

    private void joinWordParts(String firstline, String secondline) {
        String wordPart = new String();
        char c;
        int i = 0;

        for (c = secondline.charAt(i); c != ' ' && c != '\n'; i++) {
            wordPart += c;
        }
        firstline = firstline.substring(0, firstline.length() - 3) + wordPart;
        secondline = secondline.substring(wordPart.length() + 1, secondline.length() - 1);

    }

}
