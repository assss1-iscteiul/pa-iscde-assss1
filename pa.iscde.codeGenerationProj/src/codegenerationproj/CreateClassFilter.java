package codegenerationproj;

import java.io.File;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.extensibility.PidescoServices;
import pt.iscte.pidesco.javaeditor.internal.JavaEditorActivator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.extensibility.ProjectBrowserFilter;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;

public class CreateClassFilter implements ProjectBrowserFilter {

	private JavaEditorServices services;
	private File file;
	

	public CreateClassFilter() {
		BundleContext context = JavaEditorActivator.getInstance().getContext();

		ServiceReference<PidescoServices> ref = context.getServiceReference(PidescoServices.class);
		final PidescoServices pidescoServices = context.getService(ref);
	}

	@Override
	public boolean include(SourceElement element, PackageElement parent) {
		return false;
	}
}