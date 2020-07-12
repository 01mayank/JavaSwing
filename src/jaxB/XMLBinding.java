package jaxB;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLBinding {
	
	
	public Unmarshaller getXMLUnMarshaller() 
	{
	    JAXBContext jaxbContext;
	    Unmarshaller jaxbUnmarshaller = null;
		try {
			jaxbContext = JAXBContext.newInstance(beans.ProductStore.class);
		    jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return jaxbUnmarshaller;
	}
	
	
	public Marshaller getXMLMarshaller() {
		JAXBContext jaxbContext;
		Marshaller jaxbMarshaller = null;
		try {
			jaxbContext = JAXBContext.newInstance(beans.ProductStore.class);
		    jaxbMarshaller = jaxbContext.createMarshaller();
		    
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return jaxbMarshaller;
	}

}
