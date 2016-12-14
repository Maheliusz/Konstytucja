package agh.cs.konstytucja;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał Zakrzewski on 2016-12-09.
 */
public class ShowTime {
    String[] args;
    Constitution constitution;
    private List<Integer> chapterShown = new LinkedList<>();
    private List<Integer> articleShown = new LinkedList<>();

    public ShowTime(String[] args, Constitution constitution) {
        this.args = args;
        this.constitution = constitution;
    }

    public void parse() {
        boolean isChapter = false;
        for (String arg : args) {
            if (arg.toLowerCase().equals("-r")) {
                isChapter = true;
            } else if (arg.toLowerCase().equals("-c")) {
                isChapter = false;
            } else if (isChapter) {
                parseChap(arg);
            } else {
                parseArt(arg);
            }
        }
        show();
    }

    private void parseChap(String arg) {
        char c;
        int res = 0;
        for (int i = 0; i < arg.length(); i++) {
            c = arg.charAt(i);
            if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9)
                res += Character.getNumericValue(c);
        }
        chapterShown.add(res - 1);
    }

    private void parseArt(String arg) {
        char c;
        int start = 0;
        int end = 0;
        for (int i = 0; i < arg.length(); i++) {
            c = arg.charAt(i);
            end *= 10;
            if (c == '-') {
                start = end;
                end = 0;
            }
            else if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9)
                end += Character.getNumericValue(c);
             else return;
        }
        for (int i = start; i <= end; i++)
            articleShown.add(i);
        //show(start, end);
    }

    private void show() {
        //chapterShown.stream().filter(i -> i < constitution.chapters.size()).forEach(i -> constitution.chapters.get(i).print());
        //articleShown.stream().filter(i -> i < constitution.articles.size()).forEach(i -> constitution.articles.get(i).print());
    }

}
