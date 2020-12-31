package com.rout.usermgmt.service;

import com.rout.usermgmt.domain.Password;
import com.rout.usermgmt.domain.User;
import com.rout.usermgmt.dto.PasswordRequestDto;
import com.rout.usermgmt.repo.UserRepository;
import com.rout.usermgmt.security.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author sagarout.com */
@Service
public class PasswordService {

  private final TokenService tokenService;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public PasswordService(
      TokenService tokenService,
      UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  /**
   * Change old password with new password
   *
   * @param passwordRequestDto
   */
  @Transactional
  public void updatePassword(final PasswordRequestDto passwordRequestDto) {
    final String userEmailAddress = tokenService.getEmailAddressFromToken();

    final User user =
        userRepository
            .findByEmailAddress(userEmailAddress)
            .orElseThrow(
                () ->
                    new IllegalStateException(
                        String.format(
                            "User %s not found with this email Address.", userEmailAddress)));

    final Password password = user.getPassword();
    final boolean matches =
        bCryptPasswordEncoder.matches(
            passwordRequestDto.getCurrentPasswordInPlainText(), password.getEncryptedText());

    if (!matches) {
      throw new IllegalArgumentException(String.format("Current password does not match."));
    }

    password.setEncryptedText(
        bCryptPasswordEncoder.encode(passwordRequestDto.getNewPasswordInPlainText()));
  }
}
