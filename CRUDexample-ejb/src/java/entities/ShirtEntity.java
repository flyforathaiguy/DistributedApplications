/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value="ShirtEntity")
public class ShirtEntity extends ProductEntity {
    
    @NotNull(message="Please enter a color")
    String color;

    @Override
    public String toInfoString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(name);
        builder.append('\t');
        builder.append(", color: ");
        builder.append(color);
        builder.append('\t');
        builder.append(", price: ");
        builder.append(price);
        return builder.toString();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
