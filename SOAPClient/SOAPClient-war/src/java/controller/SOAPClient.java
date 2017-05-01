/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jp.ProductWebService;
import com.jp.ProductWebService_Service;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Robin
 */
@Named(value = "sOAPClient")
@SessionScoped
public class SOAPClient implements Serializable {

    /**
     * Creates a new instance of SOAPClient
     */
    
    private String shirts;
    private String shoes;
    private String shirtsMinusBrackets;
    private String shoesMinusBrackets;
    
    
    public SOAPClient() {
        
        ProductWebService_Service service = new ProductWebService_Service();
        final ProductWebService proxy = service.getProductWebServicePort();
        shirts = proxy.findShirts();
        shoes = proxy.findShoes();
        
        shirtsMinusBrackets = shirts.substring(1, shirts.length() -1);
        shoesMinusBrackets = shoes.substring(1, shoes.length() -1);
        String[] resplitShirts = shirtsMinusBrackets.split( ", ");
        String[] resplitShoes = shoesMinusBrackets.split( ", ");

        for ( String s: resplitShirts ) {
            System.out.println( shirts );
        }
        
        for ( String s : resplitShoes ) {
            System.out.println( shoes );
        }
        
        System.out.println(shirtsMinusBrackets);
        System.out.println(shoesMinusBrackets);
        
        
    }


    public String getShirts() {
        return shirts;
    }

    public void setShirts(String shirts) {
        this.shirts = shirts;
    }

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    public String getShirtsMinusBrackets() {
        return shirtsMinusBrackets;
    }

    public void setShirtsMinusBrackets(String shirtsMinusBrackets) {
        this.shirtsMinusBrackets = shirtsMinusBrackets;
    }

    public String getShoesMinusBrackets() {
        return shoesMinusBrackets;
    }

    public void setShoesMinusBrackets(String shoesMinusBrackets) {
        this.shoesMinusBrackets = shoesMinusBrackets;
    }

    
}
