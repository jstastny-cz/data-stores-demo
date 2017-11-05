package org.jboss.demo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PeopleFinderInCsv implements PeopleFinder {

	private File csv;

	public PeopleFinderInCsv(File csv) {
		if (csv == null || !csv.isFile()) {
			throw new IllegalArgumentException("Invalid file passed to PeopleFinderInCsv");
		}
		this.csv = csv;
	}

	public List<Person> findAll() {
		List<Person> people = new ArrayList<>();
		try {
			List<String> lines = getDataLines();
			people.addAll(lines.stream().map((s) -> getPerson(s)).collect(Collectors.toList()));
		} catch (IOException e) {
			// TODO: should handle properly
			System.out.println(e.getMessage());
		}
		return people;
	}

	public List<Person> findAllSortedByLastNameDesc() {
		return findAll().stream().sorted((Person p1, Person p2) -> p2.getLastName().compareTo(p1.getLastName()))
				.collect(Collectors.toList());
	}

	public Person findPersonWithHighestStreetNumber() {
		return findAll().stream().max((Person p1, Person p2) -> p1.getStreetNumber() - p2.getStreetNumber()).get();
	}

	public List<String> getDataLines() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(URI.create("file://" + csv.getAbsolutePath())));
		lines.remove(0);
		return lines;
	}

	public Person getPerson(String s) {
		List<String> strings = Arrays.asList(s.split(",")).stream().map((i) -> i.trim())
				.map((i) -> (i.startsWith("\"") && i.endsWith("\"") ? i.replaceAll("\"", "") : i))
				.collect(Collectors.toList());
		return new Person(strings.get(0), strings.get(1), strings.get(2), Integer.valueOf(strings.get(3)),
				strings.get(4));
	}

}
