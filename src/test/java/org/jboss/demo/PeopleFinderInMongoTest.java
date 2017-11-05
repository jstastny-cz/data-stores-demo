package org.jboss.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PeopleFinderInMongoTest {
	private static final ArrayList<String> SORTED = new ArrayList<String>(
			Arrays.asList("Albrecht", "Brahms", "Cantrell", "Doyle", "Evans", "Finch", "Goodguy"));
	private static final String DATABASE = "data-store-demo";

	@Test
	public void findAll() throws Exception {
		List<Person> people = null;
		PeopleFinder pfic = new PeopleFinderInMongo(DATABASE);
		people = pfic.findAll();
		Assert.assertNotNull(people, "Returned list of people can't be null");
		Assert.assertEquals(people.size(), 7, "There should have been 7 people.");
		Assert.assertEquals(people.stream().map((p) -> p.getLastName()).collect(Collectors.toList()), SORTED,
				"Lines sorted in wrong order.");
	}

	@Test
	public void findAllSortedByLastNameDesc() throws Exception {
		List<Person> people = null;
		PeopleFinder pfic = new PeopleFinderInMongo(DATABASE);
		people = pfic.findAllSortedByLastNameDesc();
		Assert.assertNotNull(people, "Returned list of people can't be null");
		Assert.assertEquals(people.size(), 7, "There should have been 7 people.");
		List<String> toReverse = (List<String>) SORTED.clone();
		Collections.reverse(toReverse);
		Assert.assertEquals(people.stream().map((p) -> p.getLastName()).collect(Collectors.toList()), toReverse,
				"Lines sorted in wrong order.");
	}

	@Test
	public void findPersonWithHighestStreetNumber() throws Exception {
		Person p = null;
		PeopleFinder pfic = new PeopleFinderInMongo(DATABASE);
		p = pfic.findPersonWithHighestStreetNumber();
		Assert.assertNotNull(p);
		Assert.assertEquals(p, new Person("Gery", "Goodguy", "Groundfloor G", 7, "Geneve"));
	}
}
