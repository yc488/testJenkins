package com.yc.articlemongon.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章评论实体类
 * Document(collection = "comment")
 * 可以省略，如果省略，则默认使用类名小写映射集合
 * @author Administrator
 */
@Data
@Document(collection = "comment")
public class Comment implements Serializable {



    /**
     * 主键标识，该属性的值会自动对应mongodb的主键字段"_id"，
     * 如果该属性名就叫“id”,则该注解可以省略，否则必须写
     */
    @Id
    private String id;

    /**
     * 该属性对应mongodb的字段的名字，如果一致，则无需该注解
     */
    @Field("content")
    private String content;

    @Indexed
    private String userId;

    private String nickName;

    private Date createTime;

    private Integer likeNum;

    private Integer replyNum;

    private String state;

    private String parentId;

    private String articleId;
}
