package com.rout.usermgmt.dto.transformer;

import com.rout.usermgmt.domain.Password;
import com.rout.usermgmt.domain.User;
import com.rout.usermgmt.dto.UserDto;
import com.rout.usermgmt.dto.UserDtoAlias;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/** @author sagar@sagarrout.com */
@Component
public class UserTransformer {

  private final BCryptPasswordEncoder passwordEncoder;

  public UserTransformer(BCryptPasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public User fromDtoToDomain(UserDto userDto) {
    return new User()
        .setDob(userDto.getDob())
        .setEmailAddress(userDto.getEmailAddress())
        .setFirstName(userDto.getFirstName())
        .setLastName(userDto.getLastName())
        .setPassword(new Password().setEncryptedText(passwordEncoder.encode(userDto.getPassword())))
        .setPhoneNumber(userDto.getPhoneNumber());
  }

  public User fromDtoAliasToDomain(UserDtoAlias userDtoAlias, User user) {
    return user.setDob(userDtoAlias.getDob())
        .setEmailAddress(userDtoAlias.getEmailAddress())
        .setFirstName(userDtoAlias.getFirstName())
        .setLastName(userDtoAlias.getLastName())
        .setPhoneNumber(userDtoAlias.getPhoneNumber());
  }

  public UserDtoAlias fromDomainToDtoAlias(User user) {
    return new UserDtoAlias()
        .setId(user.getId())
        .setDob(user.getDob())
        .setEmailAddress(user.getEmailAddress())
        .setFirstName(user.getFirstName())
        .setLastName(user.getLastName())
        .setPhoneNumber(user.getPhoneNumber());
  }

  public List<UserDtoAlias> fromDomainToDtoAlias(List<User> users) {
    return users.stream().map(this::fromDomainToDtoAlias).collect(Collectors.toList());
  }
}
