package azael.josue.recordatorio.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class note {
    // ID de la nota
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Resto de atributos de la nota
    private Long id;
    private String title;
    private String content;
    private LocalDateTime dateCreated;
    
    // Constructor con parámetros
    public note(Long id, String title, String content, LocalDateTime dateCreated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    // Constructor sin parámetros para Springboot
    public note() {
    }

    // Relación muchos a uno
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private user user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
