package PortFactory;

public class DockWorks {
    public static void main(String[] args) {

        //create Ships
        Ship aurora = new Ship("Aurora");
        Ship phoenix = new Ship("Phoenix");
        Ship victoria = new Ship("Victoria");
        // create port
        Port port = new Port();
        // load ships
        aurora.loadCargo();
        phoenix.loadCargo();
        victoria.loadCargo();
        //ships go in port
        port.addShip(aurora);
        port.addShip(phoenix);
        port.addShip(victoria);
        //create Docks
        Dock dock1 = new Dock(port);
        Dock dock2 = new Dock(port);
        // simulate Port work
        dock1.start();
        dock2.start();
    }
}