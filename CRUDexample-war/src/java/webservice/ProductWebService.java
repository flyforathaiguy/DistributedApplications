/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import entities.ShirtEntity;
import entities.ShoeEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import model.ShirtEntityFacade;
import model.ShoeEntityFacade;

/**
 *
 * @author Robin
 */
@WebService(serviceName = "ProductWebService")
public class ProductWebService {
    
    @EJB
    private ShirtEntityFacade shirtEntityFacade;
    private ShirtEntity shirt = new ShirtEntity();
    
    @EJB
    private ShoeEntityFacade shoeEntityFacade;
    private ShoeEntity shoe = new ShoeEntity();
    
    @WebMethod(operationName = "findShirts")
    public String findAllShirts(){
        return this.shirtEntityFacade.findAll().toString();
    }
    
    @WebMethod(operationName = "findShoes")
    public String findAllShoes(){
        return this.shoeEntityFacade.findAll().toString();
    }
    
    
    
}
