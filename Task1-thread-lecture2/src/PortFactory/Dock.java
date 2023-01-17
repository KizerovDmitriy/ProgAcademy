package PortFactory;


public class Dock extends Thread {

    private final Port port;

    public Dock(Port port) {
        this.port = port;
    }

    @Override
    public void run() {
        do {
            Ship ship = port.getShip();
            if (ship != null && !ship.isEmpty()) {
                ship.unloadShip();
            }
        } while (port.getCountShips() != 0);
    }
}