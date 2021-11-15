/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv20shoestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import entity.Customer;
import entity.History;
import entity.Product;
import entity.Size;
import interfaces.Keeping;
import java.util.HashSet;
import java.util.Set;
import tools.SaverToBase;

/**
 *
 * @author pupil
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Size> sizes = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private Keeping keeper = new SaverToBase();
    
    public App() {
        products = keeper.loadProducts();
        sizes = keeper.loadSizes();
        customers = keeper.loadCustomers();
        histories = keeper.loadHistories();
    }
    
    void run() {
        String repeat = "r";
        do{
            System.out.println("Выберите номер задачи: ");
            System.out.println("0: Выход из программы");
            System.out.println("1: Добавить модель");
            System.out.println("2: Список продаваемых моделей");
            System.out.println("3: Добавить покупателя");
            System.out.println("4: Список зарегистрированных покупателей");
            System.out.println("5: Покупка покупателем обуви");
            System.out.println("6: Доход магазина за всё время работы");
            System.out.println("7: Добавить размер обуви");
            System.out.println("8: Список всех размеров обуви");
            System.out.println("9: Добавить пользователю денег");
            
            int task = scanner.nextInt(); scanner.nextLine();
            switch(task){
                case 0:
                    repeat = "q";
                    System.out.println("До свидания! Приходите к нам еще!");
                    break;
                case 1:
                    System.out.println("----- Добавить модель -----");
                    addProduct();
                    break;
                case 2:
                    System.out.println("----- Список продаваемых моделей -----");
                    printListProducts();
                    System.out.println("-----------------------");
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    printListCustomers();
                    break;
                case 5:
                    addHistory();
                    break;
                case 6:
                    System.out.println("----- Доход магазина за всё время работы -----");
                    printShopIncome();
                    break;
                case 7:
                    addSize();
                    break;
                case 8:
                    printListSizes();
                    break;
                case 9:
                    System.out.println("----- Добавить пользователю денег -----");
                    addMoney();
                    break;
            }
            
        }while("r".equals(repeat));
    }
    
    private void printShopIncome() {
        double income = 0;
        for(int i = 0; i < products.size(); i++) {
            if(histories.get(i) != null) {
                income += histories.get(i).getProduct().getPrice();
                System.out.println("Доход магазина за всё время: " + income);            }
        }
        if(income == 0) {
            System.out.println("В данный момент у магазина нет прибыли!");
        }
    }
    
    private void addProduct(){
        Product product = new Product();
        Set<Integer> setNumbersSizes = printListSizes();
        if(setNumbersSizes.isEmpty()){
            System.out.println("Добавьте размер обуви.");
            return;
        }
        System.out.print("Если в списке есть размеры обуви нажмите 1: ");
        if(getNumber() != 1){
            System.out.println("Введите размер обуви.");
            return;
        }
        System.out.print("Введите тип обуви (мужская / женская): ");
        product.setProductname(scanner.nextLine());
        System.out.print("Введите модель обуви: ");
        product.setModel(scanner.nextLine());
        System.out.print("Введите цвет обуви: ");
        product.setColor(scanner.nextLine());
        System.out.print("Введите производителя обуви: ");
        product.setManufacturer(scanner.nextLine());
        System.out.print("Введите количество размеров обуви в магазине: ");
        int countSizes = getNumber();
        
        List<Size> sizesProduct = new ArrayList<>();
        for (int i = 0; i < countSizes; i++) {
            System.out.println("Введите номер модели обуви "+(i+1)+" из списка: ");
            int numberSize = insertNumber(setNumbersSizes);
            sizesProduct.add(sizes.get(numberSize - 1));
            
        }
        System.out.print("Введите цену обуви: ");
        product.setPrice(getNumber());
        System.out.println("Введите количество экземпляров обуви: ");
        product.setQuantity(getNumber());
        product.setCount(product.getQuantity());
        products.add(product);
        keeper.saveProducts(products);
    }
    
    private void addCustomer(){
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Введите номер телефона покупателя: ");
        customer.setPhone(scanner.nextLine());
        System.out.print("Введите количество денег покупателя: ");
        customer.setMoney(getNumber());
        
        customers.add(customer);
        keeper.saveCustomers(customers);
    }
    
    private void addHistory() {
        History history = new History();
        Customer customer = new Customer();
        /**
         * 1. Вывести пронумерованный список книг библиотеки
         * 2. Попросить пользователя выбрать номер книги
         * 3. Вывести пронумерованный список читателей
         * 4. Попрость пользователя выбрать номер читателя
         * 5. Генерироват текущую дату
         * 6. инициировать (задать состояние) объект history
         */
        System.out.println("------------ Покупка покупателем обуви ----------");
        System.out.println("Список моделей: ");
        Set<Integer> setNumbersProducts = printListProducts();
        if(setNumbersProducts.isEmpty()){
            return;
        }
        System.out.print("Введите номер модели обуви: ");
        int numberProduct = insertNumber(setNumbersProducts);
        System.out.println("Список покупателей: ");
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()) {
            return;
        }
        System.out.print("Введите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);
        history.setProduct(products.get(numberProduct-1));
        if(products.get(numberProduct - 1).getCount() > 0){
            products.get(numberProduct - 1).setCount(products.get(numberProduct - 1).getCount()-1);
        }
        history.setCustomer(customers.get(numberCustomer-1));
        Calendar c = new GregorianCalendar();
        history.setPurchaseDate(c.getTime());
        
        keeper.saveProducts(products);
        histories.add(history);
        keeper.saveHistories(histories);
        System.out.println("========================");
    }
    
    private int getNumber() {
        do{
            try {
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            } catch (Exception e) {
                System.out.println("Попробуй еще раз: ");
            }
        }while(true);
    }
    
    private int insertNumber(Set<Integer> setNumbers) {
        do{
            int historyNumber = getNumber();
            if(setNumbers.contains(historyNumber)){
                return historyNumber;
            }
            System.out.println("Попробуй еще раз: ");
        }while(true);
    }

    private Set<Integer> printListProducts() {
        System.out.println("Список продаваемых моделей: ");
        products = keeper.loadProducts();
        Set<Integer> setNumbersProducts = new HashSet<>();
        for (int i = 0; i < products.size(); i++) {
            StringBuilder cbSizes = new StringBuilder();
            for (int j = 0; j < products.get(i).getSize().size(); j++) {
                cbSizes.append(products.get(i).getSize().get(j).getNumbersuze())
                        .append(". ");
            }
            if(products.get(i) != null && products.get(i).getCount() > 0){
                System.out.printf("%d. %s. %s. В наличии экземпряров: %d%n"
                        ,i+1
                        ,products.get(i).toString()
                        ,cbSizes.toString()
                        ,products.get(i).getCount()
                );
                setNumbersProducts.add(i+1);
            }else if(products.get(i) != null){
                System.out.printf("%d. %s. %s Нет в наличии.%n"
                        ,i+1
                        ,products.get(i).getProductname()
                        ,cbSizes.toString()
                );
            }
        }
        return setNumbersProducts;
    }

    private Set<Integer> printListCustomers() {
        Set<Integer> setNumbersCustomers = new HashSet<>();
        System.out.println("Список покупателей: ");
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s%n"
                        ,i+1
                        ,customers.get(i).toString()
                );
                setNumbersCustomers.add(i+1);
            }
        }
        if(setNumbersCustomers.isEmpty()) {
            System.out.println("Добавьте покупателей!");
        }
        return setNumbersCustomers;
    }
    
    private Set<Integer> printListSizes() {
        Set<Integer> setNumbersSizes = new HashSet<>();
        System.out.println("Список размеров: ");
        for (int i = 0; i < sizes.size(); i++) {
            if(sizes.get(i) != null){
                System.out.printf("%d. %s%n"
                        ,i+1
                        ,sizes.get(i).toString()
                );
                setNumbersSizes.add(i+1);
            }
        }
        return setNumbersSizes;
    }
    
    private void addSize() {
        System.out.println("---- Добавление размера обуви ----");
        Size size = new Size();
        System.out.print("Введите размер обуви: ");
        size.setNumbersuze(getNumber());
        sizes.add(size);
        keeper.saveSizes(sizes);
        System.out.println("-----------------------");
    }

    private void addMoney() {
        Customer customer = new Customer();
        System.out.println("Список покупателей: ");
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()) {
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            System.out.print("Введите номер покупателя: ");
            int numberCustomer = insertNumber(setNumbersCustomers);
            System.out.println("Введите количество денег, которые вы хотите начислить покупателю: ");
            double moneyUser = scanner.nextInt();
            double summa = customers.get(numberCustomer - 1).getMoney()+moneyUser;
            customers.get(numberCustomer - 1).setMoney(summa);
            customers.add(customer);
            keeper.saveCustomers(customers);
            break;
        }
    }
}
