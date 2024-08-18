package com.example.ridesharing.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "_driver")
public class Driver {

    @Id
    @Column(name = "_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_driver__id_seq")
    @SequenceGenerator(name = "_driver__id_seq", sequenceName = "_driver__id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "_name", nullable = false)
    private String name;

    @Column(name = "_phone_no", nullable = false)
    private String phoneNo;

    @Column(name = "_email", nullable = false)
    private String email;

    @Column(name = "_password", nullable = false)
    private String password;

    @Column(name = "_engaged", nullable = false)
    private Boolean engaged = false;

    @Column(name = "_driver_license", nullable = false)
    private String driverLicense;

    @Column(name = "_car_registration_no", nullable = false)
    private String carRegistrationNo;

    @Column(name = "_car_make", nullable = false)
    private String carMake;

    @Column(name = "_car_color", nullable = false)
    private String carColor;

    @Column(name = "_longitude", nullable = false)
    private Integer longitude;

    @Column(name = "_latitude", nullable = false)
    private Integer latitude;

    @OneToMany(mappedBy = "driver")
    private Set<Booking> bookings;


}
