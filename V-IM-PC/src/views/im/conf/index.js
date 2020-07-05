export default {
  app_name: "JML-IM",
  http_protocol: "http",
  http_port: 8080,
  ws_port: 9326,
  init: "/api/user/init",
  his_url: "/api/message/list",
  chat_users_url: "/api/user/chatUserList",
  find_user: "/api/user/findUser",
  friend_add: "/api/friend/add",
  token_path: "/oauth/token",
  register_url: "/register",
  ws_protocol: "ws",
  getHostUrl: function() {
    return (
      this.http_protocol +
      "://" +
      localStorage.getItem("host") +
      ":" +
      this.http_port
    );
  },
  getTokenUrl: function() {
    return this.getHostUrl() + this.token_path;
  },
  getInitUrl: function() {
    return this.getHostUrl() + this.init;
  },
  getChatUsersUrl: function() {
    return this.getHostUrl() + this.chat_users_url;
  },
  getHisUrl: function() {
    return this.getHostUrl() + this.his_url;
  },
  getSearchUserUrl: function() {
    return this.getHostUrl() + this.find_user;
  },
  getAddFriendUrl: function() {
    return this.getHostUrl() + this.friend_add;
  },
  getRegisterUrl: function() {
    return this.getHostUrl() + this.register_url;
  },
  getWsUrl: function() {
    return (
      this.ws_protocol +
      "://" +
      localStorage.getItem("host") +
      ":" +
      this.ws_port
    );
  }
};
