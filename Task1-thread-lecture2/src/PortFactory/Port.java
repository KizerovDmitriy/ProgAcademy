package PortFactory;

import java.util.ArrayList;
import java.util.Iterator;
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
        Iterator<Ship> shipIterator = shipsInPort.iterator();
        while (shipIterator.hasNext()) {
            Ship ship = shipIterator.next();
            countShips--;
            System.out.println("Ship " + ship.getName() + " go to dock");
            shipIterator.remove();
            return ship;
        }
        return null;
    }
    public int getCountShips() {
        return countShips;
    }
}