package MainProgram;

import Classes.Calculate;
import Classes.ChildClass1;
import Classes.ChildClass2;


public class MainProgram {
    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        calculate.countMethodAndParameters(ChildClass1.class);
        System.out.println(calculate.getCounts());
    }
}