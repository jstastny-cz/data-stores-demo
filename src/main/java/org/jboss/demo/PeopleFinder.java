package org.jboss.demo;

import java.util.List;

public interface PeopleFinder {

	/**
	 * Finds all the people.
	 */
	List<Person> findAll() throws Exception;

	/**
	 * Finds all the people sorted in desc order by lastName.
	 */
	List<Person> findAllSortedByLastNameDesc() throws Exception;

	/**
	 * Finds a single person with Highest streetNumber;
	 */
	Person findPersonWithHighestStreetNumber() throws Exception;
}
