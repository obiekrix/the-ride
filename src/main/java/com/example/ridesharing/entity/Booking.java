package com.example.ridesharing.entity;

import com.example.ridesharing.constant.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "_booking")
public class Booking {

    @Id
    @Column(name = "_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_booking__id_seq")
    @SequenceGenerator(name = "_booking__id_seq", sequenceName = "_booking__id_seq", allocationSize = 1)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "_time", nullable = false)
    private Date time;

    @Column(name = "_pickup_longitude", nullable = false)
    private Integer pickupLongitude;

    @Column(name = "_pickup_latitude", nullable = false)
    private Integer pickupLatitude;

    @Column(name = "_destination_longitude", nullable = false)
    private Integer destinationLongitude;

    @Column(name = "_destination_latitude", nullable = false)
    private Integer destinationLatitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "_status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "_rider_id", nullable = false)
    private Rider rider;

    @ManyToOne
    @JoinColumn(name = "_driver_id", nullable = false)
    private Driver driver;

}
