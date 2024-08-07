package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "fullname", length = 100)
    private String fullName;

    @Column(name= "phone_number", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name= "address", length = 200)
    private String address;

    @Column(name= "pasword", length = 200, nullable = false)
    private String pasword;

    private boolean active;

    @Column(name= "date_of_birth")
    private Date dateOfBirth;

    @Column(name= "facebook_account_id")
    private int facebookAccountId;

    @Column(name= "google_account_id")
    private int googleAccountId;

    @ManyToOne // 1 role có thể có nhiều users )
    @JoinColumn(name= "role_id")
    private Role role;
}
