package com.rout.usermgmt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author sagar@sagarrout.com
 */
@Entity
public class Password extends AuditedEntity {

  @Id
  @GeneratedValue
  @Column(name = "password_id")
  private UUID id;

  @Column(nullable = false)
  private String encryptedText;

  public UUID getId() {
    return id;
  }

  public Password setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getEncryptedText() {
    return encryptedText;
  }

  public Password setEncryptedText(String encryptedText) {
    this.encryptedText = encryptedText;
    return this;
  }
}
