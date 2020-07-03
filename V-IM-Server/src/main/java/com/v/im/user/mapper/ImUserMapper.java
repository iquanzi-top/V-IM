package com.v.im.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.v.im.user.entity.ImChatGroup;
import com.v.im.user.entity.ImGroup;
import com.v.im.user.entity.ImUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 乐天
 * @since 2018-10-07
 */
@Component
@Qualifier("imUserMapper")
public interface ImUserMapper extends BaseMapper<ImUser> {

    /**
     * 根据用户id 获取好友的分组
     * @param userId id
     * @return List<ImGroup>
     */
    List<ImGroup> getGroupUsers(String userId);

    /**
     * 根据用户id 获取群组
     * @param userId id
     * @return List<ImGroup>
     */
    List<ImChatGroup> getUserGroups(String userId);


    /**
     * 获取群组的用户
     * @param chatId 群组id
     * @return 用户List
     */
    List<ImUser> getChatUserList(String chatId);

    /**
     * 根据关键词查找用户。<br/>
     * <hr/>
     * <b>取合集：</b>
     * <ol>
     *     <li>模糊匹配：name</li>
     *     <li>精确匹配：mobile、login_name</li>
     * </ol>
     * @param key 搜索关键词
     * @author Mr.Z
     * @date 2020-07-03 12:00:05
     * @return 用户集合
     */
    List<ImUser> findByKey(String key);
}
