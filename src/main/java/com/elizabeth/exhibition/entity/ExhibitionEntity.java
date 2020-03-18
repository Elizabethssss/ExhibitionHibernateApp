package com.elizabeth.exhibition.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "exhibition")
public class ExhibitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Column(name = "about", nullable = false)
    private String about;

    @Column(name = "long_about", nullable = false)
    private String longAbout;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image", nullable = false)
    private String image;

    @OneToMany(mappedBy = "exhibitionEntity")
    private List<ExpositionEntity> expositionEntityList = new ArrayList<>();

    @Override
    public String toString() {
        return "ExhibitionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", theme='" + theme + '\'' +
                ", about='" + about + '\'' +
                ", longAbout='" + longAbout + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
