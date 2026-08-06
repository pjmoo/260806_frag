package org.example.frag.domain.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {
    private String title;
    private int price;
}