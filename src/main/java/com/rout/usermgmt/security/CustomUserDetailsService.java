package com.rout.usermgmt.security;

import com.rout.usermgmt.domain.User;
import com.rout.usermgmt.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/** @author sagar@sagarrout.com */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Optional<User> optionalUser = userRepository.findByEmailAddress(username);

    optionalUser.orElseThrow(
        () -> new UsernameNotFoundException(String.format("User %s not found", username)));

    final Set<SimpleGrantedAuthority> roles =
        optionalUser.get().getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getType().getText()))
            .collect(Collectors.toSet());

    return new org.springframework.security.core.userdetails.User(
        optionalUser.get().getEmailAddress(),
        optionalUser.get().getPassword().getEncryptedText(),
        roles);
  }
}
