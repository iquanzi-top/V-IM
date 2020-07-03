<template>
    <div>
        <Row v-if="smallPrefix">
            <Col span="2">
                <img class="smallLogo" src="~@/static/icon.png" alt="icon" />
            </Col>
            <Col span="16">
                <Input size="large" v-model="key" search enter-button="搜索" :placeholder="searchKey" @on-search="search" />
            </Col>
        </Row>
        <Row v-if="!smallPrefix" type="flex" justify="center">
            <Col span="16">
                <Input size="large" v-model="key" search enter-button="搜索" :placeholder="searchKey" @on-search="search" />
            </Col>
        </Row>
    </div>
</template>

<script>
import conf from "../conf";
//import { pinyin } from "../../../utils/Pinyin.js";
import RequestUtils from "../../../utils/RequestUtils";

/*const {
    imageLoad,
    transform
} = require("../../../utils/ChatUtils");*/

export default {
    name: "searchPortal",
    data: function () {
      return {
          key: "",
          searchUserList: []
      }
    },
    props: {
        searchKey: {
            type: String,
            default: '用户帐号'
        },
        smallPrefix: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        search: function () {
            let self = this;
            this.searchUserList = [];

            let param = new FormData();
            param.set("key", self.key);

            let requestApi = RequestUtils.getInstance();
            requestApi
                .request(conf.getSearchUserUrl(), param)
                .then(response => {
                    return response.json();
                })
                .then(json => {
                    self.searchUserList = json;
                    self.$emit('searchResult', json);
                });
        }
    }
}
</script>

<style scoped>
    .ivu-input {
        border: solid 1px rgb(220, 222, 226) !important;
    }
    .smallLogo {
        height: 2.5em;
    }
</style>
