/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

//Veel dank aan stackoverflow en https://www.mkyong.com/jsf2/multi-components-validator-in-jsf-2-0/

@FacesValidator("PasswordValidator")
public class PasswordValidator implements Validator {
    
    private static final String PATTERN1 = "^(?=.*[a-z]).+$";
    private static final String PATTERN2 = "^(?=.*[A-Z]).+$";
    private static final String PATTERN3 = "^(?=.*\\d).+$";
    
    private final Pattern pattern1;
    private final Pattern pattern2;
    private final Pattern pattern3;
    
    private Matcher patternMatcher1;
    private Matcher patternMatcher2;
    private Matcher patternMatcher3;
    
    public PasswordValidator(){
        pattern1 = Pattern.compile(PATTERN1);
        pattern2 = Pattern.compile(PATTERN2);
        pattern3 = Pattern.compile(PATTERN3);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        
        if(value == null)
            return;
        String password = value.toString();
        UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
        
        if(password == null || password.isEmpty() || confirmPassword == null || 
                confirmPassword.isEmpty())
            return;
        
        //Check if passwords match
        if(!password.equals(confirmPassword)){
            uiInputConfirmPassword.setValid(false);
            FacesMessage message = new FacesMessage("Passwords do not match");  
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
        //Check if password contains the right characters
        StringBuilder builder = new StringBuilder();
        builder.append("Password does not meet these criteria:");
        int numberOfFlaws = 0;
        
        patternMatcher1 = pattern1.matcher(value.toString());         
        if(!patternMatcher1.matches()){
            numberOfFlaws++;
            builder.append("Contain at least 1 lower case character. ");
        }
        
        patternMatcher2 = pattern2.matcher(value.toString());         
        if(!patternMatcher2.matches()){
            numberOfFlaws++;
            builder.append("Contain at least 1 upper case character. ");
        }
        
        patternMatcher3 = pattern3.matcher(value.toString());         
        if(!patternMatcher3.matches()){
            numberOfFlaws++;
            builder.append("Contain at least 1 number. ");
        }
        
        if(value.toString().length() < 6){
            numberOfFlaws++;
            builder.append("Be at least 6 characters long.");
        }
        
        if(numberOfFlaws > 0){
            FacesMessage message = new FacesMessage(builder.toString());  
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }   
}
