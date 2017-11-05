package org.jboss.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PeopleInCsvTest {

	private static final ArrayList<String> SORTED = new ArrayList<String>(
			Arrays.asList("Albrecht", "Brahms", "Cantrell", "Doyle", "Evans", "Finch", "Goodguy"));

	@Test
	public void findAll() throws Exception {
		List<Person> people = null;
		PeopleInCsv pfic = new PeopleInCsv(new File("data.csv"));
		people = pfic.findAll();
		Assert.assertNotNull(people, "Returned list of people can't be null");
		Assert.assertEquals(people.size(), 7, "There should have been 7 people.");
		Assert.assertEquals(people.stream().map((p) -> p.getLastName()).collect(Collectors.toList()), SORTED,
				"Lines sorted in wrong order.");
	}

	@Test
	public void findAllSortedByLastNameDesc() throws Exception {
		List<Person> people = null;
		PeopleInCsv pfic = new PeopleInCsv(new File("data.csv"));
		people = pfic.findAllSortedByLastNameDesc();
		Assert.assertNotNull(people, "Returned list of people can't be null");
		Assert.assertEquals(people.size(), 7, "There should have been 7 people.");
		List<String> toReverse = new ArrayList<String>();
		Collections.copy(toReverse, SORTED);
		Collections.reverse(toReverse);
		Assert.assertEquals(people.stream().map((p) -> p.getLastName()).collect(Collectors.toList()), toReverse,
				"Lines sorted in wrong order.");
	}

	@Test
	public void findPersonWithHighestStreetNumber() throws Exception {
		Person p = null;
		PeopleInCsv pfic = new PeopleInCsv(new File("data.csv"));
		p = pfic.findPersonWithHighestStreetNumber();
		Assert.assertNotNull(p);
		Assert.assertEquals(p, new Person("Gery", "Goodguy", "Groundfloor G", 7, "Geneve"));
	}

	@Test
	public void getDataLines() throws IOException {
		PeopleInCsv pfic = new PeopleInCsv(new File("data.csv"));
		List<String> lines = pfic.getDataLines();
		Assert.assertNotNull(lines, "Lines reading failed");
		Assert.assertEquals(lines.size(), 7, "Unexpected number of data lines.");
	}

	@Test
	public void getPerson() {
		String line = "\"fstName\",\"lastName\", \"street\",\"5\", \"city\"";
		PeopleInCsv pfic = new PeopleInCsv(new File("data.csv"));
		Person p = pfic.getPerson(line);
		Assert.assertNotNull(p, "Person parsing failed");
		Assert.assertEquals(p, new Person("fstName", "lastName", "street", 5, "city"), "Person not parsed properly.");
	}
}
