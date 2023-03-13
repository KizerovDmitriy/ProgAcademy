package classes.apartments;

import classes.ConnectionFactory;
import dao.ApartmentsDAO;
import dao.ApartmentsDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class MainProgramApartments {
    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection("db.properties");
             Scanner scanner = new Scanner(System.in)) {
            ApartmentsDAO dao = new ApartmentsDAOImpl(connection);
            System.out.println("Need to create new table? Y/N?");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("y")){
                dao.createTable();
            }

            while (true) {
                System.out.println("1: add apartments");
                System.out.println("2: view all apartments");
                System.out.println("3: get all apartments by filter");
                System.out.print("Input your choice --> ");
                String choice = scanner.nextLine();


                switch (choice) {

                    case "1":
                        StringJoiner sj = new StringJoiner(",");
                        System.out.println("Enter region:");
                        sj.add(scanner.nextLine());
                        System.out.println("Enter address:");
                        sj.add(scanner.nextLine());
                        System.out.println("Enter area of apartment:");
                        sj.add(scanner.nextLine());
                        System.out.println("Enter number of rooms:");
                        sj.add(scanner.nextLine());
                        System.out.println("Enter price:");
                        sj.add(scanner.nextLine());
                        String[] tmp = sj.toString().split(",");
                        dao.addApartments(tmp[0], tmp[1], Double.parseDouble(tmp[2]), Integer.parseInt(tmp[3]), Double.parseDouble(tmp[4]));
                        break;

                    case "2":
                        List<Apartments> apartments = dao.getApartments();
                        if (apartments.isEmpty()) {
                            System.out.println("NO DATA");
                        } else {
                            for (Apartments a :
                                    apartments) {
                                System.out.println(a);
                            }
                        }
                        break;

                    case "3":
                        System.out.println("Choose filer:");
                        System.out.println("1: for filter by region or address");
                        System.out.println("2: for filter by price or area of apartments");
                        System.out.println("3: for filter by number of rooms");
                        System.out.print("Input your choice --> ");
                        String filterNumber = scanner.nextLine();
                        System.out.print("Input filter parameter:  ");
                        String filter = scanner.nextLine();
                        switch (filterNumber) {
                            case "1":
                                List<Apartments> filterByRegionAndAddress = dao.getApartmentsByFilter(filter);
                                if (filterByRegionAndAddress.isEmpty()) {
                                    System.out.println("No matches");
                                } else {
                                    for (Apartments a : filterByRegionAndAddress) {
                                        System.out.println(a);
                                    }
                                }
                                break;
                            case "2":
                                List<Apartments> filterByAreaAndPrice = dao.getApartmentsByFilter(Double.parseDouble(filter));
                                if (filterByAreaAndPrice.isEmpty()) {
                                    System.out.println("No matches");
                                } else {
                                    for (Apartments a : filterByAreaAndPrice) {
                                        System.out.println(a);
                                    }
                                }
                                break;
                            case "3":
                                List<Apartments> filterByRooms = dao.getApartmentsByFilter(Integer.parseInt(filter));
                                if (filterByRooms.isEmpty()) {
                                    System.out.println("No matches");
                                } else {
                                    for (Apartments a : filterByRooms) {
                                        System.out.println(a);
                                    }
                                }
                                break;
                            default:
                                return;
                        }
                        break;
                    default:
                        return;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}