package radion.ru.srcm.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.OrderedHashSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(length = 1500)
    private String description;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    @Builder.Default
    private Set<Subject> subjects = new OrderedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    @Builder.Default
    private Set<File> files = new OrderedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    @Builder.Default
    private Set<Student> studentList = new OrderedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    @Builder.Default
    private Set<TimetableTeacher> timetables = new OrderedHashSet<>();
}
