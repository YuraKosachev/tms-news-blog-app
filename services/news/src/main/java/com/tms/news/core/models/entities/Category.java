package com.tms.news.core.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "categories")
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Article> articles;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Category category = (Category) o;
//
//        if (name != null && !name.equals(category.getName())) return false;
//        if (description != null && !description.equals(category.getDescription())) return false;
//        if (id != null && !id.equals(category.getId())) return false;
//
//
//        return name == null && category.name == name
//                && description == null && category.description == description
//                && id == null && category.id == id;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (name != null ? name.hashCode() : 0)
//                + (description != null ? description.hashCode() : 0)
//                + (id != null ? id.hashCode() : 0);
//
//        return 31 * result;
//    }
}
