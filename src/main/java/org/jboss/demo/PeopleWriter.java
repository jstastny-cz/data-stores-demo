package org.jboss.demo;

public interface PeopleWriter {
	/**
	 * Method that writes given person into a data store.
	 * @param p person to write
	 */
	void write(Person p) throws Exception;
}
