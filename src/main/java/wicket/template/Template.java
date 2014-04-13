package wicket.template;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import wicket.template.parts.FooterPanel;
import wicket.template.parts.header.HeaderPanel;
import wicket.template.parts.MenuPanel;

public class Template extends WebPage
{
  public static final String CONTENT_ID = "contentComponent";

  public Template()
  {
    add( new HeaderPanel( "headerPanel" ) );
    add( new MenuPanel("menuPanel"));
    add( new FooterPanel("footerPanel"));
    add( new Label( CONTENT_ID, "Put your content here" ) );
  }
}
