package dao;

import classes.apartments.Apartments;

import java.util.List;

public interface ApartmentsDAO {
    void createTable();

    void addApartments(String region, String address, Double area, Integer rooms, Double price);

    List<Apartments> getApartments();

    List<Apartments> getApartmentsByFilter(String filter);

    List<Apartments> getApartmentsByFilter(Double filter);

    List<Apartments> getApartmentsByFilter(Integer filter);
}
