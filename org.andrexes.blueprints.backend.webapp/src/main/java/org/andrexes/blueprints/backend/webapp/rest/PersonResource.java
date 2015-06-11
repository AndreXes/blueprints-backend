package org.andrexes.blueprints.backend.webapp.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.andrexes.blueprints.backend.webapp.model.Person;
import org.andrexes.blueprints.backend.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("person")
@Api(value = "person", description = "Resource for manipulating persons.")
public class PersonResource {

    private static final String ID = "ID of the person";
    private static final String CREATE_PERSON_BODY = "Person to be created. ID for this person is not needed as it will be automatically generated.";
    private static final String UPDATE_PERSON_BODY = "Person to be Updated. ID for this person is not needed as it will be replaced with the ID from path.";

    @Autowired
    private PersonService personService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation("Retrieves all persons.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persons retrieved successfully.")
    })
    public List<Person> getPersons() {
        return personService.getAllPersons();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation("Retrieve person with provided id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person with provided id retrieved successfully.",
                    response = Person.class),
                    @ApiResponse(code = 404, message = "Person with provided id does not exist.", response = String.class)
    })
    public Person getPersonById(@ApiParam(ID) @PathParam("id") final int id) {
        return personService.getPersonById(id);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation("Creates a person with provided id and returns created person.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person updated successfully.",
                    response = Person.class),
                    @ApiResponse(code = 404, message = "Person with provided id does not exist.", response = String.class)
    })
    public Person createPerson(@ApiParam(CREATE_PERSON_BODY) final Person person) {
        return personService.createPerson(person);
    }

    @PUT
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation("Updates a person with provided id and returns updated person.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person updated successfully.",
                    response = Person.class),
                    @ApiResponse(code = 404, message = "Person with provided id does not exist.", response = String.class)
    })
    public Person updatePerson(@ApiParam(ID) @PathParam("id") final int id,
            @ApiParam(UPDATE_PERSON_BODY) final Person person) {
        person.setId(id);
        return personService.updatePerson(person);
    }

    @DELETE
    @Path("{id}")
    @ApiOperation("Deletes a person with provided id.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Person delete successfully."),
            @ApiResponse(code = 404, message = "Person with provided id does not exist.", response = String.class)
    })
    public void deleteModel(@ApiParam(ID) @PathParam("id") final int id) {
        personService.deletePerson(id);
    }

}
