package com.endereco.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6247426484320048189L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String senha;

    @NotBlank
    private String email;
}