package idv.tlliu.springboot.carol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import idv.tlliu.springboot.carol.model.entity.User;
import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  /**
   * Exists by user name.
   *
   * @param userName the user name
   * @return true, if successful
   */
  boolean existsByUserName(String userName);

  /**
   * Find by user name.
   *
   * @param userName the user name
   * @return the optional
   */
  Optional<User> findByUserName(String userName);
  
  
  @Query("select u from User u where u.email = :email")
  User getUserByEmail(@Param("email") String maillAddress);

  @Modifying
  @Transactional
  @Query("update User u set u.phoneNumber = :phoneNumber where u.email in (:email)")
  int setPhoneNumberByEmail(@Param("phoneNumber") String phoneNumber, @Param("email") List<String> mailAddresses);


  // @Query("select new com.citi.kyc.core.services.brms.domain.PeriodicAttestDTO(tg.localTaskID, "
  //         + "tg.WorkItemType, tg.taskRole, (select usr.firstName || ' ' ||usr.lastName from com.citi.kyc.core.userprofile.domain.UserProfile usr where usr.id = tg.workItemAssignedTo) as workItemAssignedTo, "
  //         +"(select grp.groupDescriptor from com.citi.kyc.core.permissions.domain.Group frp where grp.groupCode = tg.groupCode) as groupCode, tg.workItemStatus)"
  //         + " from com.citi.kyc.core.services.brms.domain.TaskGlobal tg, ProcessInstanceXref xref"
  //         + " where xref.parentProcessInstanceXrefId = "
  //         + "(select globalProcessInstanceId.uuid from com.citi.kyc.core.services.brms.domain.TaskGlobal where id.uuid= :globalWorkItemId) and tg.globalProcessInstanceId.uuid = xref.id.uuid order by tg.localTaskID")
  // List<PeriodicAttestDTO> getAttestationSummaryById(@Param("globalWorkItemId") String globalWorkItemId);
  
  List<User> findByCountry(Integer country, Pageable pageable);

  List<User> findByNameIn(List<String> names, Pageable pageable);

}
