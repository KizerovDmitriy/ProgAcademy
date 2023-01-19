package Task1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class RemoveElements {
    private final Deque<Integer> list = new ArrayDeque<>();

    public void addAndRemoveElements (){
        int i = 0;
        while (true){
            if (i < 10) {
                list.add(i);
                i++;
            }
            else break;
        }
        System.out.println("Before remove:");
        System.out.println(Arrays.toString(list.toArray()));
        list.removeFirst();
        list.removeFirst();
        list.removeLast();
        System.out.println("After:");
        System.out.println(Arrays.toString(list.toArray()));
    }
}