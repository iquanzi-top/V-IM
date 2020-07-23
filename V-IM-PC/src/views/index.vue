<template>
  <div class="v-im">
    <div class="left-bar" style="-webkit-app-region: drag">
      <ul>
        <li class="userPhoto" @click="modal = true">
          <img :src="host + user.avatar" />
        </li>
        <li title="消息">
          <router-link v-bind:to="'/index/chatBox'">
            <Icon type="ios-text-outline" />
          </router-link>
        </li>
        <li title="好友">
          <router-link v-bind:to="'/index/userBox'">
            <Icon type="ios-contact-outline" />
          </router-link>
        </li>
        <li title="群聊">
          <router-link v-bind:to="'/index/chatGroupBox'">
            <Icon type="ios-contacts-outline" />
          </router-link>
        </li>
        <li title="设置">
          <router-link v-bind:to="'/index/settings'">
            <Icon type="ios-settings-outline" />
          </router-link>
        </li>
      </ul>
    </div>
    <keep-alive>
      <router-view class="content" />
    </keep-alive>
    <Modal closable class="user-model" v-model="modal" footer-hide :title="user.name" width="300">
      <Row gutter="2">
        <Col span="6">
          <img :src="host + user.avatar" class="img" />
        </Col>
        <Col span="16">
          <Row gutter="2">
            <Col class-name="col-weight" span="7">姓名：</Col>
            <Col span="15">{{ user.name }}</Col>
          </Row>
          <Row gutter="2" class-name="row-top">
            <Col class-name="col-weight" span="7">帐号：</Col>
            <Col span="15">{{ user.loginName }}</Col>
          </Row>
        </Col>
      </Row>
      <Divider />
      <Row gutter="2" class-name="row-top row-bottom">
        <Col class-name="col-weight" span="5">手机：</Col>
        <Col span="17">{{ user.mobile }}</Col>
      </Row>
      <Row gutter="2" class-name="row-top row-bottom">
        <Col class-name="col-weight" span="5">邮箱：</Col>
        <Col span="17">{{ user.email }}</Col>
      </Row>
      <!--<p class="user-model-img">
        <img :src="host + user.avatar" class="img" />
      </p>
      <p class="user-model-item">
        <label>姓名：</label>
        <span>{{ user.name }}</span>
      </p>
      <p class="user-model-item">
        <label>手机：</label>
        <span>{{ user.mobile }}</span>
      </p>
      <p class="user-model-item">
        <label>邮箱：</label>
        <span>{{ user.email }}</span>
      </p>-->
      <p>
        <Button type="error" long @click="myLogout">退出</Button>
      </p>
    </Modal>
  </div>
</template>
<script>
import conf from "../views/im/conf";
import { logout } from "../utils/ChatUtils";

export default {
  data() {
    return {
      host: conf.getHostUrl(),
      user: {},
      modal: false
    };
  },
  methods: {
    myLogout() {
      let self = this;
      logout(self);
    }
  },
  created: function() {
    this.user = this.$store.state.user;
  },
  mounted: function() {}
};
</script>
<style lang="scss">
@import "@/static/styles/theme.scss";
@import "@/static/styles/v-im.scss";

.v-im {
  display: flex;
  flex-direction: row;
  .left-bar {
    background-color: #1c2438;
    width: 6rem;
    height: 100%;
    ul {
      margin: 3rem 1.2rem 1.2rem 1.2rem;
      list-style: none;
      li {
        -webkit-app-region: no-drag;
        display: block;
        width: 3.6rem;
        height: 3.6rem;
        text-align: center;
        margin-bottom: 2rem;
        cursor: pointer;
        .ivu-icon {
          font-size: 3rem !important;
          color: $color-default;
          margin: 0.3rem;
          cursor: pointer;
          &:hover {
            color: $color-write;
          }
        }
        .router-link-active {
          .ivu-icon {
            color: $color-write;
          }
        }
      }
      .userPhoto {
        margin-bottom: 2rem;
        img {
          width: 3.6rem;
          height: 3.6rem;
        }
      }
    }
  }
  .content {
    flex: 1;
  }
}
.ivu-divider-horizontal {
  margin: 1rem 0 !important;
}

.col-weight {
  font-weight: bold;
  font-size: 1.5rem;
}

.row-top {
  margin-top: 1.6rem;
}
.row-bottom {
  margin-bottom: 1rem;
}
</style>
