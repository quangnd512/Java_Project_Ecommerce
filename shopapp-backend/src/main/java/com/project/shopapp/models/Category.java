package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "categories")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tự tăng Id
    private Long id;

    @Column(name= "name", nullable = false) // nullable = false: Không được trống
    private String name;
}
