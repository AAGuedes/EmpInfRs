/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.emprinfrs_arteagaairam.service.rest;

import com.google.gson.Gson;
import es.cifpcm.emprinfrs_arteagaairam.dao.DaoFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Enlaces de interes:
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jax-rs/getting-started-with-jax-rs.html
 *
 * @author Airam
 */
@Path("discosArteagaA")
@Produces(MediaType.APPLICATION_JSON)
public class Rest {

    // LLAMA AL METODO QUE RECUPERA LOS DATOS DEL DATABASE.PROPERTIES
    DaoFactory daoFactory = new DaoFactory();

    public Rest() {
    }

    @GET
    public Response getTiendas() {
        return Response.ok(new Gson().toJson(daoFactory.readDiscoDao().getDiscos())).build();
    }

}
