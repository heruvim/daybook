package wicket.template.parts.header;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.validation.validator.StringValidator;

public class LoginPanel extends Panel
{
    public LoginPanel(String id)
    {
        super( id );

        Form loginForm = new Form( "loginForm" );

        TextField userNameField = new TextField( "username" );
        userNameField.add( new StringValidator.MinimumLengthValidator( 3 ) );

        PasswordTextField passwordField = new PasswordTextField( "password" );
        passwordField.add( new StringValidator.MinimumLengthValidator( 3 ) );

        loginForm.add( userNameField );
        loginForm.add( passwordField );

        add( loginForm );
    }
}
