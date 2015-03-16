package com.jayelh.jl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.junit.Test;

import javax.ws.rs.core.MediaType;


/**
 * Integration Test for the DecisionResource
 */
public class DecisionResourceIT extends JerseyTest {
    @Override
    protected AppDescriptor configure() {
        return new WebAppDescriptor.Builder().build();
    }

    /**
     * Tests first repeatedly request for the same user until debt to high. After that tries another user once.
     * @throws JSONException
     * @throws URISyntaxException
     */
    @Test
    public void testRepeated() throws JSONException,
            URISyntaxException {
        WebResource webResource = client().resource("http://localhost:8080/");
        JSONObject json = webResource.path("/decisions").type(MediaType.APPLICATION_JSON).post(JSONObject.class, "{\"email\": \"a@b.se\", \"first_name\" : \"a\", \"last_name\": \"b\", \"amount\": 100}");
        assertEquals(Boolean.TRUE, json.get("accepted"));
        assertEquals("ok", json.get("reason"));


        json = webResource.path("/decisions").type(MediaType.APPLICATION_JSON).post(JSONObject.class, "{\"email\": \"a@b.se\", \"first_name\" : \"a\", \"last_name\": \"b\", \"amount\": 100}");
        assertEquals(Boolean.TRUE, json.get("accepted"));
        assertEquals("ok", json.get("reason"));


        json = webResource.path("/decisions").type(MediaType.APPLICATION_JSON).post(JSONObject.class, "{\"email\": \"a@b.se\", \"first_name\" : \"a\", \"last_name\": \"b\", \"amount\": 800}");
        assertEquals(Boolean.TRUE, json.get("accepted"));
        assertEquals("ok", json.get("reason"));


        json = webResource.path("/decisions").type(MediaType.APPLICATION_JSON).post(JSONObject.class, "{\"email\": \"a@b.se\", \"first_name\" : \"a\", \"last_name\": \"b\", \"amount\": 100}");
        assertEquals(Boolean.FALSE, json.get("accepted"));
        assertEquals("debt", json.get("reason"));

        json = webResource.path("/decisions").type(MediaType.APPLICATION_JSON).post(JSONObject.class, "{\"email\": \"b@b.se\", \"first_name\" : \"a\", \"last_name\": \"b\", \"amount\": 100}");
        assertEquals(Boolean.TRUE, json.get("accepted"));
        assertEquals("ok", json.get("reason"));
    }

    /**
     * Tests a request with to high amount.
     * @throws JSONException
     * @throws URISyntaxException
     */
    @Test
    public void testAmount() throws JSONException,
            URISyntaxException {
        WebResource webResource = client().resource("http://localhost:8080/");
        JSONObject json = webResource.path("/decisions").type(MediaType.APPLICATION_JSON).post(JSONObject.class, "{\"email\": \"c@b.se\", \"first_name\" : \"a\", \"last_name\": \"b\", \"amount\": 1100}");
        assertEquals(Boolean.FALSE, json.get("accepted"));
        assertEquals("amount", json.get("reason"));

    }

    /**
     * Tests a request with illegal amount.
     * @throws JSONException
     * @throws URISyntaxException
     */
    @Test
    public void testIllegalAmount() throws JSONException,
            URISyntaxException {
        WebResource webResource = client().resource("http://localhost:8080/");
        try {
            JSONObject json = webResource.path("/decisions").type(MediaType.APPLICATION_JSON).post(JSONObject.class, "{\"email\": \"d@b.se\", \"first_name\" : \"a\", \"last_name\": \"b\", \"amount\": -100}");
        } catch (UniformInterfaceException e) {
            assertEquals("Should have recived 400", e.getResponse().getStatus(), 400);
            return;
        }

        assertTrue("Should have thrown exception", Boolean.FALSE);
    }

    /**
     * Tests a request with a non allowed mediatype.
     * @throws JSONException
     * @throws URISyntaxException
     */
    @Test
    public void testWrongMediaType() throws JSONException,
            URISyntaxException {
        WebResource webResource = client().resource("http://localhost:8080/");
        try {
            JSONObject json = webResource.path("/decisions").type(MediaType.APPLICATION_XHTML_XML).post(JSONObject.class, "{\"email\": \"d@b.se\", \"first_name\" : \"a\", \"last_name\": \"b\", \"amount\": 100}");
        } catch (UniformInterfaceException e) {
            assertEquals("Should have recived 415", e.getResponse().getStatus(), 415);
            return;
        }

        assertTrue("Should have thrown exception", Boolean.FALSE);
    }

    /**
     * Tests a request with a missing JSON key/value pair
     * @throws JSONException
     * @throws URISyntaxException
     */
    @Test
    public void testIncompleteJSON() throws JSONException,
            URISyntaxException {
        WebResource webResource = client().resource("http://localhost:8080/");
        try {
            JSONObject json = webResource.path("/decisions").type(MediaType.APPLICATION_XHTML_XML).post(JSONObject.class, "{\"email\": \"d@b.se\", \"first_name\" : \"a\", \"amount\": 100}");
        } catch (UniformInterfaceException e) {
            assertEquals("Should have recived 415", e.getResponse().getStatus(), 415);
            return;
        }

        assertTrue("Should have thrown exception", Boolean.FALSE);
    }
}