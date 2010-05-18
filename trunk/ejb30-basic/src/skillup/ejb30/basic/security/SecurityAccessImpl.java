package skillup.ejb30.basic.security;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(SecurityAccess.class)
public class SecurityAccessImpl implements SecurityAccess {

	@Override
	@RolesAllowed("admin")
	public String adminMethods() {
		return "管理者権限が持っています！";
	}

	@Override
	@RolesAllowed("cunsumer")
	public String cunsumerMethods() {
		return "利用者権限が持っています！";
	}

	@Override
	@RolesAllowed("developer")
	public String developerMethods() {
		return "開発者権限が持っています！";
	}

	@Override
	@PermitAll
	public String anonymousMethods() {
		return "何にも権限が持っていません！";
	}

}
