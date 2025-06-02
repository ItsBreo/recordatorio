package azael.josue.recordatorio.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    // FetchType.LAZY para cargar el usuario solo cuando sea necesario
    @ManyToOne(fetch = FetchType.LAZY)
    // Unión con la entidad 'user' que no sea nulo
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    // Evita que se serialice el usuario al convertir a JSON
    private user user;


    // Getters y Setters

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

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }
}
