package program;

public class HasMapCount {
    public static void main(String[] args) {
        int[] digits = {1, 3, 2, 1, 2, 6, 7, 3, 9};
        String[] words = {"word", "earth", "key", "key", "word"};
        HasMapArrayCount count = new HasMapArrayCount();
        System.out.println(count.countElements(words));
        System.out.println(count.countElements(digits));
    }
}