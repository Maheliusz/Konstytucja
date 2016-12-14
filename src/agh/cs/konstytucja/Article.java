package agh.cs.konstytucja;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał Zakrzewski on 2016-11-30.
 */
public class Article {
    List<String> text = new LinkedList<>();
    int number;

    public void print() {
        System.out.println("Artykuł " + number);
        int i = 0;
        for(String line : text){
            System.out.println(line);
        }
        System.out.println();
    }
}
