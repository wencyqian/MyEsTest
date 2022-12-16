package wency.controller;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wency.elstic.ElasticsearchOptionSearchRepository;
import wency.pojo.Blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description:
 *      写一些测试的接口
 * @Author: qianwenqian
 * @Date: 2022/12/15 14:12
 */
@RestController
public class DataTestController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ElasticsearchOptionSearchRepository elasticsearchOptionSearchRepository;

    @GetMapping("/addTest")
    public String testElSearch(){
        //该list用于添加需要存入的数据
        List<IndexQuery> indexQueryList = new ArrayList<>();

        //模拟一些数据
        Blog blog = new Blog();
        blog.setId((long)new Random().nextInt(1500));
        blog.setMasterName("wency");
        blog.setArticleNum(10);
        blog.setCommentNum(20);
        blog.setThumbNum(100);
        blog.setDescription("分享不仅为了别人，也为了自己");

        //把这个数据放入indexQueryList
        indexQueryList.add(new IndexQueryBuilder().withObject(blog).build());

        //模拟循环一些数据
        for (int i = 1; i <= 6; i++) {
            Blog blog2 = new Blog();
            blog2.setId((long)new Random().nextInt(1500));
            blog2.setMasterName("test");
            blog2.setArticleNum(i*60);
            blog2.setCommentNum(i*16);
            blog2.setThumbNum(i*500);
            blog2.setDescription("测试添加"+i);
            indexQueryList.add(new IndexQueryBuilder().withObject(blog2).build());
        }
        elasticsearchTemplate.bulkIndex(indexQueryList);
        return "add success";
    }

    @GetMapping("/getTestData")
    public List<Blog> getTestData(){

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("masterName", "wency"))
                .build();
        List<Blog> blogs = elasticsearchTemplate.queryForList(searchQuery, Blog.class);
        return blogs;
    }

    /**
     * 模糊查询
     * @return
     */
    @GetMapping("/addTest2")
    public String testElSearch2(){
        //该list用于添加需要存入的数据
        List<IndexQuery> indexQueryList = new ArrayList<>();

        //模拟循环一些数据
        for (int i = 1; i <= 3; i++) {
            Blog blog2 = new Blog();
            blog2.setId((long)new Random().nextInt(1500));
            blog2.setMasterName("Test"+i*2*3);
            blog2.setArticleNum(i*60);
            blog2.setCommentNum(i*16);
            blog2.setThumbNum(i*500);
            blog2.setDescription("测试添加"+i);
            indexQueryList.add(new IndexQueryBuilder().withObject(blog2).build());
        }
        elasticsearchTemplate.bulkIndex(indexQueryList);
        return "add success";
    }

    @GetMapping("/queryTestData")
    public List<Blog> queryTestData(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("masterName", ("*" + "Test" + "*").toLowerCase()))
                .build();
        List<Blog> blogs = elasticsearchTemplate.queryForList(searchQuery, Blog.class);
        return blogs;
    }

    //根据单条件查询
    @GetMapping("/queryTestDataCondition")
    public List<Blog> queryTestDataCondition() {
        List<Blog> list = new ArrayList<>();

        TermQueryBuilder thumbNum = new TermQueryBuilder("thumbNum", 1000);
        Iterable<Blog> search = elasticsearchOptionSearchRepository.search(thumbNum);
        search.forEach(e->list.add(e));

        return list;
    }
}
