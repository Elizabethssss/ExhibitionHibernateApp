package com.elizabeth.exhibition.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "exhibition_id", nullable = false)
    private ExhibitionEntity exhibitionEntity;

    @Column(name = "is_bought", nullable = false)
    private boolean isBought;

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", userEntity=" + userEntity.getId() +
                ", exhibitionEntity=" + exhibitionEntity.getId() +
                ", isBought=" + isBought +
                '}';
    }
}
