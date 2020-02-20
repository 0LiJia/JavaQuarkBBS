package com.quark.chat.entity;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import com.quark.common.entity.User;
import io.netty.channel.Channel;
import lombok.Data;

@Data
public class ChatUser {

    private User user;

    private boolean isAuth = false;//是否认证

    private long time = 0;//活跃时间

    private Channel channel;//用户对应的channel

    private String addr;            // 地址

}
