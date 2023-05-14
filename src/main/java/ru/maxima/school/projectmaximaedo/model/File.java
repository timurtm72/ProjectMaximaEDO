package ru.maxima.school.projectmaximaedo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "file")
public class File {
    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** Название файла */
    @Column(name = "name", nullable = false)
    private String name;
    /** Наименование хранилища */
    @Column(name = "name_of_storage", nullable = false)
    private String nameOfStorage;
    /** Размер */
    @Column(name = "size", nullable = false)
    private Long size;
    /** Тип файла */
    @Column(name = "mime_type", nullable = false)
    private String mimeType;
    /** Описание */
    @Column(name = "description", nullable = false)
    private String description;
    /** Ссылка на документ */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="document_id")
    private Document document;
    /** флаг удаления */
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;

    public File() {
    }

    public File(String name, String nameOfStorage, Long size, String mimeType, String description,
                Document document, boolean isRemoved) {
        this.name = name;
        this.nameOfStorage = nameOfStorage;
        this.size = size;
        this.mimeType = mimeType;
        this.description = description;
        this.document = document;
        this.isRemoved = isRemoved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfStorage() {
        return nameOfStorage;
    }

    public void setNameOfStorage(String nameOfStorage) {
        this.nameOfStorage = nameOfStorage;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

}
