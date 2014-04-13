package wicket.template.parts.header;

import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;

public class HeaderPanel extends Panel
{
  private static final CompressedResourceReference headerCss =
      new CompressedResourceReference( HeaderPanel.class, "css/header.css");

    public HeaderPanel(String id)
    {
        super( id );

        add( HeaderContributor.forCss( headerCss ) );
        add( new LoginPanel( "loginPanel" ) );

    }
}
