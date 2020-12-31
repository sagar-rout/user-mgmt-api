package com.rout.usermgmt.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author sagar@sagarrout.com
 */
@Entity
@Table(name = "users")
public class User extends AuditedEntity {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private UUID id;

  @Column(nullable = false, unique = true, columnDefinition = "text")
  private String emailAddress;

  @Column(nullable = false, columnDefinition = "text")
  private String firstName;

  @Column(columnDefinition = "text")
  private String lastName;

  @Column(nullable = false, columnDefinition = "timestamp with time zone")
  private LocalDateTime dob;

  @Column(nullable = false, columnDefinition = "text", unique = true)
  private String phoneNumber;

  @Column(nullable = false)
  private boolean isActive = true;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
  private Set<Role> roles = new HashSet<>();

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "password_id")
  private Password password;

  public UUID getId() {
    return id;
  }

  public User setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public User setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public User setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public User setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public LocalDateTime getDob() {
    return dob;
  }

  public User setDob(LocalDateTime dob) {
    this.dob = dob;
    return this;
  }

  public boolean isActive() {
    return isActive;
  }

  public User setActive(boolean active) {
    isActive = active;
    return this;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public User setRoles(Set<Role> roles) {
    this.roles.clear();
    this.roles = roles;
    return this;
  }

  public User addRole(Role role) {
    this.getRoles().add(role);
    return this;
  }

  public User removeRole(Role role) {
    this.getRoles().remove(role);
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public User setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public Password getPassword() {
    return password;
  }

  public User setPassword(Password password) {
    this.password = password;
    return this;
  }
}
