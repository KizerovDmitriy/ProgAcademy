package mainprogram;

import classes.MonoBank;

public class CurrencyView {
    public static void main(String[] args) {
        MonoBank monoBank = new MonoBank();
        System.out.println(monoBank.prettyPrint());
        System.out.println("""
                
                Another method:
                """);
        System.out.println(monoBank.prettyPrint("EUR"));
    }
}