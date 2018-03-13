
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
    
    Object readWorldFromXml() throws JAXBException, IOException, ClassNotFoundException{
        InputStream input = getClass().getClassLoader().getResourceAsStream("world.xml");
        ObjectInputStream ois = new ObjectInputStream(input);
        Object world = ois.readObject();
        return world;
    };
    void saveWordlToXml(World world) throws FileNotFoundException{
    OutputStream output = new FileOutputStream("world.xml");
    }
}
