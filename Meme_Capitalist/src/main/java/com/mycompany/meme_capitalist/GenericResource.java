/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.meme_capitalist;

import com.google.gson.Gson;
import generated.World;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

/**
 * REST Web Service
 *
 * @author Risitas
 */
@Path("")
public class GenericResource {

    @Context
    private UriInfo context;
    private Services s;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        this.s = new Services();
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.meme_capitalist.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("world")
    @Produces("application/xml")
    public Response getXml() {
        World w;
        try {
            w = s.getWorld();
            return Response.ok(w).build();
        } catch (JAXBException e) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, e);
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("world")
    @Produces("application/json")
    public World getGson() {
        World w;
        try {
            w = s.getWorld();
            //new Gson().toJson(w); pas besoin ça se fait automatiquement !
            //return Response.ok(w).build();
            return w;
        } catch (JAXBException e) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, e);
        }
        //return Response.status(Response.Status.NOT_FOUND).build();
        return null;
    }

}
