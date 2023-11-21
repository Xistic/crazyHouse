package Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adress;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
