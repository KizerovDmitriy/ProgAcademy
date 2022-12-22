import java.io.*;

public class TextSaver {

    private TextTransformer transformer;
    private File file;

    public TextSaver(TextTransformer transformer, File file) {
        this.transformer = transformer;
        this.file = file;
    }

    void saveTextToFile(String text) throws IOException {
            try (FileWriter fileWriter = new FileWriter(file)){
                fileWriter.write(transformer.transform(text));
            }
        }
    }