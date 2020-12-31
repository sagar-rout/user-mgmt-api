package com.rout.usermgmt.repo;

import com.rout.usermgmt.domain.Role;
import com.rout.usermgmt.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author sagar@sagarrout.com
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
  Optional<Role> findByType(RoleType type);
}
