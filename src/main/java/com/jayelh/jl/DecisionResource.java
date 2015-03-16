package com.jayelh.jl;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Jeresey REST Resource, handling decisions of purchases
 */
@Path("/decisions")
public class DecisionResource {

    @Context
    private DecisionManager decisionManager;

    /**
     * Handles POST request for /decision
     *
     * Post body JSON structure(PurchaseRequest):
     *
     * {"email":      <String>,
     * "first_name" : <String>,
     * "last_name":   <String>,
     * "amount":      <int>}
     *
     * @param purchaseRequest JavaBean representation of the incoming JSON object
     * @return the Jersey HTTP response including a JSON representation of Decision
     * @see Decision
     */


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeDecision(PurchaseRequest purchaseRequest) {
        Decision decision = decisionManager.decision(purchaseRequest);

        return Response.status(200).entity(decision).build();
    }

}
