package codegenerationproj;

import java.io.File;

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

import pt.iscte.pidesco.projectbrowser.model.PackageElement;

public class CreatePackageDialog implements Runnable {

	private Display window;
	private PackageElement packageElement;

	public CreatePackageDialog(PackageElement packageElement) {
		this.packageElement = packageElement;

	}

	@Override
	public void run() {
		window = new Display();
		Shell shell = new Shell(window);
		shell.setSize(600, 400);
		shell.setLayout(new RowLayout());
		shell.setText("Create new package on " + packageElement.getName() + ".");

		// contains button, text box and the check box
		Composite compositeWidget = new Composite(shell, SWT.NONE);

		compositeWidget.setLayout(new RowLayout(SWT.VERTICAL));

		// text box
		Text textField = new Text(compositeWidget, SWT.SINGLE);

		Button createPackageButton = new Button(compositeWidget, SWT.PUSH);
		createPackageButton.setText("Accept");

		// cancel button
		Button cancelButton = new Button(compositeWidget, SWT.PUSH);
		cancelButton.setText("Cancel");

		// listener for the create package button
		createPackageButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String path = packageElement.getFile().getAbsolutePath();

				// replace \ with \\
				String fixedPath = path.replace("\\", "\\\\");

				System.out.println(fixedPath);
				String packageName = textField.getText();

				if (!packageName.contains(" ")) {
					new File(fixedPath + "\\\\" + packageName).mkdirs();
				} else {
					MessageDialog.openWarning(shell, "Warning", "Please insert a valid package name.");
				}
				close();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
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
