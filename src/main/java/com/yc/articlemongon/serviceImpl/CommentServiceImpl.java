package com.yc.articlemongon.serviceImpl;

import com.yc.articlemongon.dao.CommentDao;
import com.yc.articlemongon.entity.Comment;
import com.yc.articlemongon.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    private MongoTemplate mongoTemplate;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, MongoTemplate mongoTemplate) {
        this.commentDao = commentDao;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void deleteCommentById(String id) {
        commentDao.deleteById(id);
    }

    @Override
    public void deleteAllComment() {
        commentDao.deleteAll();
    }

    @Override
    public List<Comment> findCommentList() {
        return commentDao.findAll();
    }

    @Override
    public Comment findCommentById(String id) {
        return commentDao.findById(id).get();
    }

    @Override
    public void updateCommentLikeNum(String id) {
        //查询对象
        Query query = Query.query(Criteria.where("_id").is(id));

        //更新对象
        Update update = new Update();
        //递增$inc
        update.inc("likeNum");
        mongoTemplate.updateFirst(query,update,"comment");
    }

    @Override
    public void updateCommentDynamic(Comment comment) {
        //查询对象
        Query query = Query.query(Criteria.where("_id").is(comment.getId()));

        //更新对象
        Update update = new Update();
        if(comment.getContent() != null && !"".equals(comment.getContent())){
            update.set("content",comment.getContent());
        }
        if(comment.getUserId() != null && !"".equals(comment.getUserId())){
            update.set("userId",comment.getUserId());
        }
        if(comment.getNickName() != null && !"".equals(comment.getNickName())){
            update.set("nickName",comment.getNickName());
        }
        if(comment.getCreateTime() != null ){
            update.set("createTime",comment.getCreateTime());
        }
        if(comment.getLikeNum() != null ){
            update.set("likeNum",comment.getLikeNum());
        }
        if(comment.getReplyNum() != null ){
            update.set("replyNum",comment.getReplyNum());
        }
        if(comment.getState() != null ){
            update.set("state",comment.getState());
        }
        if(comment.getArticleId() != null && !"".equals(comment.getArticleId())){
            update.set("articleId",comment.getArticleId());
        }
        mongoTemplate.updateFirst(query,update,"comment");
    }
}
