/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptv20shoestore;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import myclasses.Customer;
import myclasses.History;
import myclasses.Product;
import myclasses.Size;

/**
 *
 * @author pupil
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    Product[] products = new Product[10];
    Customer[] customers = new Customer[10];
    History[] histories = new History[10];
    
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
            
            int task = scanner.nextInt(); scanner.nextLine();
            switch(task){
                case 0:
                    repeat = "q";
                    System.out.println("До свидания! Приходите к нам еще!");
                    break;
                case 1:
                    System.out.println("----- Добавить модель -----");
                    for(int i = 0; i < products.length; i++) {
                        if(products[i] == null) {
                            products[i] = addProduct();
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("----- Список продаваемых моделей -----");
                    for(int i = 0; i < products.length; i++) {
                        if(products[i] != null) {
                            System.out.println(products[i].toString());
                        }
                    }
                    break;
                case 3:
                    System.out.println("----- Добавить покупателя -----");
                    for(int i = 0; i < customers.length; i++) {
                        if(customers[i] == null) {
                            customers[i] = addCustomer();
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("----- Список зарегистрированных покупателей -----");
                    for(int i = 0; i < customers.length; i++) {
                        if(customers[i] != null) {
                            System.out.println(customers[i].toString());
                        }
                    }
                    break;
                case 5:
                    System.out.println("----- Покупка покупателем обуви -----");
                    for(int i = 0; i < histories.length; i++) {
                        if(histories[i] == null) {
                            histories[i] = addHistory();
                            break;
                        }
                    }
                    System.out.println("------------------------");
                    break;
                case 6:
                    System.out.println("----- Доход магазина за всё время работы -----");
                    printShopIncome();
            }
            
        }while("r".equals(repeat));
    }
    
    private void printShopIncome() {
        double income = 0;
        for(int i = 0; i < products.length; i++) {
            if(histories[i] != null) {
                income += histories[i].getProduct().getPrice();
                System.out.println("Доход магазина за всё время: " + income);            }
        }
        if(income == 0) {
            System.out.println("В данный момент у магазина нет прибыли!");
        }
    }
    
    private Product addProduct() {
        Product product = new Product();
        System.out.print("Введите тип обуви (мужская / женская): ");
        product.setProductname(scanner.nextLine());
        System.out.print("Введите модель обуви: ");
        product.setModel(scanner.nextLine());
        System.out.print("Введите цвет обуви: ");
        product.setColor(scanner.nextLine());
        System.out.print("Введите производителя обуви: ");
        product.setManufacturer(scanner.nextLine());
        System.out.print("Введите количество размеров обуви в магазине: ");
        int numberSizes = scanner.nextInt(); scanner.nextLine();
        
        Size[] sizes = new Size[numberSizes];
        for (int i = 0; i < sizes.length; i++) {
            Size size = new Size();
            System.out.print("Введите размер обуви: ");
            size.setNumbersuze(scanner.nextInt()); scanner.nextLine();
            sizes[i] = size;
            
        }
        System.out.print("Введите цену обуви: ");
        product.setPrice(scanner.nextDouble()); scanner.nextLine();
        product.setSize(sizes);
        
        return product;
    }
    
    private Customer addCustomer() {
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Введите номер телефона покупателя: ");
        customer.setPhone(scanner.nextLine());
        System.out.print("Введите количество денег покупателя: ");
        customer.setMoney(scanner.nextDouble());
        
        return customer;
    }
    
    private History addHistory() {
        History history = new History();
        /**
         * 1. Вывести пронумерованный список обуви
         * 2. Попросить пользователя выбрать номер обуви 
         * 3. Вывести пронумерованный список покупателей
         * 4. Попросить пользователя выбрать номер покупателя
         * 5. Сгенерировать текущую дату выдачи 
         * 6. Инициировать объект History (задать состояние)
         */
        System.out.println("Пронумерованный список обуви");
        System.out.println("----- Список продаваемых моделей -----");
        for(int i = 0; i < products.length; i++) {
            if(products[i] != null) {
                System.out.println(i+1+". "+products[i].toString());
            }
        }
        
        System.out.print("Введите номер обуви: ");
        int numberShoes = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Список покупателей");
        System.out.println("----- Список зарегистрированных покупателей -----");
        for(int i = 0; i < customers.length; i++) {
            if(customers[i] != null) {
                System.out.println(i+1+". "+customers[i].toString());
            }
        }
        
        System.out.print("Введите номер покупателя: ");
        int numberCustomer = scanner.nextInt();
        scanner.nextLine();
        
        history.setProduct(products[numberShoes - 1]);
        history.setCustomer(customers[numberCustomer - 1]);
        Calendar c = new GregorianCalendar();
        history.setPurchaseDate(c.getTime());
        
        return history;
    }
}
