package mainprograms;

import classes.Saver;
import classes.SerializableClass;

public class SerializableProgram {
    public static void main(String[] args) {
        SerializableClass serializableClass1 = new SerializableClass("Dmytro","Kizerov","32","0666334509");
        SerializableClass serializableClass2 = new SerializableClass();
        Saver saver = new Saver();
        saver.serializeObject(serializableClass1,args[0]);
        saver.deserializeObject(serializableClass2,args[0]);
        System.out.println(serializableClass2);
    }
}
