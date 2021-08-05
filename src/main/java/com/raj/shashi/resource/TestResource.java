package com.raj.shashi.resource;


import com.couchbase.client.core.error.DocumentNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.raj.shashi.domain.User;
import com.raj.shashi.service.TestService;
import com.wordnik.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/users")
@Api(value = "/users", description = "users operations")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class TestResource {

    private TestService testService;

    public TestResource(){
        this.testService = new TestService();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get users by id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 500, message = "server error") })
    public Response getUsers(@QueryParam(value = "id") String id, @QueryParam(value = "gender") String gender)
            throws JsonParseException, JsonMappingException, IOException {

        try{
            if(gender!=null){
                List<User> list = testService.getUserByGender(gender);
                return Response.ok(list).status(200).build();
            }
            else {
                User user = testService.getUserById(id);
                if (user != null) {
                    return Response.ok(user).status(200).build();
                }

            }
        }
        catch(DocumentNotFoundException e){
        }

        return Response.status(404).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "create user")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "created"),
            @ApiResponse(code = 500, message = "server error") })
    public Response createUser(@ApiParam User user) {
        return Response.ok(testService.createUser(user)).status(201).build();
    }

}
