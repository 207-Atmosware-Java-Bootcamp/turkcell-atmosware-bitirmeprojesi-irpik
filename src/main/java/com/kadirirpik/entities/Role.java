package com.kadirirpik.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="role")
public class Role implements Serializable {
    public static final Long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name="role_name", unique = true)
    private ERole roleName;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToMany( mappedBy = "relationRoleRegisterEntities" ,fetch = FetchType.LAZY)
    private Collection<User> userRegisterEntities;

    public Role(ERole roleName) {
        this.roleName = roleName;
    }

}
