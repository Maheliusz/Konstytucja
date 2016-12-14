package agh.cs.konstytucja;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał Zakrzewski on 2016-11-30.
 */
public class Chapter {
    List<Article> articles = new ArrayList<>();
    String title;
    int number;

    public void print() {
        System.out.println("ROZDZIAŁ " + number);
        int i = 0;
        for (Article article; i < articles.size(); i++) {
            article = articles.get(i);
            article.print();
        }
        System.out.println();
    }

}
