package scm.validation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class IESoapValidator {
	
	static final String XPATH_1 = "/service/operations/operationInfo";
	
	static final String XPATH_2 = "/definitions/portType/operation";

	public static void main(String[] args) throws Exception {
		
		if (args.length != 3) {
			System.out.println("Usage: /path/SimpleTaskDispatcher.wsdl /path/DefaultSystem_IESoap.esbsvc /path/result.log");
			return;
		}
		
		BufferedWriter bfw = null;

		String logFile = null;
		
		String wsdlFile = null;
		
		String esbsvcFile = null;

		DocumentBuilderFactory domFactory = null;
		DocumentBuilder builder = null;
		Document doc = null;

		XPathFactory factory = null;
		XPath xpath = null;
		XPathExpression expr = null;

		try {
			
			wsdlFile = args[0];
			
			esbsvcFile = args[1];
			
			logFile = args[2];
			
			bfw = new BufferedWriter(new FileWriter(logFile));

			domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(false);
			builder = domFactory.newDocumentBuilder();

			factory = XPathFactory.newInstance();
			xpath = factory.newXPath();
			expr = xpath.compile(XPATH_1);

			doc = builder.parse(esbsvcFile);

			NodeList nodes = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);

			Set<String> operationSet = new HashSet<String>();

			for (int i = 0; i < nodes.getLength(); i++) {
				operationSet.add(nodes.item(i).getAttributes().getNamedItem("wsdlOperation").getNodeValue());
			}

			doc = builder.parse(wsdlFile);

			expr = xpath.compile(XPATH_2);

			nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

			String operationName = null;
			for (int i = 0; i < nodes.getLength(); i++) {
				operationName = nodes.item(i).getAttributes().getNamedItem("name").getNodeValue();
				if (!operationSet.contains(operationName)) {
					bfw.write("missing operation defination [" + operationName + "] in DefaultSystem_IESoap.esbsvc\n");
				}
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw e;
			
		} finally {
			
			if(bfw != null) bfw.close();
			bfw = null;
			domFactory = null;
			builder = null;
			doc = null;
			factory = null;
			xpath = null;
			expr = null;
		}
	}
}
