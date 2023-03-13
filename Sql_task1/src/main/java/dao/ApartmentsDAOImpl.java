package dao;

import classes.apartments.Apartments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentsDAOImpl implements ApartmentsDAO {
    private final Connection connection;

    public ApartmentsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Apartments");
            st.execute("CREATE TABLE Apartments (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " region VARCHAR(30) NOT NULL, " +
                    "address VARCHAR(50) NOT NULL ," +
                    "area DECIMAL(10,2) NOT NULL ," +
                    "rooms INT NOT NULL ," +
                    "price DECIMAL(10,2) NOT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addApartments(String region, String address, Double area, Integer rooms, Double price) {
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO Apartments (region,address,area,rooms,price) VALUES (?,?,?,?,?)")) {
            st.setString(1, region);
            st.setString(2, address);
            st.setDouble(3, area);
            st.setInt(4, rooms);
            st.setDouble(5, price);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Apartments> getApartments() {
        List<Apartments> result = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM Apartments")) {
                getList(result, rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Apartments> getApartmentsByFilter(String filter) {
        List<Apartments> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Apartments WHERE region = ? OR address = ?")) {
            ps.setString(1, filter);
            ps.setString(2, filter);
            ResultSet rs = ps.executeQuery();
            getList(result, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public List<Apartments> getApartmentsByFilter(Double filter) {
        List<Apartments> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Apartments WHERE area BETWEEN ? AND ? OR price BETWEEN ? AND ?")) {
            ps.setDouble(1, filter - 10);
            ps.setDouble(2, filter + 10);
            ps.setDouble(3, filter - 2000);
            ps.setDouble(4, filter + 2000);
            ResultSet rs = ps.executeQuery();
            getList(result, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Apartments> getApartmentsByFilter(Integer filter) {
        List<Apartments> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Apartments WHERE rooms = ?")) {
            ps.setInt(1, filter);
            ResultSet rs = ps.executeQuery();
            getList(result, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void getList(List<Apartments> result, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Apartments apartments = new Apartments();
            apartments.setId(rs.getInt(1));
            apartments.setRegion(rs.getString(2));
            apartments.setAddress(rs.getString(3));
            apartments.setArea(rs.getDouble(4));
            apartments.setRooms(rs.getInt(5));
            apartments.setPrice(rs.getDouble(6));
            result.add(apartments);
        }
    }
}