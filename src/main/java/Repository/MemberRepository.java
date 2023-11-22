package Repository;

import Model.House;
import Model.HouseMembers;
import Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<HouseMembers, Long> {

    boolean existsByUserAndHouse(House house, User user);

    boolean existsByHouseAndIsOwner(House house, boolean b);

    boolean existsByUser(Long id);

    void addHouseMember(House house, User user, boolean isOwner);

    boolean existsByHouseAndUser(House house, User user);

    void deleteByHouseAndUser(House house, User user);

    List<HouseMembers> findAllByHouseId(Long houseId);
}
