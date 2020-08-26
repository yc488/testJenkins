package com.yc.articlemongon;

import com.yc.articlemongon.entity.Comment;
import com.yc.articlemongon.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class ArticlemongonApplicationTests {

    @Autowired
    private CommentService commentService;

    @Test
    void contextLoads() {
    }

    /**
     * 测试插入数据
     */
    @Test
    public void testSaveComment(){
        Comment comment = new Comment();
        comment.setId("2");
        comment.setArticleId("100000");
        comment.setContent("测试添加的数据1");
        comment.setCreateTime(new Date());
        comment.setUserId("1001");
        comment.setNickName("李四");
        comment.setState("1");
        comment.setLikeNum(0);
        comment.setReplyNum(0);

        commentService.saveComment(comment);
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        List<Comment> commentList = commentService.findCommentList();
        for (Comment comment : commentList){
            System.out.println(comment);
        }
    }

    /**
     * 测试通过id查询
     */
    @Test
    public void testFindById(){
        String id = "1";
        Comment commentById = commentService.findCommentById(id);
        System.out.println(commentById);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate(){
        String id = "5f1d51db9b172845f8a7c6be";
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent("测试更新");
        commentService.updateComment(comment);
    }

    /**
     * 测试点赞，不需要根据id查询数据之后再进行保存
     * 直接使用$inc,直接更新likeNum字段加一
     */
    @Test
    public void testUpdateLikeNum(){
        String id = "5f1d52ebcce7236a091eb288";
        commentService.updateCommentLikeNum(id);
    }

    /**
     * 测试动态更新
     */
    @Test
    public void testUpdateCommentDynamic(){
        String id = "5f1d52ebcce7236a091eb288";
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent("测试动态更新");
        commentService.updateCommentDynamic(comment);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDel(){
        String id = "5f2e5a2862457b25a58200c8";
        commentService.deleteCommentById(id);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelAll(){
        commentService.deleteAllComment();
    }

}
