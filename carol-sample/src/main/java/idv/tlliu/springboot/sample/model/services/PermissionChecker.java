package idv.tlliu.springboot.sample.model.services;

import idv.tlliu.springboot.sample.model.common.exceptions.InstanceNotFoundException;
import idv.tlliu.springboot.sample.model.entities.Users;

/**
 * The Interface PermissionChecker.
 */
public interface PermissionChecker {
	
	/**
	 * Check user exists.
	 *
	 * @param userId the user id
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	public void checkUserExists(Long userId) throws InstanceNotFoundException;
	
	/**
	 * Check user.
	 *
	 * @param userId the user id
	 * @return the user
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	public Users checkUser(Long userId) throws InstanceNotFoundException;
	
}
