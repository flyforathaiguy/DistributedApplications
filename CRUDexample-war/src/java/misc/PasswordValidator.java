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
    
    private static final String PATTERN = "^(?=.*[A_Z])(?=.*[a-z])(?=.*[0-9]).{6,}$";
    
    private Pattern pattern;
    private Matcher patternMatcher;
    
    public PasswordValidator(){
        pattern = Pattern.compile(PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        
        if(value == null)
            return;
        System.out.println("Of hier????");
        String password = value.toString();
        System.out.println("hier 1");
        UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmPassword");
        System.out.println("hier 2");
        String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
        System.out.println("hier 3");
        
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
        patternMatcher = pattern.matcher(value.toString());
        
                
        if(!patternMatcher.matches()){
            FacesMessage message = new FacesMessage("Password must contains at least one lower and upper letter, one number and be at least 6 characters long");  
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }   
}
