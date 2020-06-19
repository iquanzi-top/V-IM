package com.v.im.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v.im.api.entity.Message;
import com.v.im.api.entity.SendInfo;
import com.v.im.chat.conf.StartTioRunner;
import com.v.im.chat.conf.TioServerConfig;
import com.v.im.chat.conf.WsOnlineContext;
import com.v.im.common.utils.ChatUtils;
import com.v.im.message.entity.ImMessage;
import com.v.im.message.service.IImMessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.server.ServerGroupContext;
import org.tio.websocket.common.WsResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zkp
 */
@RestController
@RequestMapping("/api/user")
public class ChatController {


    @Resource
    private StartTioRunner startTioRunner;


    @Resource
    @Qualifier(value = "iImMessageService")
    private IImMessageService iImMessageService;

    /**
     * 发送信息给用户
     * 注意：目前仅支持发送给在线用户
     *
     * @param userId 接收方id
     * @param msg    消息内容
     */
    @PostMapping("sendMsg")
    public void sendMsg(String userId, String msg, HttpServletRequest request) throws Exception {
        String host = ChatUtils.getHost(request);
        ServerGroupContext serverGroupContext = startTioRunner.getAppStarter().getWsServerStarter().getServerGroupContext();

        SendInfo sendInfo = new SendInfo();
        sendInfo.setCode(ChatUtils.MSG_MESSAGE);
        Message message = new Message();
        message.setId("system");
        message.setFromid("system");
        message.setContent(msg);
        message.setMine(false);
        message.setTimestamp(System.currentTimeMillis());
        message.setType(ChatUtils.FRIEND);
        message.setAvatar(host + "/static/img/icon.png");
        message.setUsername("系统消息");
        sendInfo.setMessage(message);

        ChannelContext cc = WsOnlineContext.getChannelContextByUser(userId);
        if (cc != null && !cc.isClosed) {
            WsResponse wsResponse = WsResponse.fromText(new ObjectMapper().writeValueAsString(sendInfo), TioServerConfig.CHARSET);
            Tio.sendToUser(serverGroupContext, userId, wsResponse);
        } else {
            saveMessage(message, ChatUtils.UNREAD, userId);
        }
    }


    private void saveMessage(Message message, String readStatus, String userId) {
        ImMessage imMessage = new ImMessage();
        imMessage.setToId(userId);
        imMessage.setFromId(message.getFromid());
        imMessage.setSendTime(System.currentTimeMillis());
        imMessage.setContent(message.getContent());
        imMessage.setReadStatus(readStatus);
        imMessage.setType(message.getType());
        iImMessageService.saveMessage(imMessage);
    }
}
