/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.SingletonBean;
import entities.Address;
import entities.ProductEntity;
import entities.User;
import entities.UserDetails;
import entities.UserOrder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import misc.PasswordValidator;
import misc.Sex;
import model.AddressFacade;
import model.UserFacade;
import model.UserOrderFacade;

/**
 *
 * @author jaspe
 */
@Named(value = "userController")
@SessionScoped

public class UserController implements Serializable {

    @EJB
    private AddressFacade addressFacade;

    @EJB
    private UserOrderFacade userOrderFacade;

    @EJB
    private SingletonBean singletonBean;

    @EJB
    private UserFacade userFacade;

    private User user = new User();
    private UserDetails userDetails;
    
    private Address address = new Address();
    private UserOrder order = new UserOrder();
    private List<String> orderList = new ArrayList<>();
    
    private String loginUserName;
    private String loginPassword;
    
    

    public UserController() {
    }

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
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
    
    public String createNewUser(){
        user = new User();
        userDetails = new UserDetails();
        address = new Address();
        return "create_a_user";
    }
    
    public String existingUser(){
        return "existing_user";
    }
    
    public String login(){
        
        try{
            User newUser = this.userFacade.find(this.loginUserName);
            if(newUser != null && newUser.getPassword().equals(this.loginPassword)){
                this.user = newUser;
                this.userDetails = this.user.getDetails();
                this.address = this.user.getAddress();
                return "login_success";   
            }
            else
                return "login_failed";
        } catch(Exception e){
            System.out.println("Login error: " + e.getMessage());
            return "login_failed";  
        }
    }
    
    public String add(){
        try{
            User secondUser = this.userFacade.find(this.user.getUserName());
            if(secondUser != null)
                return "user_already_exists";
            else{
                this.user.setDetails(this.userDetails);
                this.userFacade.create(user);
                this.address = new Address();
                return "user_created";
            }
                
        } catch(Exception e){
            System.out.println("User create error: " + e.getMessage());
            return "user_creation_failed";
        }
        
    }
    
    public String confirmAddress(){
        return "address_confirmed";
    }
    
    public String confirmNewAddress(){
        //User has no address --> create in DB
        if(this.user.getAddress() == null){
            this.user.setAddress(this.address);
            this.addressFacade.create(this.address);
            this.userFacade.edit(this.user);
        }
        else{   //User already has an address --> change in DB
            this.addressFacade.edit(this.address);
        }
        return "address_confirmed";
    }
    
    public String confirmOrder(){
        
        System.out.println("Confirm order start------------------------------------");

        FacesContext context = FacesContext.getCurrentInstance();
        List<ProductEntity> productList = (List<ProductEntity>) context.getApplication().evaluateExpressionGet(context, "#{cartController.findAll()}", List.class);
        System.out.println("tussen------------" + productList.size());
        
        for(ProductEntity prod : productList){
            System.out.println("Confirm order: " + prod.toInfoString());
            order.addProduct(prod.toInfoString());
        }
        
        System.out.println("Confirm order 2, order size: " + order.getProducts().size());
        user.addPlacedOrder(order);
        orderList = order.getProducts();
        //user.setOrder(order);
        
        System.out.println("Confirm order 3: id= " + order.getId());
        this.userOrderFacade.create(order);
        
        System.out.println("Confirm order 4: id= " + order.getId());
        this.userFacade.edit(user);
       
        System.out.println("Confirm order 5");
        singletonBean.addPurchase(productList.size());
        System.out.println("Confirm order 6");
        context.getApplication().evaluateExpressionGet(context, "#{cartController.finalizeCheckout()}", Boolean.class);
        System.out.println("Confirm order 7");
        System.out.println("Singleton purchases: " + singletonBean.getPurchasesToday());
        order = new UserOrder();
        
        return "order_completed";
    }
    
    public String endShopping(){
        //Going back to index.html
        return "end_shopping";
    }
}
