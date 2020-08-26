package com.yc.articlemongon.dao;

import com.yc.articlemongon.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Administrator
 */
public interface CommentDao extends MongoRepository<Comment,String> {

}
