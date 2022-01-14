package com.automation.config;

import java.util.Properties;

public class ReadPropertiesFileLib {
	private static Properties properties = null;

	public ReadPropertiesFileLib() {
		ReadPropertiesFileLib.properties = new Properties();
		try {
			ReadPropertiesFileLib.properties.load(ReadPropertiesFileLib.class.getResourceAsStream("/testconfig.properties"));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		assert !ReadPropertiesFileLib.properties.isEmpty();
	}

	public static String getProperty(final String keyName) {
		if (ReadPropertiesFileLib.properties == null) {
			new ReadPropertiesFileLib();
		}
		String property = ReadPropertiesFileLib.properties.getProperty(keyName);
		return property != null ? property.trim() : property;
	}

}
