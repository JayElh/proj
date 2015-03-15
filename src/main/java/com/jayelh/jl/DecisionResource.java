package com.jayelh.jl;
 
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class DecisionResource {

    @Context
    private DecisionManager decisionManager;

	@POST
	@Path("/decisions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(Data data) {
	    Result result = decisionManager.decision(data);
 
	    return Response.status(200).entity(result).build();
	}
}
