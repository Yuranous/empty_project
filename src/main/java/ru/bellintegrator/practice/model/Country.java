package ru.bellintegrator.practice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Страна
 */
@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Код
     */
    @Column(name = "citizenship_code")
    private Integer citizenshipCode;

    /**
     * Название
     */
    @Column(name = "name", length = 25)
    private String name;

    /**
     * Конструктор для hibernate
     */
    public Country() {

    }
}
