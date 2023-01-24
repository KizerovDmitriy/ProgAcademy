package program;

import dictionary.UkrainianToEnglishDictionary;
import translator.TranslateAndWrite;

public class MainDictionaryProgram {
    public static void main(String[] args) {

        UkrainianToEnglishDictionary dictionary = new UkrainianToEnglishDictionary();
        dictionary.addWordToDictionary("i", "я");
        dictionary.addWordToDictionary("want", "хочу");
        dictionary.addWordToDictionary("learn", "вивчати");
        dictionary.addWordToDictionary("java", "Джаву");

        TranslateAndWrite translateAndWrite = new TranslateAndWrite(args[1], dictionary);
        translateAndWrite.translate(args[0]);
        dictionary.saveDictionary(args[2]);
    }
}