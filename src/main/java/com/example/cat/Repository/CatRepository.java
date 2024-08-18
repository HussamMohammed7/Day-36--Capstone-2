package com.example.cat.Repository;

import com.example.cat.Model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

    @Query("SELECT c FROM Cat c WHERE c.id = ?1")
    Cat findCatById(Integer id);

    @Query("SELECT c FROM Cat c WHERE c.adopted = true")
    List<Cat> findByAdoptedTrue();

    @Query("SELECT c FROM Cat c WHERE c.adopted = false")
    List<Cat> findByAdoptedFalse();

    @Query("SELECT c FROM Cat c WHERE c.age BETWEEN ?1 AND ?2")
    List<Cat> findByAgeBetween(Integer startAge, Integer endAge);

    @Query("SELECT c FROM Cat c WHERE c.breed = ?1")
    List<Cat> findByBreed(String breed);

    @Query("SELECT c FROM Cat c WHERE c.gender = ?1")
    List<Cat> findByGender(String gender);

    @Query("SELECT c FROM Cat c WHERE c.ownerId = ?1")
    List<Cat> findByOwnerId(Integer ownerId);
}
