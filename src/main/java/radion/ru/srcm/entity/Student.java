package radion.ru.srcm.entity;

import jakarta.persistence.*;
import lombok.*;
import radion.ru.srcm.util.Interest;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;
    private String city;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Interest interest;

    @Column(length = 1500)
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    @Builder.Default
    private List<File> files = new ArrayList<>();
}
