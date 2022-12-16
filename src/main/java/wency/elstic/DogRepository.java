package wency.elstic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import wency.pojo.Dog;

/**
 * Description:
 *      对接数据层，也就是该篇的使用核心ElasticsearchRepository
 * @Author: qianwenqian
 * @Date: 2022/12/16 10:03
 */
@Repository
public interface DogRepository extends ElasticsearchRepository<Dog,String> {
}
