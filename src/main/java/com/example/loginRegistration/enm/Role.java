package com.example.loginRegistration.enm;

import java.util.Set;

public enum Role {
	ADMIN ,//(Set.of(Permission.USER_READ, Permission.USER_REGESTER, Permission.USER_DELETE, 
			//Permission.USER_UPDATE)),
	USER; //(Set.of(Permission.USER_REGESTER, Permission.USER_READ));
	

	/**private final Set<Permission> permission;
	
	Role(Set<Permission> permission){
		this.permission = permission;
	}
	
	public Set<Permission> getPermission() {
		return permission;
	}**/

}
