package com.rout.usermgmt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author sagar@sagarrout.com
 */
@Entity
public class Role extends AuditedEntity {

  @Id
  @GeneratedValue
  @Column(name = "role_id")
  private UUID id;

  @Column(nullable = false, unique = true, length = 50, updatable = false)
  @Enumerated(EnumType.STRING)
  private RoleType type;

  @Column(nullable = false, length = 150)
  private String description;

  public UUID getId() {
    return id;
  }

  public Role setId(UUID id) {
    this.id = id;
    return this;
  }

  public RoleType getType() {
    return type;
  }

  public Role setType(RoleType type) {
    this.type = type;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Role setDescription(String description) {
    this.description = description;
    return this;
  }
}
