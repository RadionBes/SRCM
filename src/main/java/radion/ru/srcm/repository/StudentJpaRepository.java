package radion.ru.srcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.entity.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    boolean existsStudentByGroupAndFullName(Group group, String fullName);
}
