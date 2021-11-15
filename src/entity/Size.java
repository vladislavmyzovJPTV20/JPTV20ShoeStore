/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Size implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int shoesSize;

    public Size() {
    }   
 
    public int getShoesSize() {
        return shoesSize;
    }

    public void setShoesSize(int shoesSize) {
        this.shoesSize = shoesSize;
    }

    @Override
    public String toString() {
        return "Size{" + "shoesSize=" + shoesSize + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}