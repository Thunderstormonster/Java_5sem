package bstu.kachanova.javaproject.demo.repository;

import bstu.kachanova.javaproject.demo.exception.RepositoryException;
import bstu.kachanova.javaproject.demo.models.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Scooter, Long> {
    @Modifying
    void deleteById(int id)throws RepositoryException;

    @Modifying
    void deleteByName(String name)throws RepositoryException;

    Scooter getById(Long id);

    Scooter getByName(String name)throws RepositoryException;

    boolean existsByName(String name)throws RepositoryException;

    //изменла имя
    @Modifying
    @Query("update Scooter c set c.name=:name, c.description=:description, c.cost=:cost where c.id=:id")
    void updateBookById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("cost") int cost
    )throws RepositoryException;
}
