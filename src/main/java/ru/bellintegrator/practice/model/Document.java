package ru.bellintegrator.practice.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Document")
public class Document implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Номер
     */
    @Column(name = "number", length = 25)
    private String number;

    /**
     * Дата выдачи
     */
    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    /**
     * Тип
     */
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "doc_type_id")
    private DocumentType type;
}
