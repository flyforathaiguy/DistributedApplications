/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Address;
import entities.User;
import entities.UserDetails;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import misc.Sex;
import model.UserFacade;

/**
 *
 * @author jaspe
 */
@Named(value = "userController")
@SessionScoped

public class userController implements Serializable {

    @EJB
    private UserFacade userFacade;
    
    private User user = new User();
    private UserDetails userDetails = new UserDetails();
    
    private Address address = new Address();
    
    private String loginUserName;
    private String loginPassword;
    
    public userController() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    
//    public SelectItem[] getSexes(){
//        
//        System.out.println("Called getSexes()");
//        for(Sex s : Sex.values()){
//            System.out.println(s.toString());
//            System.out.println(s.getClass());
//        }
//        
//        SelectItem[] items = new SelectItem[Sex.values().length];
//        int i = 0;
//        for(Sex s : Sex.values()){
//            items[i++] = new SelectItem(s, s.getLabel());
//        }
//        return items;
//        //return Sex.values();
//    }
    
    public Sex[] getSexes(){
        return Sex.values();
    }
    
    public String login(){
        
        try{
            User user = this.userFacade.find(this.loginUserName);
            if(user != null && user.getPassword().equals(this.loginPassword)){
                this.user = user;
                return "login_succes";   
            }
            else
                return "login_failed";
        } catch(Exception e){
            return "login_failed";  
        }
    }
    
    public String add(){
        this.userFacade.create(user);
        return "user_created";
    }

}
