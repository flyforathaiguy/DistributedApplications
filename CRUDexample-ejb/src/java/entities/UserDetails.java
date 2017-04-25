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
import misc.MaritalStatus;
import misc.Sex;

@Embeddable
public class UserDetails implements Serializable {
    
   @Enumerated(EnumType.STRING)
   private MaritalStatus maritalStatus;
   
   @Enumerated(EnumType.STRING)
   private Sex sex;

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
   
   
   
    
}