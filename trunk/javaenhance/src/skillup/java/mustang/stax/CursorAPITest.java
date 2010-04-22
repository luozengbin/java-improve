package skillup.java.mustang.stax;

import java.io.FileOutputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class CursorAPITest {
	
	public static void main(String[] args) throws XMLStreamException {
		
		XMLInputFactory  inFactory = XMLInputFactory.newFactory();
		
		XMLStreamReader streamReader = inFactory.createXMLStreamReader(CursorAPITest.class.getResourceAsStream("sample-for-stax.xml"));
		
		while(streamReader.hasNext()){
			System.out.println(streamReader.next());
		}
		
		XMLOutputFactory  outFactory = XMLOutputFactory.newFactory();
		
		//outFactory.createXMLStreamWriter(new FileOutputStream("/"))
	}
}
