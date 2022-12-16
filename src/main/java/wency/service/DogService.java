package wency.service;

import wency.pojo.Dog;

import java.util.List;

/**
 * Description:
 *
 * @Author: qianwenqian
 * @Date: 2022/12/16 10:04
 */
public interface DogService {

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteDog(String id);

    /**
     * 插入
     * @param dog
     * @return
     */
    Dog saveDog(Dog dog);

    /**
     * 批量插入
     * @param dogsList
     * @return
     */
    Boolean saveDogAll(List<Dog> dogsList);

    /**
     * 查询所有小狗
     * @return
     */
    List<Dog> findAllDog();

    /**
     * 查询一只小狗
     * @param id
     * @return
     */
    Dog findOneDog(String id);
}
