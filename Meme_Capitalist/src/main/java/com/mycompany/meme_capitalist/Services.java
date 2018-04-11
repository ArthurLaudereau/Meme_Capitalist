package com.mycompany.meme_capitalist;

import generated.PallierType;
import generated.ProductType;
import generated.World;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXB;
import static javax.xml.bind.JAXB.unmarshal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Risitas
 */
public class Services {

    private World readWorldFromXml() throws JAXBException {
        JAXBContext cont = JAXBContext.newInstance(World.class);
        Unmarshaller m = cont.createUnmarshaller();
        World world;
        try {
            world = (World) m.unmarshal(new File("world.xml"));
        } catch (UnmarshalException e) {
            InputStream input = getClass().getClassLoader().getResourceAsStream("world.xml");
            world = (World) m.unmarshal(input);
        }
        return world;
    }
    ;
    
    private void saveWordlToXml(String username, World world) throws FileNotFoundException, JAXBException {
        JAXBContext cont = JAXBContext.newInstance(World.class);
        Marshaller m = cont.createMarshaller();
        OutputStream os = new FileOutputStream("world.xml");
        m.marshal(world, os);
    }

    public World getWorld(String username) throws JAXBException {
        World world = readWorldFromXml();
        return world;
    }
    
    
    // prend en paramètre le pseudo du joueur et le produit
// sur lequel une action a eu lieu (lancement manuel de production ou
// achat d’une certaine quantité de produit)
// renvoie false si l’action n’a pas pu être traitée
public Boolean updateProduct(String username, ProductType newproduct) throws JAXBException, FileNotFoundException {
 // aller chercher le monde qui correspond au joueur
 World world = getWorld(username);
 // trouver dans ce monde, le produit équivalent à celui passé
 // en paramètre
 ProductType product = findProductById(world, newproduct.getId());
 if (product == null) { return false;}

 // calculer la variation de quantité. Si elle est positive c'est
 // que le joueur a acheté une certaine quantité de ce produit
 // sinon c’est qu’il s’agit d’un lancement de production.
 int qtchange = newproduct.getQuantite() - product.getQuantite();
 if (qtchange > 0) {
 // soustraire de l'argent du joueur le cout de la quantité
 // achetée et mettre à jour la quantité de product

 } else {
 // initialiser product.timeleft à product.vitesse
 // pour lancer la production
 }
 // sauvegarder les changements du monde
 saveWordlToXml(username, world);
 return true;
 }

public ProductType findProductById(World world, int idcible){
        ProductType product = world.getProducts().getProduct().get(idcible-1);
            return product;
}

// prend en paramètre le pseudo du joueur et le manager acheté.
// renvoie false si l’action n’a pas pu être traitée
public Boolean updateManager(String username, PallierType newmanager) throws JAXBException, FileNotFoundException {
 // aller chercher le monde qui correspond au joueur
 World world = getWorld(username);
 // trouver dans ce monde, le manager équivalent à celui passé
 // en paramètre
 PallierType manager = findManagerByName(world, newmanager.getName());
 if (manager == null) {
 return false;
 }

 // débloquer ce manager

 // trouver le produit correspondant au manager
 ProductType product = findProductById(world, manager.getIdcible());
 if (product == null) {
 return false;
 }
 // débloquer le manager de ce produit

 // soustraire de l'argent du joueur le cout du manager
 // sauvegarder les changements au monde
 saveWordlToXml(username, world);
 return true;
 }

    public PallierType findManagerByName(World world, String name){
        for (PallierType manager : world.getManagers().getPallier()){
            if (manager.getName()==name){
                return manager;
            }
        }
        return null;
    }



}
