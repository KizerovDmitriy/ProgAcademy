package mainprograms;

import classes.Saver;
import classes.SerializableClass;

public class SerializableProgram {
    public static void main(String[] args) {
        SerializableClass serializableClass1 = new SerializableClass("Dmytro","Kizerov","32","0666334509");
        SerializableClass serializableClass2 = new SerializableClass();
        Saver saver = new Saver();
        saver.serializeObject(serializableClass1,"C:\\Users\\Dmitriy\\Desktop\\HomeTaskJavaProAnnotations\\src\\resource\\serialize.txt");
        saver.deserializeObject(serializableClass2,"C:\\Users\\Dmitriy\\Desktop\\HomeTaskJavaProAnnotations\\src\\resource\\serialize.txt");
        System.out.println(serializableClass2);
    }
}
