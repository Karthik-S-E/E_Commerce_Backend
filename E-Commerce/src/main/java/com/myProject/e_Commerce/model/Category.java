package com.myProject.e_Commerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long categoryId;
 @NotBlank
  private String categoryName;

 @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL)
 @JsonIgnore
 private List<Product> products;

}
