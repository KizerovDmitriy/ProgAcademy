package PortFactory;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final String name;
    private final List<Container> trunk = new ArrayList<>(10);

    private boolean isEmpty = true;

    public Ship(String name) {
        this.name = name;
    }

    public void loadCargo() {
        for (int i = 0; i < trunk.size(); i++) {
            trunk.add(new Container());
        }
        setEmpty(false);
    }

    public void unloadShip() {
        for (int i = 0; i < trunk.size(); i++) {
            trunk.remove(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (trunk.size() == 0) {
            setEmpty(true);
            System.out.println("Ship is unload! " + name);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}