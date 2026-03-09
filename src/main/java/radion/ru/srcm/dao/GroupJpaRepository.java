package radion.ru.srcm.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import radion.ru.srcm.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupJpaRepository extends JpaRepository<Group, Long> {
    boolean existsGroupByKey(String key);

    @EntityGraph(attributePaths = {"studentList", "files", "timetables", "subjects"})
    Optional<Group> findGroupByKey(String key);

    @NotNull
    @Override
    @EntityGraph(attributePaths = {"studentList", "files", "timetables", "subjects"})
    List<Group> findAll();
}
