package codegenerationproj;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import pt.iscte.pidesco.extensibility.PidescoServices;

public class CGActivator implements BundleActivator {

	public static final String CGPLUGIN_ID;

	static {
		CGPLUGIN_ID = FrameworkUtil.getBundle(CGActivator.class).getSymbolicName();
	}

	private static BundleContext context;
	private static CGActivator instance;
	private ServiceRegistration<CodeGenerationServices> service;
	private PidescoServices pidescoServices;

	public BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		CGActivator.context = bundleContext;
		instance = this;
		service = context.registerService(CodeGenerationServices.class, new CodeGenerationServicesImpl(), null);

		ServiceReference<PidescoServices> ref = context.getServiceReference(PidescoServices.class);
		pidescoServices = context.getService(ref);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		CGActivator.context = null;
		instance = null;
		service.unregister();
	}

	public static CGActivator getInstance() {
		return instance;
	}

}
