package codegenerationproj;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.extensibility.PidescoTool;
import pt.iscte.pidesco.javaeditor.internal.JavaEditorActivator;
import pt.iscte.pidesco.projectbrowser.internal.ProjectBrowserActivator;
import pt.iscte.pidesco.projectbrowser.internal.ProjectBrowserServicesImpl;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class CreateEnumTool implements PidescoTool {

	public static final String CREATEENUM_ID = "pa.iscde.codeGenerationProj.createEnum";

	protected PackageElement packageElement = null;
	protected ProjectBrowserServices browser;

	public CreateEnumTool() {
		BundleContext context = JavaEditorActivator.getInstance().getContext();

		ProjectBrowserActivator.getInstance().addListener(new ProjectBrowserListener() {

			@Override
			public void selectionChanged(Collection<SourceElement> selection) {
				if (!selection.isEmpty()) {
					SourceElement element = (SourceElement) selection.toArray()[0];
					if (element != null) {
						if (element.isPackage()) {
							packageElement = (PackageElement) element;
						} else if (element.isClass()) {
							packageElement = ((ClassElement) element).getParent();
						}
					} else {
						packageElement = null;
					}
				} else {
					packageElement = null;
				}
			}

			@Override
			public void doubleClick(SourceElement element) {
			}
		});
		
		//update project browser 
		ServiceReference<ProjectBrowserServices> ref2 = context.getServiceReference(ProjectBrowserServices.class);
		browser = context.getService(ref2);
	}

	@Override
	public void run(boolean activate) {
		if (packageElement != null) {
			Thread t = new Thread(new CreateEnumDialog(packageElement));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {}
			
			//update project browser
			((ProjectBrowserServicesImpl)browser).refreshTree();
		}
	}
}
