package classes;

public class Apartments {
    @Id
    private int id;
    private String region;
    private String address;
    private Double area;
    private Integer rooms;
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
