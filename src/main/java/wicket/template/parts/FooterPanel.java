package wicket.template.parts;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class FooterPanel extends Panel
{
  public FooterPanel(String id)
  {
    super( id );
    add( new Label( "message", "Footer message" ) );
  }
}
