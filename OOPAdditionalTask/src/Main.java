import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        TextTransformer textTransformer = new TextTransformer();
        System.out.println(textTransformer.transform("hello"));

        TextTransformer inverseTransformer = new InverseTransformer();
        System.out.println(inverseTransformer.transform("hello"));

        TextTransformer upDownTransformer = new UpDownTransformer();
        System.out.println(upDownTransformer.transform("hello"));

        TextSaver textSaver = new TextSaver(upDownTransformer, new File("D:\\1.txt"));
        textSaver.saveTextToFile("hello");
    }
}