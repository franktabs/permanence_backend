package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.demo.entities.Direction}
 */
public class DirectionDto implements Serializable {
    @NotNull(message = "Le nom est obligatoire")
    @Size(max = 255)
    @NotEmpty(message = "Le nom est obligatoire")
    @NotBlank(message = "Le nom est obligatoire")
    private final String name;

    public DirectionDto(@NotNull(message = "Le nom est obligatoire") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectionDto entity = (DirectionDto) o;
        return Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ")";
    }
}