package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name", nullable = false, length = 350)
    private String name;

    private Float price;

    @Column(name= "thumbnail", length = 350)
    private String thumbnail;

    @Column(name= "description")
    private String description;

    @ManyToOne //liên kết từ nhiều sang 1 (1 category có thể có nhiều products )
    @JoinColumn(name= "category_id")
    private Category category;
}
