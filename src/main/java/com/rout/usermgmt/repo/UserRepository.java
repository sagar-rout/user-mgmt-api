package com.rout.usermgmt.repo;

import com.rout.usermgmt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author sagar@sagarrout.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  default void deleteByIdIfExist(UUID id) {
    if (this.existsById(id)) {
      this.deleteById(id);
    }
  }

  Optional<User> findByEmailAddress(String emailAddress);
}
