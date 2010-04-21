package skillup.ejb30.basic.other;

import javax.ejb.Stateless;

@Stateless
public class OtherBean implements Other{

	@Override
	public String sayMe() {
		return "other";
	}

}
