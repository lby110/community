package com.community.community.controller;

import com.community.community.dto.ReCommentDTO;
import com.community.community.dto.ResultDTO;
import com.community.community.dto.CommentDTO;
import com.community.community.exception.CustomizeErrorCode;
import com.community.community.model.Comment;
import com.community.community.model.User;
import com.community.community.service.ICommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {
    @Autowired
    private ICommentService iCommentService;

    /**
     * 新增回复
     * @param commentDTO
     * @param request
     * @return
     */
    @PostMapping("/post")
    public Object post(@RequestBody CommentDTO commentDTO,
                    HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        if (commentDTO==null|| StringUtils.isBlank(commentDTO.getContext())){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FIND_CONTEXT);
        }
        Comment comment=new Comment();
        //问题id
        comment.setParentId(commentDTO.getParentId());
        //评论内容
        comment.setContext(commentDTO.getContext());
        //新增时间
        comment.setGmtCreate(System.currentTimeMillis());
        //修改时间
        comment.setGmtModified(System.currentTimeMillis());
        comment.setType(commentDTO.getType());
        //评论人id
        comment.setUserId(user.getId());
        comment.setLikeCount(0L);
        iCommentService.insertComment(comment);
        return ResultDTO.okOf();
    }

}
