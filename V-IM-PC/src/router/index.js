import Vue from "vue";
import Router from "vue-router";
import UserBox from "../views/im/layout/userBox.vue";
import ChatBox from "../views/im/layout/chatBox.vue";
import ChatGroupBox from "../views/im/layout/chatGroupBox.vue";
import Settings from "../views/im/layout/settings"

Vue.use(Router);
const VueRouterPush = Router.prototype.push;
Router.prototype.push = function push(to) {
  return VueRouterPush.call(this, to).catch(err => err)
};

export default new Router({
  routes: [
    {
      path: "/index",
      name: "landing-page",
      component: require("@/views/index.vue").default,
      children: [
        {
          path: "chatBox",
          name: "chatBox",
          component: ChatBox
        },
        {
          path: "/",
          name: "userIndex",
          component: UserBox
        },
        {
          path: "userBox",
          name: "userBox",
          component: UserBox
        },
        {
          path: "chatGroupBox",
          name: "chatGroupBox",
          component: ChatGroupBox
        },
        {
          path: "settings",
          name: "settings",
          component: Settings
        }
      ]
    },
    {
      path: "/",
      name: "login-page",
      component: require("@/views/login.vue").default
    }
  ]
});
