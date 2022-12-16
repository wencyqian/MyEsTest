package wency.elstic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import wency.pojo.Blog;

/**
 * Description:
 *    假如我们不知道主键id，我们只知道一些条件，例如想查询 点赞数  thumbNum =1000的 数据
 * @Author: qianwenqian
 * @Date: 2022/12/15 15:26
 */
@Repository
public interface ElasticsearchOptionSearchRepository extends ElasticsearchRepository<Blog,String> {
}
