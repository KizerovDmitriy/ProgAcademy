package dao;

import entities.Apartments;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class ApartmentsDAOImpl implements ApartmentsDAO, AutoCloseable {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Apartments");
    private EntityManager em = emf.createEntityManager();

    @Override
    public void addApartments(Scanner sc) {
        System.out.println("Input apartments region: ");
        String region = sc.nextLine();
        System.out.println("Enter address:");
        String address = sc.nextLine();
        System.out.println("Enter area of apartment:");
        Double area = Double.parseDouble(sc.nextLine());
        System.out.println("Enter number of rooms:");
        Integer rooms = Integer.parseInt(sc.nextLine());
        System.out.println("Enter price:");
        Double price = Double.parseDouble(sc.nextLine());

        em.getTransaction().begin();
        try {
            Apartments apartments = new Apartments(region, address, area, rooms, price);
            em.persist(apartments);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void getApartments() {
        Query query = em.createQuery("SELECT x FROM Apartments x", Apartments.class);
        query.getResultList().forEach(System.out::println);
    }

    @Override
    public void getApartmentsByFilter(String filter) {
        getList().stream()
                .filter(val -> val.getRegion().equalsIgnoreCase(filter) || val.getAddress().equalsIgnoreCase(filter))
                .forEach(System.out::println);
    }

    @Override
    public void getApartmentsByFilter(Double filter) {
        getList().stream()
                .filter(val -> val.getArea().equals(filter) || val.getPrice().equals(filter))
                .forEach(System.out::println);
    }

    @Override
    public void getApartmentsByFilter(Integer filter) {
        getList().stream().filter(rooms -> rooms.getRooms().equals(filter)).forEach(System.out::println);
    }

    private List<Apartments> getList() {
        Query query = em.createQuery("SELECT x FROM Apartments x", Apartments.class);
        return (List<Apartments>) query.getResultList();
    }

    @Override
    public void close() {
        try {
            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}