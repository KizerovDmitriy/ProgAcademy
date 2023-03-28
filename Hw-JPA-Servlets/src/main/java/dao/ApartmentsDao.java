package dao;


import model.Apartments;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class ApartmentsDao implements AutoCloseable{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Apartments");
    private EntityManager em = emf.createEntityManager();

    public void addApartments(Apartments apartments) {
        em.getTransaction().begin();
        try {
            em.persist(apartments);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    public List<Apartments> viewApartments(){
        Query query = em.createQuery("SELECT x FROM Apartments x", Apartments.class);
        return (List<Apartments>) query.getResultList();
    }
    public List<Apartments> getApartmentsByFilter(String filter) {
        return viewApartments().stream()
                .filter(val -> val.getRegion().equalsIgnoreCase(filter) || val.getAddress().equalsIgnoreCase(filter))
                .collect(Collectors.toList());
    }

    public List<Apartments> getApartmentsByFilter(Double filter) {
        return viewApartments().stream()
                .filter(val -> val.getArea() == filter || val.getPrice() == filter)
                .collect(Collectors.toList());
    }

    public List<Apartments> getApartmentsByFilter(Integer filter) {
        return viewApartments().stream()
                .filter(rooms -> rooms.getRooms() == filter)
                .collect(Collectors.toList());
    }

    @Override
    public void close() throws Exception {
        emf.close();
        em.close();
    }
}
