package dao;

import java.util.Scanner;

public interface ApartmentsDAO {

    void addApartments(Scanner scanner);

    void getApartments();

    void getApartmentsByFilter(String filter);

    void getApartmentsByFilter(Double filter);

    void getApartmentsByFilter(Integer filter);
}
