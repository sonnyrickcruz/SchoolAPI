package com.school.api.constants;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class Config implements EnvironmentAware {

	private static Environment environment;

	@SuppressWarnings("static-access")
	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;//NOSONAR
	}

	/**
	 * Return the property value associated with the given key, or null if the
	 * key cannot be resolved.
	 * 
	 * @param property
	 * @return string
	 */
	public static String getProperty(String property) {
		return environment.getProperty(property);
	}

}
