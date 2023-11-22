package Service;

import Model.House;
import Model.HouseMembers;
import Model.User;
import Repository.HouseRepository;
import Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    @Autowired
    public HouseService(HouseRepository houseRepository, UserService userService){
        this.houseRepository = houseRepository;
    }

    public House addHouse(House house){
        return houseRepository.save(house);
    }

    public void deleteHouse(Long id){
        if(houseRepository.existsById(id)) houseRepository.deleteById(id);
        else throw new EntityNotFoundException("Дома с таким id нет" + id);
    }

    public House getHouseByAdress(String address){
        return houseRepository.findByAddress(address).orElseThrow(() -> new EntityNotFoundException("Дом не найден по адресу: " + address));
    }
    public House getHouseById(Long houseId){
        return houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException("Дом не найден по id: " + houseId));
    }

    public List<House> getAllHouse() {
        List<House> houses = houseRepository.findAll();
        if (houses.isEmpty()) {
            throw new EntityNotFoundException("Дома не найдены");
        }
        return houses;
    }

}
