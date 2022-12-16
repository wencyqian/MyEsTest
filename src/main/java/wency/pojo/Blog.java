package wency.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description:
 *
 * @Author: qianwenqian
 * @Date: 2022/12/15 13:55
 */
@Data
// indexName 相当于数据库的名字；
// type 相当于表的名字
@Document(indexName = "testdata",type = "blogs")
public class Blog {
    private Long id;
    private String masterName;
    private Integer articleNum;
    private Integer commentNum;
    private Integer thumbNum;
    private String description;
}
