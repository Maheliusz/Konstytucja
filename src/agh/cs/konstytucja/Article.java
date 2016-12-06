package agh.cs.konstytucja;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michał Zakrzewski on 2016-11-30.
 */
public class Article implements IParse {
    List<Paragraph> paragraphs = new LinkedList<Paragraph>();
    String text;
    int number;

    @Override
    public void parse() {

    }
}
