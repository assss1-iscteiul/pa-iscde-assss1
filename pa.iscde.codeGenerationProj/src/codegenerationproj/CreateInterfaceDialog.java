package codegenerationproj;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.projectbrowser.model.PackageElement;

public class CreateInterfaceDialog implements Runnable {

	private Display window;
	private PackageElement packageElement;
	private CodeGenerationServices cgServices;

	public CreateInterfaceDialog(PackageElement packageElement) {
		this.packageElement = packageElement;
	}

	@Override
	public void run() {

		BundleContext context = CGActivator.getInstance().getContext();

		ServiceReference<CodeGenerationServices> ref = context.getServiceReference(CodeGenerationServices.class);
		cgServices = context.getService(ref);

		window = new Display();
		Shell shell = new Shell(window);
		shell.setSize(600, 400);
		shell.setLayout(new RowLayout());
		shell.setText("Create new interface on " + packageElement.getName() + ".");

		// contains button, text box and the check box
		Composite compositeWidget = new Composite(shell, SWT.NONE);

		compositeWidget.setLayout(new RowLayout(SWT.VERTICAL));

		// text box
		Text textField = new Text(compositeWidget, SWT.SINGLE);

		Button createInterfaceButton = new Button(compositeWidget, SWT.PUSH);
		createInterfaceButton.setText("Accept");

		// cancel button
		Button cancelButton = new Button(compositeWidget, SWT.PUSH);
		cancelButton.setText("Cancel");

		// listener for the create interface button
		createInterfaceButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = packageElement.getFile().getAbsolutePath();

				String interfaceName = textField.getText();

				if (!interfaceName.contains(" ")) {

					((CodeGenerationServicesImpl) cgServices).create(path, interfaceName, CGExtensionPoint.INTERFACE);

				} else {
					MessageDialog.openWarning(shell, "Warning", "Please insert a valid interface name.");
				}

				close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		cancelButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!window.readAndDispatch())
				window.sleep();
		}
		window.dispose();

	}

	private void close() {
		window.dispose();
	}

}
