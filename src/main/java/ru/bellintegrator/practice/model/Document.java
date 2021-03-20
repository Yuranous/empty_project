package ru.bellintegrator.practice.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Document")
public class Document {

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
     * Номер
     */
    @Column(name = "number")
    private String number;

    /**
     * Дата выдачи
     */
    @Column(name = "date")
    private LocalDate date;

    /**
     * Тип
     */
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "id")
    @Column(name = "doc_type_id")
    private DocumentType type;

    /**
     * Конструктор для hibernate
     */
    public Document() {

    }
}
