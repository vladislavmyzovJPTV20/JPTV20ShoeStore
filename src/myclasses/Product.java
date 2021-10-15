/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import java.util.Arrays;

/**
 *
 * @author pupil
 */
public class Product {
    private String productname;
    private String model;
    private String color;
    private String manufacturer;
    private Size[] size;
    private double price;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Size[] getSize() {
        return size;
    }

    public void setSize(Size[] size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Тип обуви: " + productname + ". Модель обуви: " + model + ". Цвет обуви: " + color + ". Производитель: " + manufacturer + ". Размер: " + Arrays.toString(size) + ". Цена: " + price;
    }
}
