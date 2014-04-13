import org.apache.wicket.PageParameters;
import wicket.template.parts.ContentPanel;
import wicket.template.Template;

/**
 * Homepage
 */
public class HomePage extends Template
{

	private static final long serialVersionUID = 1L;

	 /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {
        super();
        replace( new ContentPanel(CONTENT_ID) );
    }
}
