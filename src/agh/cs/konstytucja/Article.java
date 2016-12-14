package agh.cs.konstytucja;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał Zakrzewski on 2016-11-30.
 */
public class Article {
    public List<String> text = new LinkedList<>();
    public int number;

    public void print() {
        System.out.println("Artykuł " + number);
        for(String line : text){
            System.out.println(line);
        }
        System.out.println();
    }
}
