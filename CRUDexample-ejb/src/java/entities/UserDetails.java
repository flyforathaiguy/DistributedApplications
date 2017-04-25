/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import misc.Sex;

@Embeddable
public class UserDetails implements Serializable {
    
    private String firstName;
    private String lastName;
    private String email;
    
   @Enumerated(EnumType.STRING)
   private Sex sex;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        System.out.println("Set sex with object");
        this.sex = sex;
    }
    
    public void setSex(String sex){
        System.out.println("Set sex with string");
    }

   
   
   
    
}