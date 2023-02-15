package mainProgram;

import classes.Calculate;
import classes.ChildClass1;
import classes.ChildClass2;


public class MainProgram {
    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        calculate.countMethodAndParameters(ChildClass2.class);
        System.out.println(calculate.getCounts());
    }
}