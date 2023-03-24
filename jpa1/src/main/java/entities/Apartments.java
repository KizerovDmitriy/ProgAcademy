package entities;

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
    private Double area;
    @Column(nullable = false)
    private Integer rooms;
    @Column(nullable = false)
    private Double price;

    public Apartments() {
    }

    public Apartments(String region, String address, Double area, Integer rooms, Double price) {
        this.region = region;
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.price = price;
    }

    public String getRegion() {
        return region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartments{" +
                "region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}
