package com.rout.usermgmt.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

/**
 * @author sagar@sagarrout.com
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditedEntity {

  @Version
  @Column(nullable = false, columnDefinition = "int8")
  protected long version;

  @Column(nullable = false, updatable = false, columnDefinition = "timestamp with time zone")
  @CreatedDate
  protected LocalDateTime createdDate;

  @Column(nullable = false, columnDefinition = "timestamp with time zone")
  @LastModifiedDate
  protected LocalDateTime lastUpdated;

  @Column(nullable = false, columnDefinition = "text")
  @CreatedBy
  protected String createdBy;

  @Column(nullable = false, columnDefinition = "text")
  @LastModifiedBy
  protected String lastModifiedBy;

  public long getVersion() {
    return version;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }
}
