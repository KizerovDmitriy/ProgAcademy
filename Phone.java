public class Phone {

    private String phoneNumber;

    public void registerPhoneNumber() {
        if (Network.phoneNumbers.contains(phoneNumber)) {
            System.out.println("Your number already exist in base");
        } else {
            Network.phoneNumbers.add(phoneNumber);
        }
    }

    public void outgoingCall(String phoneNumber) {

        if (!(Network.phoneNumbers.contains(this.phoneNumber))) {
            System.out.println("Your phone number don't exist in Network base");
            return;
        } else if (!(Network.phoneNumbers.contains(phoneNumber))) {
            System.out.println("This phone number don't exist in Network base");
            return;
        } else {
            new Phone(phoneNumber).incomingCall(this.phoneNumber);
        }
    }

    public void incomingCall(String phoneNumber) {
        System.out.printf("This number is calling you %s", phoneNumber);
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}