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
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {
    
    //Met dank aan https://www.mkyong.com/jsf2/custom-validator-in-jsf-2-0/
    
    private static final String PATTERN = "^[A-Za-z0-9.-_%+-]+@[A-za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    private Pattern pattern;
    private Matcher patternMatcher;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("hier dan???");
        if(value == null)
            return;
        patternMatcher = pattern.matcher(value.toString());
        if(!patternMatcher.matches()){
            FacesMessage message = new FacesMessage("Enter a valid email");  
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
    
    public EmailValidator(){
        pattern = Pattern.compile(PATTERN);
    }
    
}
