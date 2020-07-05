<template>
  <div class="welcome-message">
    <div v-if="show">
      <SearchPortal class="search-box" searchKey="用户帐号或群组名称" :smallPrefix="show" @searchResult="searchResult"></SearchPortal>
      <Divider />
      <Row type="flex" justify="start" class-name="code-row-bg">
        <Col push="1" offset="1" span="4" v-for="(user, index) in result.slice(0, page * 4)" :key="index" @click.native="clickUser(user)">
          <Card>
            <div>
              <img style="height: 2.5em;" :src="host + user.avatar" />
              <h3>{{ user.name }}</h3>
            </div>
          </Card>
        </Col>
      </Row>
      <Row class="row-top" type="flex" justify="start" class-name="code-row-bg">
        <Col push="1" offset="1" span="4" v-for="(user, index) in result.slice(page * 4)" :key="index" @click.native="clickUser(user)">
          <Card>
            <div>
              <img style="height: 2.5em;" :src="host + user.avatar" />
              <h3>{{ user.name }}</h3>
            </div>
          </Card>
        </Col>
      </Row>
      <Row v-if="Math.ceil(total / 8) > 1">
        <Page :total="Math.ceil(total / 8)" show-total />
      </Row>
    </div>
    <div v-if="!show">
      <img src="~@/static/icon.png" alt="icon" />
      <SearchPortal class="search-box" searchKey="用户帐号或群组名称" :smallPrefix="show" @searchResult="searchResult"></SearchPortal>
    </div>
    <Modal closable class="user-model" v-model="modal" footer-hide :title="showUser.name" width="300">
      <p class="user-model-img">
        <img :src="host + showUser.avatar" class="img" />
      </p>
      <div>
        <p class="user-model-item">
          <label>姓名：</label>
          <span>{{ showUser.name }}</span>
        </p>
        <p class="user-model-item">
          <label>手机：</label>
          <span>{{ showUser.mobile }}</span>
        </p>
        <p class="user-model-item">
          <label>邮箱：</label>
          <span>{{ showUser.email }}</span>
        </p>
        <p>
          <Button type="success" size="large" long @click="addFriend(showUser.id)">添加好友</Button>
        </p>
      </div>
    </Modal>
  </div>
</template>
<script>
import conf from "../conf";
import RequestUtils from "../../../utils/RequestUtils";
import SearchPortal from "./searchPortal.vue";

export default {
  name: "welcome",
  data: function () {
    return {
      host: conf.getHostUrl(),
      show: false,
      total: 0,
      page: 0,
      result: [],
      modal: false,
      showUser: {}
    }
  },
  components: {
    SearchPortal
  },
  methods: {
    searchResult: function (data) {
      this.show = data.length > 0;
      this.total = data.length;
      this.page = this.show ? Math.ceil(data.length / 8) : 0;
      this.result = data;
    },
    clickUser: function (user) {
      this.showUser = user;
      this.modal = true;
    },
    addFriend: function (userId) {
      // let self = this;
      console.log('申请添加【', userId, '】为好友');
      let param = new FormData();
      param.set("userId", userId);

      let requestApi = RequestUtils.getInstance();
      requestApi
              .request(conf.getAddFriendUrl(), param)
              .then(response => {
                return response.json();
              })
              .then(json => {
                console.log("添加好友结果：", json);
              });
    }
  }
};
</script>

<style scoped lang="scss">
.welcome-message {
  display: table-cell !important;
  margin-top: 4rem;
  height: 100%;
  text-align: center;
  vertical-align: middle;
  & > img {
    width: 12rem;
    margin-top: 10rem;
  }
}
.row-top {
  margin-top: 2rem;
}
</style>
