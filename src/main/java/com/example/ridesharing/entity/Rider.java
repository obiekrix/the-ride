package com.example.ridesharing.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "_rider")
public class Rider {

    @Id
    @Column(name = "_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_rider__id_seq")
    @SequenceGenerator(name = "_rider__id_seq", sequenceName = "_rider__id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "_name", nullable = false)
    private String name;

    @Column(name = "_phone_no", nullable = false)
    private String phoneNo;

    @Column(name = "_email", nullable = false)
    private String email;

    @Column(name = "_password", nullable = false)
    private String password;

    @Column(name = "_longitude", nullable = false)
    private Integer longitude;

    @Column(name = "_latitude", nullable = false)
    private Integer latitude;

    @OneToMany(mappedBy = "rider")
    private Set<Booking> bookings;
}
