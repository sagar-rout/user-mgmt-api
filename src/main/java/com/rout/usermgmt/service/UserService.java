package com.rout.usermgmt.service;

import com.rout.usermgmt.domain.Role;
import com.rout.usermgmt.domain.RoleType;
import com.rout.usermgmt.domain.User;
import com.rout.usermgmt.dto.UserDto;
import com.rout.usermgmt.dto.UserDtoAlias;
import com.rout.usermgmt.dto.transformer.UserTransformer;
import com.rout.usermgmt.repo.RoleRepository;
import com.rout.usermgmt.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** @author sagar@sagarrout.com */
@Service
public class UserService {

  private static final RoleType DEFAULT_ROLE = RoleType.ROLE_USER;

  private final UserTransformer userTransformer;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserService(
      UserTransformer userTransformer,
      UserRepository userRepository,
      RoleRepository roleRepository) {
    this.userTransformer = userTransformer;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Transactional(readOnly = true)
  public UserDtoAlias getById(UUID id) {
    return userRepository
        .findById(id)
        .map(userTransformer::fromDomainToDtoAlias)
        .orElseThrow(() -> new NoDataFoundException(String.format("Resource %s not found", id)));
  }

  @Transactional
  public UserDtoAlias create(UserDto userDto) {
    final User toBeSavedUser = userTransformer.fromDtoToDomain(userDto);
    final Role defaultRole = roleRepository.findByType(DEFAULT_ROLE).get();
    toBeSavedUser.addRole(defaultRole);
    return userTransformer.fromDomainToDtoAlias(userRepository.save(toBeSavedUser));
  }

  @Transactional
  public void deleteById(UUID id) {
    userRepository.deleteByIdIfExist(id);
  }

  @Transactional
  public UserDtoAlias update(UserDtoAlias userDtoAlias, UUID id) {
    final User user =
        userRepository
            .findById(id)
            .orElseThrow(
                () -> new NoDataFoundException(String.format("Resource %s not found", id)));

    // TODO : Check if we don't need to call save explicitly or use hibernate dirty checking
    return userTransformer.fromDomainToDtoAlias(
        userRepository.save(userTransformer.fromDtoAliasToDomain(userDtoAlias, user)));
  }
}
