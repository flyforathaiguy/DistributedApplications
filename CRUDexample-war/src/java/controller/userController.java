/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javax.faces.context.FacesContext;
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

public class userController implements Serializable {

    @EJB
    private AddressFacade addressFacade;

    @EJB
    private UserOrderFacade userOrderFacade;

    @EJB
    private UserFacade userFacade;
    
    private User user;
    private UserDetails userDetails;
    private Address address;
    
    private String loginUserName;
    private String loginPassword;
    
    private boolean admin = false;
    
    private List<String> listOrder = new ArrayList<>();
    
    public userController() {
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public String makeAdmin(){
        this.admin = true;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", true);
        return "made_admin";
    }
    
    public String undoAdmin(){
        this.admin = false;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", false);
        return "made_admin";
    }

    public List<String> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<String> listOrder) {
        this.listOrder = listOrder;
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
            User secondUser = this.userFacade.find(this.loginUserName);
            if(secondUser != null && secondUser.getPassword().equals(this.loginPassword)){
                this.user = secondUser;
                this.userDetails = user.getDetails();
                this.address = user.getAddress();
                return "login_success";   
            }
            else
                return "login_failed";
        } catch(Exception e){
            return "login_failed";  
        }
    }
    
    public String createNewUser(){
        this.user = new User();
        this.userDetails = new UserDetails();
        this.user.setDetails(this.userDetails);
        return "create_a_user";
    }
    
    public String existingUser(){
        return "existing_user";
    }
    
    public String addUser(){
        this.userFacade.create(user);
        return "user_created";
    }
    
    public String confirmAddress(){
        return "address_confirmed";
    }
    
    public String confirmNewAddress(){
        if(this.user.getAddress() == null){
            this.user.setAddress(this.address);
            this.addressFacade.create(this.address);
            this.userFacade.edit(this.user);
        }
        else{
            this.addressFacade.edit(this.address);
        }
        
        return "address_confirmed";
    }
    
    public String confirmOrder(){
        System.out.println("1-------------------------------");
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("2-------------------------------");
        List<ProductEntity> productList = context.getApplication().evaluateExpressionGet(context, "#{cartController.findAll()}", List.class);
        System.out.println("3------------------------------- size: " + productList.size());
        
        listOrder.clear();
        UserOrder order = new UserOrder();
        System.out.println("4-------------------------------");
        for(ProductEntity p : productList){
            order.addProduct(p.toInfoString());
            listOrder.add(p.toInfoString());
        }
        System.out.println("5------------------------------- size: " + listOrder.size());
        
        this.user.addPlacedOrder(order);
        System.out.println("6-------------------------------");
        this.userOrderFacade.create(order);
        System.out.println("7-------------------------------");
        this.userFacade.edit(this.user);
        
        System.out.println("8-------------------------------");
        context.getApplication().evaluateExpressionGet(context, "#{cartController.finalizeCheckout()}", Boolean.class);
        
        return "order_completed";
    }
    
    public String endShopping(){
        return "end_shopping";
    }
}
