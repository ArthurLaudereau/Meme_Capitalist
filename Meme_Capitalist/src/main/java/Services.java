
import generated.World;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
    private JAXBContext jaxbContext;
    
    World readWorldFromXml() throws JAXBException{
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream input = getClass().getClassLoader().getResourceAsStream("world.xml");
        return unmarshaller.unmarshal(input, World.class);
        ///ALED CA MARCHE PAS COMMENT ON MARSHALL DEJA ?
    };
    void saveWordlToXml(World world) throws FileNotFoundException{
    OutputStream output = new FileOutputStream("worldSave.xml");
    }
}
