package Service;

import Model.House;
import Model.HouseMembers;
import Model.User;
import Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseMemberService {
    private final HouseService houseService;
    private final UserService userService;

    private final MemberRepository memberRepository;

    @Autowired
    public HouseMemberService(HouseService houseService, UserService userService, MemberRepository memberRepository){
        this.houseService = houseService;
        this.userService = userService;
        this.memberRepository = memberRepository;
    }

    public void addMemberInHouse(Long houseId, Long userId, boolean isOwner){
        House house = houseService.getHouseById(houseId);
        User user = userService.getUser(userId);

        if(memberRepository.existsByUserAndHouse(house,user)) throw new IllegalArgumentException("Пользователь уже живёт в этом доме");
        if(isOwner && memberRepository.existsByHouseAndIsOwner(house, true)) throw new IllegalArgumentException("У этого дома уже есть владелец");
        memberRepository.addHouseMember(house, user, isOwner);

        HouseMembers houseMembers = new HouseMembers();
        houseMembers.setHouse(house);
        houseMembers.setUser(user);
        houseMembers.set_owner(isOwner);
        memberRepository.save(houseMembers);
    }

    public void deleteMemberHouse(Long houseId, Long userId){
        User user = userService.getUser(userId);
        House house = houseService.getHouseById(houseId);

        if (!memberRepository.existsByHouseAndUser(house, user)) {
            throw new IllegalArgumentException("Пользователь не является членом этого дома");
        }
        memberRepository.deleteByHouseAndUser(house, user);
    }

    public List<User> getAllMembers(Long houseId) {
        // Предполагаем, что у вас есть метод в HouseMembersRepository для получения всех членов дома
        List<HouseMembers> houseMembersList = memberRepository.findAllByHouseId(houseId);

        // Создаем список пользователей из членов дома
        List<User> userList = houseMembersList.stream()
                .map(HouseMembers::getUser)
                .collect(Collectors.toList());

        return userList;
    }



}
