<template>
  <div class="welcome-message">
    <div v-if="show">
      <SearchPortal class="search-box" searchKey="用户帐号或群组名称" :smallPrefix="show" @searchResult="searchResult"></SearchPortal>
      <Divider />
      <Row type="flex" justify="space-around" class-name="code-row-bg">
        <Col span="5">
          <Card>
            <div>
              <img style="height: 2.5em;" src="~@/static/icon.png" />
              <h3>默认Logo</h3>
            </div>
          </Card>
        </Col>
        <Col span="5">
          <Card>
            <div>
              <img style="height: 2.5em;" src="~@/static/icon.png" />
              <h3>默认Logo</h3>
            </div>
          </Card>
        </Col>
        <Col span="5">
          <Card>
            <div>
              <img style="height: 2.5em;" src="~@/static/icon.png" />
              <h3>默认Logo</h3>
            </div>
          </Card>
        </Col>
        <Col span="5">
          <Card>
            <div>
              <img style="height: 2.5em;" src="~@/static/icon.png" />
              <h3>默认Logo</h3>
            </div>
          </Card>
        </Col>
      </Row>
    </div>
    <div v-if="!show">
      <img src="~@/static/icon.png" alt="icon" />
      <SearchPortal class="search-box" searchKey="用户帐号或群组名称" :smallPrefix="show" @searchResult="searchResult"></SearchPortal>
    </div>
  </div>
</template>
<script>
import SearchPortal from "./searchPortal.vue";

export default {
  name: "welcome",
  data: function () {
    return {
      show: false,
      result: []
    }
  },
  components: {
    SearchPortal
  },
  methods: {
    searchResult: function (data) {
      this.show = data.length > 0;
      this.result = data;
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
</style>
