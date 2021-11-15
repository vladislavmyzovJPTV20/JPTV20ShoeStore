/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import entity.Customer;
import entity.History;
import entity.Product;
import entity.Size;

/**
 *
 * @author pupil
 */
public interface Keeping {
    public void saveProducts(List<Product> product);
    public List<Product> loadProducts();
    public void saveCustomers(List<Customer> customers);
    public List<Customer> loadCustomers();
    public void saveHistories(List<History> histories);
    public List<History> loadHistories();
    public void saveSizes(List<Size> sizes);
    public List<Size> loadSizes();
}
