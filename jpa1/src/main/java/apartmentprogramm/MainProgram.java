package apartmentprogramm;

import dao.ApartmentsDAOImpl;

import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        try (ApartmentsDAOImpl dao = new ApartmentsDAOImpl()) {
            try (Scanner sc = new Scanner(System.in)) {
                while (true) {
                    System.out.println("1: add apartments");
                    System.out.println("2: view all apartments");
                    System.out.println("3: get all apartments by filter");
                    System.out.print("Input your choice --> ");
                    String choice = sc.nextLine();

                    switch (choice) {
                        case "1" -> dao.addApartments(sc);
                        case "2" -> dao.getApartments();
                        case "3" -> getFilterParameter(sc, dao);
                        default -> {
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something go wrong");
        }
    }

    private static void getFilterParameter(Scanner sc, ApartmentsDAOImpl dao) {
        System.out.println("Choose filer:");
        System.out.println("1: for filter by region or address");
        System.out.println("2: for filter by price or area of apartments");
        System.out.println("3: for filter by number of rooms");
        System.out.print("Input your choice --> ");
        String choice = sc.nextLine();
        System.out.println("Input filter parameter:");
        String parameter = sc.nextLine();
        switch (choice) {
            case "1" -> dao.getApartmentsByFilter(parameter);
            case "2" -> dao.getApartmentsByFilter(Double.parseDouble(parameter));
            case "3" -> dao.getApartmentsByFilter(Integer.parseInt(parameter));
            default -> {
                return;
            }
        }
    }
}