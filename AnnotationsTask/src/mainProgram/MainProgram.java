package main;

import classes.Calculate;
import classes.ChildClass2;

import java.util.Map;


public class MainProgram {
    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        Map<Class<?>, Integer> result = calculate.countMethodAndParameters(ChildClass2.class);
        result.forEach((k,v)-> System.out.println(k + " : " + v));
    }
}
