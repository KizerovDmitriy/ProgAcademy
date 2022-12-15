public class Test {
    public static void main(String[] args) {
        Phone phone = new Phone("+380669076841");
        phone.registerPhoneNumber();
        phone.outgoingCall("+380666543456");
    }
}
