package idv.tlliu.springboot.carol.sample.model.services;

import idv.tlliu.springboot.carol.model.entity.User;
import idv.tlliu.springboot.carol.sample.model.common.exceptions.InstanceNotFoundException;

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
	public User checkUser(Long userId) throws InstanceNotFoundException;
	
}
