package com.whw.dao;

import com.whw.model.*;
import com.whw.util.Page;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface CommentDao extends BaseDao<Comment> {

   /**
    * 通过集市编号查询评论
    * @param jsbh
    * @return
    */
   List<Comment> findAllCommentByJsbh(String jsbh);

   List<Comment> findCommentByJsbh(String jsbh, Page page);

   List<Comment> findAllCommentByWpbh(String wpbh);

   /**
    * 通过物品变编号查询评论
    * @param wpbh
    * @param page
    * @return
    */
   List<Comment> findCommentByWpbh(String wpbh, Page page);

   /**
    * 插入一条评论
    * @param comment
    */
   void insertComment(Comment comment);

   /**
    * 检查评论是否存在
    * @param comment
    * @return
    */
   boolean checkComment(Comment comment);

   /**
    * 删除一条评论
    * @param comment
    * @return
    */
   boolean removeComment(Comment comment);

}
