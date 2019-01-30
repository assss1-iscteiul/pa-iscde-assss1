package codegenerationproj;

import org.eclipse.core.runtime.IExtension;

public enum CGExtensionPoint {
	CLASS,
	CLASSMAIN,
	ENUM,
	INTERFACE;

	private IExtension[] extensions;

	public IExtension[] getExtensions() {
		return extensions;
	}

	public boolean existsId(String id) {
		for (IExtension e : extensions)
			if (e.getUniqueIdentifier().equals(id))
				return true;

		return false;
	}

	public void checkId(String id) {
		if (!existsId(id))
			throw new IllegalArgumentException("invalid id '" + id + "' for a " + name().toLowerCase());
	}

}