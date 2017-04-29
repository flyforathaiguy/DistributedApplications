/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jaspe
 */

//@Singleton
//@Startup

//Injection
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myqueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConsumerBean implements MessageListener {
    
    public ConsumerBean(){
    }
    
    @PostConstruct
    private void setupListener(){
//Using JNDI lookup
        
        System.out.println("Setup up listener...");
        
//        try{
//            Context jndiContext = new InitialContext();
//            ConnectionFactory factory = (ConnectionFactory) jndiContext.lookup("jms/myQueueFactory");
//            Destination queue = (Destination) jndiContext.lookup("jms/myqueue");
//            
//            try(JMSContext context = factory.createContext()){
//                context.createConsumer(queue).setMessageListener(this);
//            }
//            
//            System.out.println("Listener succesfully set up");
//            
//        } catch(NamingException e){
//            Logger.getLogger(ConsumerBean.class.getName()).log(Level.SEVERE, null, e);
//        }
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage textmessage = (TextMessage) message;
        try {
            System.out.println("Message from queue: " + textmessage.getText());
        } catch (JMSException ex) {
            Logger.getLogger(ConsumerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
