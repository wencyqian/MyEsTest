package wency.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description:
 *          实体类
 * @Author: qianwenqian
 * @Date: 2022/12/16 9:47
 */
@Data
@Document(indexName = "pets",type = "dog")
public class Dog {
    private String id;
    private String name;
    private Integer age;
    private String color;
}
