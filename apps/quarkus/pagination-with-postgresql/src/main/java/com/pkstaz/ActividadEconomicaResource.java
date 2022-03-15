package com.pkstaz;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
@Path("/recursos/v1/carpeta.tributaria/{id}/actividades_economicas/")
@Produces("application/json")
@Consumes("application/json")
public class ActividadEconomicaResource {

    @GET
    public Response ActividadEconomica(@PathParam("id") Integer id, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) {

        PanacheQuery<PanacheEntityBase> actEconomica;

        if(null != page && null != limit){
            actEconomica = ActividadEconomica.findAll().page(Page.of(page, limit));
        }else{
            actEconomica = ActividadEconomica.findAll();
        }

        return Response.ok(actEconomica.list()).status(200).build();
    }
}