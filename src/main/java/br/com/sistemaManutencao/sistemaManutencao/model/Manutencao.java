package br.com.sistemaManutencao.sistemaManutencao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100)
    @NotBlank
    @Column(length = 100, nullable = false)
    private String item;

    @Size(min = 3, max = 400)
    @NotBlank
    @Column(length = 400, nullable = false)
    private String description;


    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime finisheadAt;

    @Column(length = 50, nullable = false)
    @NotBlank
    private String status; 

    @Column(length = 50, nullable = false)
    @NotBlank
    private String setor; 

    @Column(length = 20, nullable = false)
    @NotBlank
    private String tipoProblema; 

    @Column(length = 100, nullable = false)
    @NotBlank
    private String autor; 
}