package model;


import javax.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private double area;
    @Column(nullable = false)
    private int rooms;
    @Column(nullable = false)
    private double price;

    public Apartments(String region, String address, double area, int rooms, double price) {
        this.region = region;
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.price = price;
    }
    public Apartments(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartments{" +
                " region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}