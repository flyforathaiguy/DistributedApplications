/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="ShoeEntity")
public class ShoeEntity extends ProductEntity {
    
    int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
}
