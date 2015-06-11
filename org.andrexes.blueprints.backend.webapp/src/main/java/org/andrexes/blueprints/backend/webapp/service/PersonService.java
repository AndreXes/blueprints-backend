package org.andrexes.blueprints.backend.webapp.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.andrexes.blueprints.backend.webapp.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    private static final String PERSON_DOES_NOT_EXIST = "Person with id [{0}] does not exist.";

    private volatile Map<Integer, Person> persons = new LinkedHashMap<>();

    public List<Person> getAllPersons() {
        return new ArrayList<>(persons.values());
    }

    public Person getPersonById(final int id) {
        return Optional.ofNullable(persons.get(id)).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(PERSON_DOES_NOT_EXIST, id)));
    }

    public Person createPerson(final Person person) {
        final int id = persons.keySet().stream().max((p1, p2) -> p1.compareTo(p2)).orElse(1) + 1;
        person.setId(id);
        persons.put(id, person);
        return getPersonById(id);
    }

    public Person updatePerson(final Person person) {
        final int id = person.getId();
        if (persons.containsKey(id)) {
            persons.put(id, person);

        } else {
            new NotFoundException(MessageFormat.format(PERSON_DOES_NOT_EXIST, id));
        }

        return getPersonById(id);
    }

    public void deletePerson(final int id) {
        if (persons.containsKey(id)) {
            persons.remove(id);

        } else {
            new NotFoundException(MessageFormat.format(PERSON_DOES_NOT_EXIST, id));
        }

    }
}
