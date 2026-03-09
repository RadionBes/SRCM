package radion.ru.srcm.entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String key;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String begin;
    @Column(nullable = false)
    private String end;
}
