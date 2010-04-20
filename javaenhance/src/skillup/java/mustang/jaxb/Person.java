package skillup.java.mustang.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

	public enum Language {
		C, CPlusPlus, D, CSharp, Scheme, StandardML, Lisp, Basic, ObjectiveCaml, Python, Haskell, Perl, Delphi, Prolog, JavaScript, VisualBasic, ActionScript, Icon, Ruby, PHP
	}
	
	private String name;
	
	private int age;
	
	private Language[] languages;
	
	
	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@XmlElementWrapper(name="Languages")
    @XmlElement
	public Language[] getLanguages() {
		return languages;
	}

	public void setLanguages(Language ... languages) {
		this.languages = languages;
	}
	
	
}
