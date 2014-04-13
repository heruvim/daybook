package wicket.template.parts;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentModel;
import org.apache.wicket.model.Model;
import wicket.template.Template;

/**
 * Homepage
 */
public class ContentPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	 /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
   public ContentPanel(String id)
   {
     super( id );

     final Label label = new Label( "info", "" );
     label.setOutputMarkupId( true );

     final AjaxCheckBox showIndicator = new AjaxCheckBox("indicator")
     {
       @Override
       protected void onUpdate(AjaxRequestTarget ajaxRequestTarget)
       {

       }
     };

     add( label );
     add( showIndicator );
   }
}
