package wency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import wency.elstic.DogRepository;
import wency.pojo.Dog;
import wency.service.DogService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 *
 * @Author: qianwenqian
 * @Date: 2022/12/16 10:05
 */
@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogRepository dogRepository;

    @Override
    public boolean deleteDog(String id) {
        try {
            dogRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Dog saveDog(Dog dog) {
        try {
            Dog save = dogRepository.save(dog);
            System.out.println("结果："+save.toString());
            return save;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean saveDogAll(List<Dog> dogsList) {
        try {
            dogRepository.saveAll(dogsList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Dog> findAllDog() {
        List<Dog> dogs = new ArrayList<>();
        try {
            Iterable<Dog> all = dogRepository.findAll();
            for(Dog d:all){
                dogs.add(d);
            }
            ;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return dogs;
    }

    @Override
    public Dog findOneDog(String id) {
        Optional<Dog> byId = dogRepository.findById(id);
        return dogRepository.findById(id).get();
    }
}
