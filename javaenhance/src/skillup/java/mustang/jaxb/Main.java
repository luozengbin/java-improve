package skillup.java.mustang.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws JAXBException, SAXException,
			FileNotFoundException {

		JAXBContext context = JAXBContext.newInstance(Person.class);

		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new File("src/skillup/java/mustang/jaxb/schema1.xsd"));

		Marshaller marshaller = context.createMarshaller();
		marshaller.setSchema(schema);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		Person person = new Person();
		person.setName("太郎");
		person.setAge(77);
		person.setLanguages(Person.Language.C, Person.Language.CPlusPlus);

		marshaller.marshal(person, new FileOutputStream("src/skillup/java/mustang/jaxb/person.xml"));

		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setSchema(schema);
		Person person2 = (Person)unmarshaller.unmarshal(new FileInputStream("src/skillup/java/mustang/jaxb/person.xml"));
		System.out.println("name     : " + person2.getName());
        System.out.println("age      : " + person2.getAge());
        for(Person.Language language : person2.getLanguages()) {
            System.out.println("language : " + language);
        }

	}
}
