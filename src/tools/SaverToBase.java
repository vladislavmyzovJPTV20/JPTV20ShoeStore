/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import interfaces.Keeping;
import java.util.List;
import entity.Customer;
import entity.History;
import entity.Product;
import entity.Size;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class SaverToBase implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTV20ShoeStorePU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveProducts(List<Product> products) {
        tx.begin();
            for (int i = 0; i < products.size(); i++) {
                if(products.get(i).getId() == null){
                    em.persist(products.get(i));
                }else{
                    em.merge(products.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Product> loadProducts() {
        List <Product> products = null;
        try {
            products = em.createQuery("SELECT product FROM Product product")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return products;
    }

    @Override
    public void saveCustomers(List<Customer> customers) {
        tx.begin();
            for (int i = 0; i < customers.size(); i++) {
                if(customers.get(i).getId() == null){
                    em.persist(customers.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Customer> loadCustomers() {
        List <Customer> customers = null;
        try {
            customers = em.createQuery("SELECT customer FROM Customer customer")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return customers;
    }

    @Override
    public void saveHistories(List<History> histories) {
        tx.begin();
            for (int i = 0; i < histories.size(); i++) {
                if(histories.get(i).getId() == null){
                    em.persist(histories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        List <History> histories = null;
        try {
            histories = em.createQuery("SELECT history FROM History history")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return histories;
    }

    @Override
    public void saveSizes(List<Size> sizes) {
        tx.begin();
            for (int i = 0; i < sizes.size(); i++) {
                if(sizes.get(i).getId() == null){
                    em.persist(sizes.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Size> loadSizes() {
        List <Size> sizes = null;
        try {
            sizes = em.createQuery("SELECT size FROM Size size")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return sizes;
    }
    
}