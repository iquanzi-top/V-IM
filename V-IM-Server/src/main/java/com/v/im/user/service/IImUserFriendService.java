package com.v.im.user.service;

import com.v.im.user.entity.ImGroup;
import com.v.im.user.entity.ImUser;
import com.v.im.user.entity.ImUserFriend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-12-31
 */
public interface IImUserFriendService extends IService<ImUserFriend> {

    /**
     * 根据用户的ID 获取 用户好友(双向用户关系)
     *
     * @param userId 用户ID
     * @return 好友分组的列表
     */
    List<ImGroup> getUserFriends(String userId);

    /**
     * 添加用户user的好友friend记录
     * @param user 用户对象
     * @param friend 好友对象
     * @author Mr.Z
     * @date 2020-07-05 16:07:33
     * @return true：成功；false：失败
     */
    boolean addFriend(ImUser user, ImUser friend);
}
