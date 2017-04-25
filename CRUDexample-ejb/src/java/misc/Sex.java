/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

public enum Sex{
    MALE("Male"),
    FEMALE("Female");
   
    private final String label;
    
    private Sex(String label){
        this.label=label;
    }

    public String getLabel() {
        return this.label;
    }
    
    
}