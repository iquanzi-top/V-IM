package com.v.im.user.service.impl;

import com.v.im.user.entity.ImGroup;
import com.v.im.user.entity.ImUser;
import com.v.im.user.entity.ImUserFriend;
import com.v.im.user.mapper.ImUserFriendMapper;
import com.v.im.user.service.IImUserFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  用户关系表
 * </p>
 *
 * @author jobob
 * @since 2018-12-31
 */
@Service
@Qualifier(value = "imUserFriendService")
public class ImUserFriendServiceImpl extends ServiceImpl<ImUserFriendMapper, ImUserFriend> implements IImUserFriendService {


    /**
     * 根据用户的ID 获取 用户好友(双向用户关系)
     *
     * @param userId 用户ID
     * @return 好友分组的列表
     */
   @Override
   public List<ImGroup> getUserFriends(String userId){
       return this.baseMapper.getUserFriends(userId);
    }

    @Override
    public boolean addFriend(ImUser user, ImUser friend) {
       // 添加用户user的好友friend记录
        ImUserFriend imUserFriend = new ImUserFriend();
        imUserFriend.preInsert();
        imUserFriend.setUserId(user.getId());
        imUserFriend.setFriendId(friend.getId());
        // 自己的默认好友分组
        imUserFriend.setUserGroupId(user.getDefaultGroupId());

        // 好友的默认好友分组
        imUserFriend.setFriendGroupId(friend.getDefaultGroupId());
        return this.save(imUserFriend);
    }
}
