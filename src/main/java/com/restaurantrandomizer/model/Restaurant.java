package com.restaurantrandomizer.model;


import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "restaurants", schema = "public")
@Data
public class Restaurant {
    @Id
    @SequenceGenerator(name="restaurants_id_seq",
            sequenceName="restaurants_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="restaurants_id_seq")
    @Column(name = "id", updatable=false)
    private Long id;

    private String name;

}
