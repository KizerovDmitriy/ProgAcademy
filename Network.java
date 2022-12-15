import java.util.ArrayList;
import java.util.List;

public class Network {
    public static List<String> phoneNumbers = new ArrayList<>();

    static {
        phoneNumbers.add("+380666334509");
        phoneNumbers.add("+380669052211");
        phoneNumbers.add("+380665912274");
    }

    private static Network network;

    private Network() {

    }

    public static Network getInstance() {
        if (network == null) {
            network = new Network();
        }
        return network;
    }
}