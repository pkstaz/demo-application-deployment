package com.pkstaz.health;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health/")
public class HealthCheck {

    @GET
    public Response ActividadEconomica() {
        return Response.ok("OK").status(200).build();
    }
}