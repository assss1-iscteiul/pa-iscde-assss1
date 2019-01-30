package codegenerationproj;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class CodeGenerationServicesImpl implements CodeGenerationServices {

	@Override
	public void create(String path, String name, CGExtensionPoint type) {
		String fixedPath = path.replace("\\", "\\\\");

		String[] packageNameArray = fixedPath.split("\\\\");

		String packageName = (packageNameArray[packageNameArray.length - 1]);

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(fixedPath + "\\\\" + name + ".java"), "utf-8"))) {

			// define the package string. eg: package extensibility
			String packageString = "package " + packageName + ";";

			// write package at the beginning
			writer.write(packageString + "\r\n" + "\r\n");

			// write type and name

			switch (type) {
			case CLASS:
				writer.write("public class " + name + " {" + "\r\n" + "\r\n");

				break;
			case CLASSMAIN:
				writer.write("public class " + name + " {" + "\r\n" + "\r\n");
				writer.write("\tpublic static void main(String[] args) {\r\n" + "\t\t\r\n" + "\t}" + "\r\n");
				break;
			case ENUM:
				writer.write("public enum " + name + " {" + "\r\n" + "\r\n");
				break;
			case INTERFACE:
				writer.write("public interface " + name + " {" + "\r\n" + "\r\n");
			default:
				break;
			}

			writer.write("}");
			writer.close();

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
