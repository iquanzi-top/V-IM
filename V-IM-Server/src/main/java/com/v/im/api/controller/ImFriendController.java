package com.v.im.api.controller;

import com.v.im.user.entity.ImUser;
import com.v.im.user.service.IImUserFriendService;
import com.v.im.user.service.IImUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 好友控制器<br/>
 * -----------------------------------------------------------------------------
 * @author Mr.Z
 * @version 1.0
 * @createDate 2020-07-05
 * @since JDK 1.8
 */
@Slf4j
@RestController
@RequestMapping("/api/friend")
public class ImFriendController {

    @Resource
    @Qualifier(value = "imUserService")
    private IImUserService imUserService;

    @Resource
    @Qualifier(value = "imUserFriendService")
    private IImUserFriendService imUserFriendService;

    @RequestMapping("/add")
    public Boolean addFriend(String userId) {
        //获取好友信息
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        ImUser user = imUserService.getByLoginName(username);

        ImUser friend = imUserService.getById(userId);
        Assert.notNull(friend, "抱歉，该用户不存在");


        log.debug("用户[{}]添加用户[{}]为好友", user.getName(), friend.getName());
        boolean addResult = imUserFriendService.addFriend(user, friend);
        Assert.isTrue(addResult, "抱歉，添加好友失败");

        return addResult;
    }
}
