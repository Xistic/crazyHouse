package Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class HouseMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_owner")
    private boolean is_owner;
}
