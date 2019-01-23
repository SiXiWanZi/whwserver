package com.whw.dao.impl;
import com.whw.dao.CommentDao;
import com.whw.model.Comment;
import com.whw.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao{

   /**
    * 去除循环引用，导致的jsonStr转换不成功
    * @param list
    */
   private void setCommentParentNUll(List<Comment> list){
      if(list==null||list.size()==0)return;
      for (Comment comment:list){
         setCommentParentNUll(comment.getChildComments());
         if(comment.getComment()!=null)comment.setComment(null);
      }
   }
   @Override
     public List<Comment> findAllCommentByJsbh(String jsbh) {
      String hql = "from Comment c where c.jsbh =? and c.comment is null order by c.plsj";
      List<Comment> list =find(hql, new String[]{jsbh});
      setCommentParentNUll(list);
      return list;
   }

   @Override
   public List<Comment> findCommentByJsbh(String jsbh, Page page) {
      String hql = "from Comment c where c.jsbh =? and c.comment is null order by c.plsj";
      List<Comment> list = find(hql, page, new String[]{jsbh});
      setCommentParentNUll(list);
      return list;
   }

   @Override
   public List<Comment> findAllCommentByWpbh(String wpbh) {
      String hql = "from Comment c where c.wpbh =? and c.comment is null order by c.plsj";
      List<Comment> list = find(hql,new String[]{wpbh});
      setCommentParentNUll(list);
      return list;
   }

   @Override
   public List<Comment> findCommentByWpbh(String wpbh, Page page) {
      String hql = "from Comment c where c.wpbh =? and c.comment is null order by c.plsj";
      List<Comment> list = find(hql,page, new String[]{wpbh});
      setCommentParentNUll(list);
      return list;
   }

   @Override
   public void insertComment(Comment comment) {
      save(comment);
   }

   @Override
   public boolean checkComment(Comment comment) {
      String hql = "from Comment c where c.plyh.yhbh=? and (c.wpbh=? or c.jsbh=?) and c.plnr=?";
      List<Comment> list = find(hql,new String[]{comment.getPlyh().getYhbh(),comment.getWpbh(),comment.getJsbh(),comment.getPlnr()});
      if(list==null||list.size()==0)return true;
      return false;
   }

   @Override
   public boolean removeComment(Comment comment) {
      try{
         delete(comment);
         return true;
      }catch (Exception e) {
         return false;
      }
   }
}
