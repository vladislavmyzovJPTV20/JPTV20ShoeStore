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
    private List<History> histories = new ArrayList<>();
    private List<Size> sizes = new ArrayList<>();
    private Keeping keeper = new SaverToBase();
    
    String[] Months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Норябрь", "Декабрь"};

    public App() {
        products = keeper.loadProducts();
        customers = keeper.loadCustomers();
        histories = keeper.loadHistories();
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
            shoesSize.add(sizes.get(numberSize - 1));
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
        products.add(product);
        keeper.saveProducts(products);        
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
        customers.add(customer);
        keeper.saveCustomers(customers);
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
        
        System.out.println("-------------------------------");
        System.out.println("Список зарагестрированных покупателей: ");
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()){
            return;
        }
        System.out.println("Введите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);
        history.setProduct(products.get(numberProducts-1));
        if(products.get(numberProducts-1).getPrice() < customers.get(numberCustomer -1).getMoney()){
            if(products.get(numberProducts - 1).getCount() > 0){
                products.get(numberProducts - 1).setCount(products.get(numberProducts - 1).getCount()-1);
                double price = customers.get(numberCustomer - 1).getMoney()-products.get(numberProducts-1).getPrice();       
                customers.get(numberCustomer - 1).setMoney(price);
                history.setCustomer(customers.get(numberCustomer-1));
                Calendar c = new GregorianCalendar();
                history.setPurchaseDate(c.getTime());
                keeper.saveProducts(products);
                histories.add(history);
                keeper.saveHistories(histories);
                keeper.saveCustomers(customers);
                System.out.println("Спасибо за покупку!");
            }            
        }
        else{
            System.out.println("У Вас недостаточно средств для покупки обуви!");
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
        products = keeper.loadProducts();
        Set<Integer> setNumbersProducts = new HashSet<>();
        for (int i = 0; i < products.size(); i++) {
            StringBuilder cbSizes = new StringBuilder();
            for (int j = 0; j < products.get(i).getSize().size(); j++) {
                cbSizes.append(products.get(i).getSize().get(j).getShoesSize())
                        .append(". ");
            }
            if(products.get(i) != null && products.get(i).getCount() > 0){
                System.out.printf("%d. Обувь %s. %s. Размер обуви: %s. В наличии экземпляров: %d%n"
                        ,i+1
                        ,products.get(i).getProductname()
                        ,products.get(i).getModel()
                        ,cbSizes.toString()
                        ,products.get(i).getCount()
                );
                setNumbersProducts.add(i+1);
            }else if(products.get(i) != null){
                System.out.printf("%d. Обувь %s %s. Размер обуви: %s. Нет в наличии."
                        ,i+1
                        ,products.get(i).getProductname()
                        ,products.get(i).getModel()
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
        System.out.println("Список книг: ");
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
        System.out.println("---- Добaвление размера обуви ----");
        Size size = new Size();
        System.out.print("Введите размер обуви: ");
        size.setShoesSize(scanner.nextInt()); scanner.nextLine();
        sizes.add(size);
        keeper.saveSizes(sizes);
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
            keeper.saveCustomers(customers);
            break;
        }
    }

    private void printIncomeInMonth() {
        System.out.print("Введите номер соотвутствующего месяца: ");
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
        System.out.println("Название обуви: "+products.get(numProduct - 1).getProductname());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое название обуви: ");
            products.get(numProduct - 1).setProductname(scanner.nextLine());
        }
        System.out.println("Цвет обуви: "+products.get(numProduct - 1).getColor());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый цвет обуви: ");
            products.get(numProduct - 1).setColor(scanner.nextLine());
        }
        System.out.println("Новый тип обуви: "+products.get(numProduct - 1).getModel());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый тип обуви: ");
            products.get(numProduct - 1).setModel(scanner.nextLine());
        }
        System.out.println("Производитель обуви: "+products.get(numProduct - 1).getManufacturer());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите нового производителя обуви: ");
            products.get(numProduct - 1).setManufacturer(scanner.nextLine());
        }
        System.out.println("Цена обуви: "+products.get(numProduct - 1).getPrice());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новую цену обуви: ");
            products.get(numProduct - 1).setPrice(scanner.nextDouble());
        }
        System.out.println("Количество экземпляров обуви: "+products.get(numProduct - 1).getQuantity());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое количество обуви: ");
            int oldCount = products.get(numProduct - 1).getCount();
            int oldQuantity = products.get(numProduct - 1).getQuantity();
            int newQuantity;
            do {                
                newQuantity = getNumber();
                if(newQuantity >= 0 && newQuantity >= oldQuantity - oldCount){
                    break;
                }
                System.out.println("Попробуй еще (>"+(oldQuantity - oldCount)+"): ");
            } while (true);
            int newCount = oldCount + (newQuantity - oldQuantity);
            products.get(numProduct - 1).setQuantity(newQuantity);
            products.get(numProduct - 1).setCount(newCount);
        }
        keeper.saveProducts(products);
    }
    
   private Set<Integer> printListAllProducts() {
        System.out.println("Список обуви: ");
        products = keeper.loadProducts();
        Set<Integer> setNumbersProducts = new HashSet<>();
        for (int i = 0; i < products.size(); i++) {
            StringBuilder cbSizes = new StringBuilder();
            for (int j = 0; j < products.get(i).getSize().size(); j++) {
                cbSizes.append(products.get(i).getSize().get(j).getShoesSize())
                        .append(". ");
            }
            if(products.get(i) != null && products.get(i).getCount() >= 0){
                System.out.printf("%d. Обувь %s. %s. Размер обуви: %s. В наличии экземпляров: %d%n"
                        ,i+1
                        ,products.get(i).getProductname()
                        ,products.get(i).getModel()
                        ,cbSizes.toString()
                        ,products.get(i).getCount()
                );
                setNumbersProducts.add(i+1);
            }else if(products.get(i) != null){
                System.out.printf("%d. Обувь %s %s. Размер обуви: %s. Нет в наличии."
                        ,i+1
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
        System.out.println("Имя покупателя: "+customers.get(numСustomer - 1).getFirstname());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новое имя покупателя: ");
            customers.get(numСustomer - 1).setFirstname(scanner.nextLine());
        }
        System.out.println("фамилия покупателя: "+customers.get(numСustomer - 1).getLastname());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новую фамилию покупателя: ");
            customers.get(numСustomer - 1).setLastname(scanner.nextLine());
        }
        System.out.println("Телефон покупателя: "+customers.get(numСustomer - 1).getPhone());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый телефон покупателя: ");
            customers.get(numСustomer - 1).setPhone(scanner.nextLine());
        }
        System.out.println("Бюджет покупателя: "+customers.get(numСustomer - 1).getMoney());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый бюджет покупателя: ");
            customers.get(numСustomer - 1).setMoney(scanner.nextDouble()); scanner.nextLine();
        }
        keeper.saveCustomers(customers);
    }
    
    private void updateSize() {
        Set<Integer> setNumbersSizes = printListSizes();
        if(setNumbersSizes.isEmpty()){
            System.out.println("Нет размеров в базе");
            return;
        }
        System.out.println("Выберите номер размера: ");
        int numSize = insertNumber(setNumbersSizes);
        Set<Integer> setNum = new HashSet<>();
        setNum.add(1);
        setNum.add(2);
        System.out.println("Размер обуви: "+sizes.get(numSize - 1).getShoesSize());
        System.out.println("Хотите изменить нажмите 1, оставить без изменения 2");
        int change = insertNumber(setNum);
        if(1 == change){
            System.out.println("Введите новый размер: ");
            sizes.get(numSize - 1).setShoesSize(scanner.nextInt());
        }
        keeper.saveSizes(sizes);
    }
        
    private void returnProduct() {
        System.out.println("Список обуви: ");
        if(quit()) return;
        Set<Integer> numbersGivenProducts = printGivenProducts();
        if(numbersGivenProducts.isEmpty()){
            return;
        }
        int historyNumber = insertNumber(numbersGivenProducts);
        Calendar c = new GregorianCalendar();
        histories.get(historyNumber - 1).setReturnedDate(c.getTime());
        for (int i = 0; i < products.size(); i++) {
          if(products.get(i).getProductname().equals(histories.get(historyNumber-1).getProduct().getProductname())){
            products.get(i).setCount(products.get(i).getCount()+1);
          }
        }
        keeper.saveProducts(products);
        keeper.saveHistories(histories);
    }
    
    private Set<Integer> printGivenProducts(){
        System.out.println("Список выданной обуви: ");
        Set<Integer> setNumberGivenProducts = new HashSet<>();
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null 
                    && histories.get(i).getReturnedDate() == null
                    && histories.get(i).getProduct().getCount()
                        <histories.get(i).getProduct().getQuantity()
                    ){
                System.out.printf("%d. Обувь: %s купил %s %s%n",
                        i+1,
                        histories.get(i).getProduct().getProductname(),
                        histories.get(i).getCustomer().getFirstname(),
                        histories.get(i).getCustomer().getLastname()
                );
                setNumberGivenProducts.add(i+1);
            }
        }
        if(setNumberGivenProducts.isEmpty()){
            System.out.println("нет купленной обуви");
        }
        return setNumberGivenProducts;
    }
}
