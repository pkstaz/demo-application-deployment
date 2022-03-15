package com.pkstaz;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@Path("/recursos/v1/carpeta.tributaria/{id}/actividades_economicas/")
public class ActividadEconomicaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActividadEconomica> ActividadEconomica() {

        System.out.println();
        //Page.of(X, Y) X = Page; Y = Rows
        PanacheQuery<ActividadEconomica> actEconomica = ActividadEconomica.findAll().page(Page.of(1, 1));

        return actEconomica.list();
    }
}