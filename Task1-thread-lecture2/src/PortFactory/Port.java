package PortFactory;


import java.util.ArrayList;
import java.util.List;

public class Port {
    private final List<Ship> shipsInPort;
    private int countShips = 0;

    public Port() {
        shipsInPort = new ArrayList<>();
    }

    public synchronized void addShip(Ship ship) {
        shipsInPort.add(ship);
        countShips++;
    }

    public synchronized Ship getShip() {
        for (Ship s : shipsInPort) {
            countShips--;
            System.out.println("Ship " + s.getName() + " go to dock");
            shipsInPort.remove(s);
            return s;
        }
        return null;
    }
    public int getCountShips() {
        return countShips;
    }
}