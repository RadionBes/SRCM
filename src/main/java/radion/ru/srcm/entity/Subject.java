package radion.ru.srcm.entity;


import jakarta.persistence.*;
import lombok.*;


@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String hours;
    private String first;

    @ManyToOne
    private Group group;
}
