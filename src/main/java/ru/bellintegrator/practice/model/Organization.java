package ru.bellintegrator.practice.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Краткое наименование
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Полное наименование
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * ИНН
     */
    @Column(name = "inn", nullable = false)
    private String inn;

    /**
     * КПП
     */
    @Column(name = "kpp", nullable = false)
    private String kpp;

    /**
     * Адрес
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Номер телефона
     */
    @Column(name = "phone", columnDefinition = "char(10)")
    private String phone;

    /**
     * Статус
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Офисы
     */
    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "offices"
    )
    private Set<Office> offices;

    /**
     * Конструктор для hibernate
     */
    public Organization() {

    }
}
