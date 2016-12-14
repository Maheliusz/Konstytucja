package agh.cs.konstytucja;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Constitution polishConsitution = new Constitution();
        Parser parser = new Parser(polishConsitution, args);
        polishConsitution = parser.parse();
        ShowTime show = new ShowTime(args, polishConsitution);
        show.parse();

    }


}
