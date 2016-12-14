package agh.cs.konstytucja;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał Zakrzewski on 2016-12-09.
 */
public class ShowTime {
    private String[] args;
    private Constitution constitution;
    private List<Integer> chapterShown = new LinkedList<>();
    private List<Integer> articleShown = new LinkedList<>();

    public ShowTime(String[] args, Constitution constitution) {
        this.args = args;
        this.constitution = constitution;
    }

    public void parse() {
        boolean isChapter = false;
        for (String arg : args) {
            if (arg.toLowerCase().equals("-c")) {
                isChapter = true;
            } else if (arg.toLowerCase().equals("-a")) {
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
            res *= 10;
            if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9)
                res += Character.getNumericValue(c);
            else return;
        }
        chapterShown.add(res - 1);
    }

    private void parseArt(String arg) {
        char c;
        int start = 0;
        int end = 0;
        for (int i = 0; i < arg.length(); i++) {
            c = arg.charAt(i);
            if (c == '-') {
                if (end == 0) return;
                start = end;
                end = 0;
            } else if (Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9) {
                end *= 10;
                end += Character.getNumericValue(c);
                //end *= 10;
            } else return;
        }
        if (start == 0) articleShown.add(end - 1);
        else {
            for (int i = start; i <= end; i++)
                articleShown.add(i - 1);
        }
    }

    private void show() {
        chapterShown.stream().filter(i -> i < constitution.chapters.size()).filter(i -> i >= 0).forEach(i -> constitution.chapters.get(i).print());
        articleShown.stream().filter(i -> i < constitution.articles.size()).filter(i -> i >= 0).forEach(i -> constitution.articles.get(i).print());
    }

}
