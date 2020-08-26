package com.yc.articlemongon.service;

import com.yc.articlemongon.entity.Comment;

import java.util.List;

/**
 * @author Administrator
 */
public interface CommentService {

    /**
     * 保存一个评论
     * @param comment 评论实体
     */
    void saveComment(Comment comment);

    /**
     * 更新评论
     * @param comment 评论实体
     */
    void updateComment(Comment comment);

    /**
     * 根据id删除评论
     * @param id id
     */
    void deleteCommentById(String id);

    /**
     * 根据id删除评论
     * @param id id
     */
    void deleteAllComment();

    /**
     * 查询所有评论
     * @return List
     */
    List<Comment> findCommentList();

    /**
     * 根据id查询评论
     * @param id id
     * @return Comment
     */
    Comment findCommentById(String id);

    /**
     * 点赞数加一
     * @param id id
     */
    void updateCommentLikeNum(String id);

    /**
     * 动态更新评论
     * @param comment comment
     */
    void updateCommentDynamic(Comment comment);
}
