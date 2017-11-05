package org.jboss.demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class PeopleFinderInMongo implements PeopleFinder {

	private String database;

	public PeopleFinderInMongo(String database) {
		super();
		this.database = database;
	}

	@Override
	public List<Person> findAll() {
		try (MongoClient m = new MongoClient()) {
			MongoDatabase d = m.getDatabase(database);
			List<Person> p = new ArrayList<>();
			return d.getCollection("people").find().sort(new Document("lastName", 1)).map((a) -> getPerson(a)).into(p);
		}
	}

	@Override
	public List<Person> findAllSortedByLastNameDesc() {
		try (MongoClient m = new MongoClient()) {
			MongoDatabase d = m.getDatabase("data-store-demo");
			List<Person> p = new ArrayList<>();
			return d.getCollection("people").find().sort(new Document("lastName", -1)).map((a) -> getPerson(a)).into(p);
		}
	}

	@Override
	public Person findPersonWithHighestStreetNumber() {
		try (MongoClient m = new MongoClient()) {
			MongoDatabase d = m.getDatabase("data-store-demo");
			return d.getCollection("people").find().sort(new Document("address.number", -1)).limit(1)
					.map((a) -> getPerson(a)).first();
		}
	}

	private Person getPerson(Document a) {
		Document address = (Document) a.get("address");
		return new Person(a.getString("firstName"), a.getString("lastName"), address.getString("street"),
				address.getInteger("number"), address.getString("city"));
	}
}
