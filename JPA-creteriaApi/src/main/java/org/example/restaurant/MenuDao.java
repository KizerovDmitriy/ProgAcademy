package org.example.restaurant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class MenuDao implements AutoCloseable {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Menu");
    private final EntityManager em = emf.createEntityManager();
    private final CriteriaBuilder cb = em.getCriteriaBuilder();

    public void addNewDish(Scanner sc) {
        System.out.println("Input name of dish:");
        String name = sc.nextLine();
        System.out.println("Input price:");
        Double price = Double.parseDouble(sc.nextLine());
        System.out.println("Input weight:");
        Double weight = Double.parseDouble(sc.nextLine());
        System.out.println("Does the client have a discount?  Y/N:");
        String choice = sc.nextLine();
        boolean discount = choice.equalsIgnoreCase("Y");
        try {
            Menu menu = new Menu(name, price, weight, discount);
            em.getTransaction().begin();
            em.persist(menu);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public void sortByPrice() {
        CriteriaQuery<Menu> cr = cb.createQuery(Menu.class);
        Root<Menu> root = cr.from(Menu.class);
        cr.orderBy(cb.asc(root.get("price")));
        em.createQuery(cr).getResultList().forEach(System.out::println);
    }

    public void onlyDiscount() {
        CriteriaQuery<Menu> cr = cb.createQuery(Menu.class);
        Root<Menu> root = cr.from(Menu.class);
        cr.select(root).where(cb.isTrue(root.get("discount")));
        em.createQuery(cr).getResultList().forEach(System.out::println);
    }

    public void lessThanKilo() {
        CriteriaQuery<Menu> cr = cb.createQuery(Menu.class);
        Root<Menu> root = cr.from(Menu.class);
        double maxWeight = 1000;
        double sum = 0;
        cr.select(root);
        Set<Menu> resultList = new HashSet<>();
        List<Menu> menuList = em.createQuery(cr).getResultList();
        Collections.shuffle(menuList);
        for (Menu menu : menuList){
            if (menu.getWeight() + sum < maxWeight){
                resultList.add(menu);
                sum += menu.getWeight();
            }
        }
        resultList.forEach(System.out::println);
    }

    public void close() {
        emf.close();
        em.close();
    }
}