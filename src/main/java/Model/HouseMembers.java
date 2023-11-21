package Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class HouseMembers {
    @Id
    @ManyToOne
    @JoinColumn(name = "house_id")
    private House id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_owner")
    private boolean isOwner;
}
