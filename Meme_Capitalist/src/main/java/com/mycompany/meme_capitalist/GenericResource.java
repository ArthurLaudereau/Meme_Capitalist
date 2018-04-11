/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.meme_capitalist;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import generated.PallierType;
import generated.ProductType;
import generated.World;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("generic")
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
   public World getXml(@Context HttpServletRequest request) throws FileNotFoundException, JAXBException {
        String username = request.getHeader("X-User");
        return s.getWorld(username);
    }
    
    @GET
    @Path("world")
    @Produces("application/json")
    public World getGson(@Context HttpServletRequest request) throws JAXBException {
        World w;
        String username = request.getHeader("X-User");;
        w = s.getWorld(username);
        //new Gson().toJson(w); pas besoin ça se fait automatiquement !
        //return Response.ok(w).build();
        return w;
    }

}
