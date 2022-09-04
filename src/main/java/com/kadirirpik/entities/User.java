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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User implements Serializable {
    public static final Long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="picture_url")
    private String pictureUrl;

    @Column(name="enabled")
    private boolean enabled;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(joinColumns =@JoinColumn(name="user_id") ,inverseJoinColumns = @JoinColumn(name="roles_id"))
    private Collection<Role> relationRoleRegisterEntities;

}
