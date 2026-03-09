package radion.ru.srcm.entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true, nullable = false)
    private String key;
    @Column(nullable = false)
    private String teacher;
}