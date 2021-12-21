package bstu.kachanova.javaproject.demo.repository;



import bstu.kachanova.javaproject.demo.models.Role;
import bstu.kachanova.javaproject.demo.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(Role name);
}
