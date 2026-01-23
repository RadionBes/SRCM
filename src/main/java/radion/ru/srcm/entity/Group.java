package radion.ru.srcm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "group_college")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String key;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String year;
    @Column(nullable = false)
    private String spec;
    @Column(nullable = false)
    private String hoz;

    private String curator;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private final List<Student> studentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private final List<Timetable> timetables = new ArrayList<>();
}
