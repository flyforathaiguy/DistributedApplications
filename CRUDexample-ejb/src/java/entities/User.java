/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jaspe
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull(message="Please choose a username")
    private String userName;
    @NotNull(message="Please choose a password")
    private String password;
    
    
    @Embedded
    private UserDetails details;
    
    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "address_fk")
    private Address address;
    
    @OneToMany(orphanRemoval=true)
    @JoinTable(name="joined_user_order",
            joinColumns = @JoinColumn(name="user_fk"),
            inverseJoinColumns = @JoinColumn(name="order_fk"))
    private List<UserOrder> placedOrders = new ArrayList<>();

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    public UserOrder getOrder() {
//        return order;
//    }
//
//    public void setOrder(UserOrder order) {
//        this.order = order;
//    }
    

    public List<UserOrder> getPlacedOrders(){
        return this.placedOrders;
    }
    
    public void clearPlacedOrders(){
        this.placedOrders.clear();
    }
    
    public void addPlacedOrder(UserOrder newOrder){
        this.placedOrders.add(newOrder);
    }
    
    public void setPlacedOrders(List<UserOrder> newOrderList){
        this.placedOrders = newOrderList;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ id=" + userName + " ]";
    }
    
}
