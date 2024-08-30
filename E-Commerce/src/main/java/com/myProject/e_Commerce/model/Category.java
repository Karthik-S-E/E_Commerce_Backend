package com.myProject.e_Commerce.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Repository;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long categoryId;
 @NotBlank
  private String categoryName;

}
