package com.jayelh.jl;
 
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Jeresey REST Resource handling decisions of purchases
 */
@Path("/")
public class DecisionResource {

    @Context
    private DecisionManager decisionManager;

    /**
     *
     */
	@POST
	@Path("/decisions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeDecision(PurchaseRequest purchaseRequest) {
	    Decision decision = decisionManager.decision(purchaseRequest);
 
	    return Response.status(200).entity(decision).build();
	}
}
