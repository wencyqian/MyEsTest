package wency.controller;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wency.elstic.DogRepository;
import wency.pojo.Dog;
import wency.service.DogService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Description:
 *      写个简单的接口验证一下这些常规crud
 * @Author: qianwenqian
 * @Date: 2022/12/16 10:42
 */
@RestController
public class DogController {

    @Autowired
    DogService dogService;

    @Autowired
    DogRepository repository;

    //精确查询
    @GetMapping("queryTest")
    public void queryTest(){
        QueryBuilder qb0 = QueryBuilders.termQuery("name" + ".keyword", "Alaska-km6u");
        QueryBuilder qb = QueryBuilders.boolQuery().must(qb0);

        List<Dog> list = new ArrayList<>();
        Iterable<Dog> aIterable = repository.search(qb);
        for (Dog dog : aIterable) {
            list.add(dog);
        }
        System.out.println(list.toString());
    }

    public static String getNonceStr(int length){
        //生成随机字符串
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

        Random random = new Random();

        StringBuilder randomStr = new StringBuilder();

        //设置生成字符串的长度，用于循环
        for (int i = 0; i < length; i++) {
            //从62个的数字或字母中选择
            int number = random.nextInt(62);

            //将产生的数字通过length次承载到sb中
            randomStr.append(str.charAt(number));
        }
        return randomStr.toString();
    }

    @GetMapping("addDogTest")
    public void addTest(){
        //插入一只小狗
       Dog dog = new Dog();
       dog.setId("100111");
       dog.setName("TestDog");
       dog.setAge(3);
       dog.setColor("black");
       dogService.saveDog(dog);
    }

    @GetMapping("addListTest")
    public String addTest2(){
        //插入所有小狗
        List<Dog> dogList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Dog dog = new Dog();
            dog.setId(UUID.randomUUID().toString().replace("-",""));
            dog.setName("Alaska-"+getNonceStr(4));
            dog.setAge(new Random().nextInt(100));
            dog.setColor("red");
            dogList.add(dog);
        }
        dogService.saveDogAll(dogList);
        return "success";
    }

    @GetMapping("deleteOne")
    public String deleteOne(){
        dogService.deleteDog("d71f9acda6c94a1fa618e2431f660c48");
        return "success";
    }

    @GetMapping("findTest")
    public void find(){
        //根据Id，查找一只小狗
        Dog oneDog = dogService.findOneDog("6a10f03f311a48edace3076b321e677e");
        System.out.println(oneDog.toString());
    }

    @GetMapping("findAll")
    public void find2() {
        //查找所有小狗
        List<Dog> allDog = dogService.findAllDog();
        System.out.println(allDog.toString());
    }

    @GetMapping("updateDogTest")
    public void updateDogTest() {
        //插入一只小狗
        Dog dog = new Dog();
        dog.setId("100111");
        dog.setName("update");
        dog.setAge(2);
        dog.setColor("black");
        dogService.saveDog(dog);
    }
}
