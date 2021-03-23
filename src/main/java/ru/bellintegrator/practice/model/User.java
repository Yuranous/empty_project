package ru.bellintegrator.practice.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;

/**
 * Пользователь
 */
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
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
     * Иия
     */
    @Column(name = "first_name", length = 25, nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "second_name", length = 25)
    private String secondName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 25)
    private String middleName;

    /**
     * Должность
     */
    @Column(name = "position", length = 25, nullable = false)
    private String position;

    /**
     * Номер телефона
     */
    @Column(name = "phone", columnDefinition = "char(10)")
    private String phone;

    /**
     * Статус
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Офис
     */
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     * Документ
     */
    @OneToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "doc_id")
    private Document document;

    /**
     * Страна
     */
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "country_id")
    private Country country;

    /**
     * Конструктор для hibernate
     */
    public User() {

    }
}
