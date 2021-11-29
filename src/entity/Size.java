/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + this.shoesSize;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Size other = (Size) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.shoesSize != other.shoesSize) {
            return false;
        }
        return true;
    }
}