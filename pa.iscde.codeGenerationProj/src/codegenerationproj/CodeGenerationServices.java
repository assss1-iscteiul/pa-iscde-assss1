package codegenerationproj;

	/**
	 * 
	 * @author Andre
	 * Service containing operations offered by the CodeGeneration infrastructure.
	 */
public interface CodeGenerationServices {

	
	/**
	 * Creates java file objects
	 * @param path (non-null) directory in which the java file object will be created
	 * @param name (non-null) name of the java file object
	 * @param type (non-null) type of java file object to be created
	 */
	void create(String path, String name, CGExtensionPoint type);

}
