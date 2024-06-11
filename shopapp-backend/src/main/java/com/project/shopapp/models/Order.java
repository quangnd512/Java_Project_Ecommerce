package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name= "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 1 user có thể có nhiều orders
    @JoinColumn(name= "user_id")
    private User user;

    @Column(name= "fullname", length = 100)
    private String fullName;

    @Column(name= "email", length = 100)
    private String email;

    @Column(name= "phone_number", length = 100, nullable = false)
    private String phoneNumber;

    @Column(name= "address", length = 100)
    private String address;

    @Column(name= "note", length = 100)
    private String note;

    @Column(name= "order_date")
    private LocalDateTime orderDate;

    @Column(name= "status")
    private String status;

    @Column(name= "total_money")
    private Integer totalMoney;

    @Column(name= "shipping_method")
    private String shippingMethod;

    @Column(name= "shipping_address")
    private String shippingAddress;

    @Column(name= "shipping_date")
    private Date shippingDate;

    @Column(name= "tracking_number")
    private String trackingNumber;

    @Column(name= "payment_method")
    private String paymentMethod;

    @Column(name= "active")
    private Boolean active;
}
