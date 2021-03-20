package ru.bellintegrator.practice.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Office")
public class Office {

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
     * Название
     */
    @Column(name = "name", length = 25)
    private String name;

    /**
     * Номер телефона
     */
    @Column(name = "phone", columnDefinition = "char(10)")
    private String phone;

    /**
     * Адрес
     */
    @Column(name = "address", length = 50)
    private String address;

    /**
     * Статус
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Организация-владелец
     */
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "id")
    @Column(name = "org_id", nullable = false)
    private Organization organization;

    /**
     * Работники
     */
    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "users"
    )
    private Set<User> users;

    /**
     * Конструктор для hibernate
     */
    public Office() {
    }
}
