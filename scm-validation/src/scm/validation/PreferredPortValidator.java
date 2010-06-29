package scm.validation;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.sun.xml.internal.stream.events.CharacterEvent;

public class PreferredPortValidator {

	public static void main(String[] args) throws IOException,
			XMLStreamException {
		
		if (args.length != 3) {
			System.out.println("Usage: /path/bpel_xml_list.txt /path/result.log /path/config.properties");
			return;
		}
		
		BufferedWriter bfw = null;
		
		BufferedReader bfr = null;
		
		Reader propReader = null;
		
		XMLEventReader reader = null;
		
		BufferedInputStream stream = null;
		
		List<String> noPreferredPortProcessIds = new ArrayList<String>();
			
		try {
			
			String bpelXmlList = args[0];
			
			String logFile = args[1];
			
			Properties prop = new Properties();
			// ê›íËèÓïÒèâä˙âª
			propReader = new InputStreamReader(new FileInputStream(args[2]), System.getProperty(Constants.FILE_ENCODING));
			prop.load(propReader);
			propReader.close();
			propReader = null;
			
			noPreferredPortProcessIds = Arrays.asList(((String)prop.getProperty(Constants.NOPREFERREDPORT_PROCESSID)).split("[,]"));
			
			bfw = new BufferedWriter(new FileWriter(logFile));
			

			bfr = new BufferedReader(new FileReader(bpelXmlList));

			String bpelXml = null;
			
			XMLInputFactory factory = XMLInputFactory.newInstance();
			

			while (true) {
				
				boolean validated = true;
				
				bpelXml = bfr.readLine();
				if (bpelXml == null || bpelXml.trim().length() == 0) {
					break;
				}
				
				if(stream != null) stream.close();
				
				stream = new BufferedInputStream(new FileInputStream(bpelXml));

				reader = factory.createXMLEventReader(stream);

				while (reader.hasNext()) {

					XMLEvent event = reader.nextEvent();

					if (event.isStartElement()) {
						StartElement element = (StartElement) event;

						if ("property".equals(element.getName().getLocalPart())
								&& "wsdlLocation".equals(element
										.getAttributeByName(new QName("name"))
										.getValue())) {
							
							String wsdlLocation = ((CharacterEvent)reader.nextEvent()).getData();
							
							if(wsdlLocation.startsWith("http://")){
								
								boolean skip = false;
								for (String processId : noPreferredPortProcessIds) {
									if(wsdlLocation.contains(processId)){
										skip = true;
										break;
									}
								}
								
								if(skip){
									continue;
								}
								
								boolean hasNextStartElement = false;
								
								while (reader.hasNext()) {
									
									event = reader.nextEvent();
									if (event.isStartElement()) {
										
										hasNextStartElement = true;
										
										if(!"property".equals(((StartElement) event).getName().getLocalPart())){
											validated = false;
											break;
										}
										
										if(!"preferredPort".equals(((StartElement) event).getAttributeByName(new QName("name")).getValue())){
											validated = false;
											break;
										}
										
										event = reader.nextEvent();
										
										if(event.isEndElement()){
											validated = false;
											break;
										}
										
										String preferredPort = ((CharacterEvent)event).getData();
										
										if(preferredPort == null || preferredPort.trim().length() == 0){
											validated = false;
											break;
										}
										
										break;
									}
								}
								
								if(!validated || !hasNextStartElement){
									bfw.write("missing preferredPort defination (" + bpelXml + ")\n");
									break;
								}
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bfw != null) bfw.close();
			if(bfr != null) bfr.close();
			if(propReader != null) propReader.close();
			if(stream != null) stream.close();
		}
	}
}
