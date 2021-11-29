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
import facade.CustomerFacade;
import facade.HistoryFacade;
import facade.ProductFacade;
import facade.SizeFacade;
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
    private SizeFacade sizeFacade;
    private ProductFacade productFacade;
    private CustomerFacade customerFacade;
    private HistoryFacade historyFacade;
    
    String[] Months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Норябрь", "Декабрь"};

    public App() {
        init();
    }
    
    private void init() {
        sizeFacade = new SizeFacade(Size.class);
        productFacade = new ProductFacade(Product.class);
        customerFacade = new CustomerFacade(Customer.class);
        historyFacade = new HistoryFacade(History.class);
    }
    
    public void run(){
        String repeat = "r";
        do{
            System.out.println("Выберите номер задачи: "); 
            System.out.println("0: Выход из программы");
            System.out.println("1: Добавить модель обувь");
            System.out.println("2: Добавить размер обуви");
            System.out.println("3: Список продаваемой модели обуви");
            System.out.println("4: Добавить покупателя");            
            System.out.println("5: Список зарагестрированных покупателей");   
            System.out.println("6: Покупка покупателем обуви");
            System.out.println("7: Доход магазина за всё время работы");
            System.out.println("8: Добавить пользователю деньги");
            System.out.println("9: Доход магазина за определенный месяц работы");
            System.out.println("10: Изменить данные обуви");
            System.out.println("11: Изменить данные покупателя");
            System.out.println("12: Изменить размер обуви");
            int task = scanner.nextInt(); scanner.nextLine();
            switch(task){
                case 0:
                    repeat = "q";
                    System.out.println("Пока! Приходи к нам еще!");
                    break;
                case 1:
                    System.out.println("----- Добавление модели обуви  -----");
                    addProduct();
                    System.out.println("-------------------------------");
                    break;
                case 2:    
                    System.out.println("----- Добавление размера обуви -----");
                    addSize();
                    System.out.println("-------------------------------");
                    break;
                case 3:
                    System.out.println("----- Список продаваемых моделей обуви -----");
                    printListProducts();
                    System.out.println("-------------------------------");
                    break;
                case 4:
                    System.out.println("----- Добавление покупателя -----");
                    addCustomer();
                    System.out.println("-------------------------------");
                    break;
                case 5:
                    System.out.println("----- Список зарагестрированных покупателей -----");
                    printListCustomers();
                    System.out.println("-------------------------------");
                    break;
                case 6:
                    System.out.println("----- Покупа модели обуви покупателем -----");
                    addHistory();
                    System.out.println("-------------------------------");
                    break;
                case 7:
                    System.out.println("----- Доход магазина за всё время работы -----");
                    printIncomeShop();
                    break;
                case 8:
                    System.out.println("----- Добавить пользователю деньги -----");
                    addMoney();
                    break;
                case 9:
                    System.out.println("----- Добавить пользователю деньги -----");
                    printIncomeInMonth();
                    break;
                case 10:
                    System.out.println("----- Изменить данные обуви -----");
                    updateProducts();
                    System.out.println("-------------------------------");
                    break;
                case 11:
                    System.out.println("----- Изменить покупателя -----");
                    updateCustomer();
                    System.out.println("-------------------------------");
                    break;
                case 12:
                    System.out.println("----- Изменить размер обуви -----");
                    updateSize();
                    System.out.println("-------------------------------");
                    break;
                default:
                    System.out.println("Введено неверное значение!");
                    break;
            }
        }while("r".equals(repeat));
    }
    
    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите \"q\", для продолжения любой другой символ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
      return false;
    }
    
    private void printIncomeShop(){
        List<History> histories = historyFacade.findAll();
        double income = 0;
        for(int i = 0; i < histories.size();i++){
            if(histories.get(i) != null){
                income += histories.get(i).getProduct().getPrice();
                System.out.println("Доход за всё время: "+ income +" €");
                System.out.println("-------------------------------");
            }
            if(income == 0){
                System.out.println("У магазина нет дохода в данный момент");
            }
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
            System.out.println("Введите размер.");
            return;
        }
        System.out.println();
        System.out.print("Введите количество размеров: ");
        int countSizes = getNumber();
        List<Size> shoesSize = new ArrayList<>();
        for (int i = 0; i < countSizes; i++) {
            System.out.println("Введите номер размера "+(i+1)+" из списка: ");
            int numberSize = insertNumber(setNumbersSizes);
            shoesSize.add(sizeFacade.find((long)numberSize));
        }
        product.setSize(shoesSize);
        System.out.println("Введите название обуви: ");
        product.setProductname(scanner.nextLine());
        System.out.println("Введите тип обуви (мужская/женская): ");
        product.setModel(scanner.nextLine());
        System.out.println("Введите производителя обуви:");
        product.setManufacturer(scanner.nextLine());
        System.out.println("Введите цвет обуви: ");
        product.setColor(scanner.nextLine());
        System.out.println("Введите цену обуви: ");
        product.setPrice(scanner.nextDouble()); scanner.nextLine();
        System.out.print("Введите количество экземпляров обуви: ");
        product.setQuantity(getNumber());
        product.setCount(product.getQuantity());
        productFacade.create(product);
    }
    private void addCustomer(){
        Customer customer = new Customer();
        System.out.println("Введите имя покупателя: ");
        customer.setFirstname(scanner.nextLine());
        System.out.println("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.println("Введите номер телефона покупателя: ");
        customer.setPhone(scanner.nextLine());
        System.out.println("Введите количество денег покупателя: ");
        customer.setMoney(scanner.nextDouble()); scanner.nextLine();
        customerFacade.create(customer);
    }
    private void addHistory(){
        History history = new History();
        System.out.println("Список продаваемой обуви: ");
        Set<Integer> setNumbersProducts = printListProducts();
        if(setNumbersProducts.isEmpty()){
            return;
        }
        System.out.println("Введите номер обуви: ");
        int numberProducts = insertNumber(setNumbersProducts);
        Product products = productFacade.find((long)numberProducts);
        
        System.out.println("-------------------------------");
        System.out.println("Список зарагестрированных покупателей: ");
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()){
            return;
        }
        System.out.print("Введите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);
        Customer customers = customerFacade.find((long)numberCustomer);
        history.setProduct(products);
        if(products.getPrice() < customers.getMoney()){
            if(products.getCount() > 0){
                products.setCount(products.getCount()-1);
                double priceLaw = customers.getMoney()-products.getPrice();       
                customers.setMoney(priceLaw);
                history.setCustomer(customers);
                Calendar c = new GregorianCalendar();
                history.setPurchaseDate(c.getTime());
                productFacade.edit(products);
                historyFacade.edit(history);
                customerFacade.edit(customers);
                System.out.println("Вы успешно приобрели обувь!");
            }            
        }
        else{
            System.out.println("Недостаточно средств для приобретения обуви!");
        }
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
        System.out.println("Список обуви: ");
        List<Product> products = productFacade.findAll();
        Set<Integer> setNumbersProducts = new HashSet<>();
        for (int i = 0; i < products.size(); i++) {
            StringBuilder cbSizes = new StringBuilder();
            for (int j = 0; j < products.get(i).getSize().size(); j++) {
                cbSizes.append(products.get(i).getSize().get(j).getShoesSize())
                        .append(". ");
            }
            if(products.get(i) != null && products.get(i).getCount() > 0){
                System.out.printf("%d. Обувь %s. %s. Размер обуви: %s. В наличии экземпляров: %d%n"
                        ,products.get(i).getId()
                        ,products.get(i).getProductname()
                        ,products.get(i).getModel()
                        ,cbSizes.toString()
                        ,products.get(i).getCount()
                );
                setNumbersProducts.add(i+1);
            }else if(products.get(i) != null){
                System.out.printf("%d. Обувь %s %s. Размер обуви: %s. Нет в наличии."
                        ,products.get(i).getId()
                        ,products.get(i).getProductname()
                        ,products.get(i).getModel()
                        ,cbSizes.toString()
                );
            }
        }
        return setNumbersProducts;        
    }

    private Set<Integer> printListCustomers() {
        List<Customer> customers = customerFacade.findAll();
        Set<Integer> setNumbersCustomers = new HashSet<>();
        System.out.println("Список покупателей: ");
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s %s. Деньги: %s. Телефон: %s%n"
                        ,customers.get(i).getId()
                        ,customers.get(i).getFirstname()
                        ,customers.get(i).getLastname()
                        ,customers.get(i).getMoney()
                        ,customers.get(i).getPhone()
                );
                setNumbersCustomers.add(customers.get(i).getId().intValue());
            }
        }
        if(setNumbersCustomers.isEmpty()) {
            System.out.println("Добавьте покупателей!");
        }
        return setNumbersCustomers;
    }
    
    private Set<Integer> printListSizes() {
        List<Size> sizes = sizeFacade.findAll();
        Set<Integer> setNumbersSizes = new HashSet<>();
        System.out.println("Список размеров: ");
        for (int i = 0; i < sizes.size(); i++) {
            if(sizes.get(i) != null){
                System.out.printf("%d. %s%n"
                        ,sizes.get(i).getId()
                        ,sizes.get(i).getShoesSize()
                );
                setNumbersSizes.add(sizes.get(i).getId().intValue());
            }
        }
        return setNumbersSizes;
    }
    
    private void addSize() {
        System.out.println("---- Добaвление размера обуви ----");
        Size size = new Size();
        System.out.print("Введите размер обуви: ");
        size.setShoesSize(scanner.nextInt()); scanner.nextLine();
        sizeFacade.create(size);
    }

    private void addMoney() {
        Customer customer = new Customer();
        System.out.println("Список покупателей: ");
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()) {
            return;
        }
        System.out.print("Введите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);
        Customer customers = customerFacade.find((long)numberCustomer);
        System.out.println("Введите количество денег, которые вы хотите начислить покупателю: ");
        double moneyUser = scanner.nextInt();
        double summa = customers.getMoney()+moneyUser;
        customers.setMoney(summa);
        customerFacade.edit(customers);
    }

    private void printIncomeInMonth() {
        System.out.print("Введите номер соотвутствующего месяца: ");
        List<History> histories = historyFacade.findAll();
        int monthScan = scanner.nextInt();
        double IncomeInMonth = 0;
        for(int i = 0; i < histories.size();i++){
            if(histories.get(i).getPurchaseDate().getMonth()+1 == monthScan){
                IncomeInMonth += histories.get(i).getProduct().getPrice();
            }
        }
        if(IncomeInMonth != 0){
            System.out.println("прибыль магазина за "+Months[monthScan-1] +" - " + IncomeInMonth +"€");  
        }else{
            System.out.println("Нет прибыли за "+Months[monthScan-1]);
        }
    }

    private void updateProducts() {
        Set<Integer> setNumbersProducts = printListAllProducts();
        if(setNumbersProducts.isEmpty()){
            System.out.println("Нет обуви в базе данных!");
            return;
        }
        System.out.println("Выберите номер обуви: ");
        int numProduct = insertNumber(setNumbersProducts);
        Set<Integer> setNum = new HashSet<>();
        setNum.add(1);
        setNum.add(2);
        Product products = productFacade.find((long)numProduct);
        System.out.println("Название обуви: "+products.getProductname());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое название обуви: ");
            products.setProductname(scanner.nextLine());
        }
        System.out.println("Цвет обуви: "+products.getColor());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый цвет обуви: ");
            products.setColor(scanner.nextLine());
        }
        System.out.println("Новый тип обуви: "+products.getModel());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый тип обуви: ");
            products.setModel(scanner.nextLine());
        }
        System.out.println("Производитель обуви: "+products.getManufacturer());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите нового производителя обуви: ");
            products.setManufacturer(scanner.nextLine());
        }
        System.out.println("Цена обуви: "+products.getPrice());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новую цену обуви: ");
            products.setPrice(scanner.nextDouble());
        }
        System.out.println("Количество экземпляров обуви: "+products.getQuantity());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое количество обуви: ");
            int oldCount = products.getCount();
            int oldQuantity = products.getQuantity();
            int newQuantity;
            do {                
                newQuantity = getNumber();
                if(newQuantity >= 0 && newQuantity >= oldQuantity - oldCount){
                    break;
                }
                System.out.println("Попробуй еще (>"+(oldQuantity - oldCount)+"): ");
            } while (true);
            int newCount = oldCount + (newQuantity - oldQuantity);
            products.setQuantity(newQuantity);
            products.setCount(newCount);
        }
        productFacade.edit(products);
    }
    
   private Set<Integer> printListAllProducts() {
        System.out.println("Список обуви: ");
        List<Product> products = productFacade.findAll();
        Set<Integer> setNumbersProducts = new HashSet<>();
        for (int i = 0; i < products.size(); i++) {
            StringBuilder cbSizes = new StringBuilder();
            for (int j = 0; j < products.get(i).getSize().size(); j++) {
                cbSizes.append(products.get(i).getSize().get(j).getShoesSize())
                        .append(". ");
            }
            if(products.get(i) != null && products.get(i).getCount() >= 0){
                System.out.printf("%d. Обувь %s. %s. Размер обуви: %s. В наличии экземпляров: %d%n"
                        ,products.get(i).getId()
                        ,products.get(i).getProductname()
                        ,products.get(i).getModel()
                        ,cbSizes.toString()
                        ,products.get(i).getCount()
                );
                setNumbersProducts.add(i+1);
            }else if(products.get(i) != null){
                System.out.printf("%d. Обувь %s %s. Размер обуви: %s. Нет в наличии."
                        ,products.get(i).getId()
                        ,products.get(i).getProductname()
                        ,products.get(i).getModel()
                        ,cbSizes.toString()
                );
            }
        }
        return setNumbersProducts;        
    }

    private void updateCustomer() {
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()){
            System.out.println("Нет покупателей в базе данных!");
            return;
        }
        System.out.println("Выберите номер покупателя: ");
        int numСustomer = insertNumber(setNumbersCustomers);
        Set<Integer> setNum = new HashSet<>();
        setNum.add(1);
        setNum.add(2);
        Customer customers = customerFacade.find((long)numСustomer);
        System.out.println("Имя покупателя: "+customers.getFirstname());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое имя покупателя: ");
            customers.setFirstname(scanner.nextLine());
        }
        System.out.println("фамилия покупателя: "+customers.getLastname());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новую фамилию покупателя: ");
            customers.setLastname(scanner.nextLine());
        }
        System.out.println("Телефон покупателя: "+customers.getPhone());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый телефон покупателя: ");
            customers.setPhone(scanner.nextLine());
        }
        System.out.println("Бюджет покупателя: "+customers.getMoney());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый бюджет покупателя: ");
            customers.setMoney(scanner.nextDouble()); scanner.nextLine();
        }
        customerFacade.edit(customers);
    }
    
    private void updateSize() {
        Set<Integer> setNumbersSizes = printListSizes();
        if(setNumbersSizes.isEmpty()){
            System.out.println("Нет размеров в базе");
            return;
        }
        System.out.println("Выберите номер размера: ");
        int numSize = insertNumber(setNumbersSizes);
        Size sizes = sizeFacade.find((long)numSize);
        Set<Integer> setNum = new HashSet<>();
        setNum.add(1);
        setNum.add(2);
        System.out.println("Размер обуви: "+sizes.getShoesSize());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый размер: ");
            sizes.setShoesSize(scanner.nextInt());
        }
        sizeFacade.edit(sizes);
    }
        
    private void returnProduct() {
        System.out.println("Вернуть бракованную обувь: ");
        if(quit()) return;
        Set<Integer> numbersGivenProducts = printGivenProducts();
        if(numbersGivenProducts.isEmpty()){
            return;
        }
        int historyNumber = insertNumber(numbersGivenProducts);
        History history = historyFacade.find((long) historyNumber);
        Calendar c = new GregorianCalendar();
        history.setPurchaseDate(c.getTime());
        Product product = productFacade.find(history.getProduct().getId());
        product.setCount(product.getCount()+1);
        productFacade.edit(product);
        historyFacade.edit(history);
    }
    
    private Set<Integer> printGivenProducts(){
        System.out.println("Список выданной обуви: ");
        Set<Integer> setNumberGivenProducts = new HashSet<>();
        List<History> historyesWithGivenBooks = historyFacade.findWithGivenBooks();
        for (int i = 0; i < historyesWithGivenBooks.size(); i++) {
            System.out.printf("%d. Обувь: %s купил %s %s%n",
                    historyesWithGivenBooks.get(i).getId(),
                    historyesWithGivenBooks.get(i).getProduct().getProductname(),
                    historyesWithGivenBooks.get(i).getCustomer().getFirstname(),
                    historyesWithGivenBooks.get(i).getCustomer().getLastname()
            );
            setNumberGivenProducts.add(historyesWithGivenBooks.get(i).getId().intValue());
        }
        if(setNumberGivenProducts.isEmpty()){
            System.out.println("Купленной обуви нет!");
        }
        return setNumberGivenProducts;
    }
}
