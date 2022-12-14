package com.kadirirpik.entities.abstracts;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract public class BaseEntity implements Serializable {
    public static final Long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Kim ekledi ?
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    // Kim ne zaman ekledi ?
    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private java.util.Date createdDate;

    // Kim Güncelledi ?
    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

    // Kim ne zaman Güncelledi ?
    @Column(name = "updated_dated")
    @LastModifiedDate
    private java.util.Date updatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "system_created_date", updatable = false)
    private java.util.Date systemCreatedDate;

}
