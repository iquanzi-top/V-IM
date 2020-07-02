<template>
    <div class="im-settings">
        <Tabs>
            <TabPane label="个人设置" icon="ios-person">
                <List class="list_content">
                    <ListItem>
                        <ListItemMeta title="登录后默认状态"/>
                        <template slot="action">
                            <Select v-model="userCurStatus">
                                <Option v-for="item in userStatus" :value="item.value" :key="item.value">
                                    {{ item.label }}
                                </Option>
                            </Select>
                        </template>
                    </ListItem>
                    <ListItem>
                        <ListItemMeta title="通过帐号被其他人搜索到" description="关闭后，其他用户将无法通过帐号搜索到您"/>
                        <template slot="action">
                            <i-switch size="large" v-model="searchStatus" @on-change="changeSearch" true-color="#13ce66" false-color="#ff4949">
                                <span slot="open">允许</span>
                                <span slot="close">拒绝</span>
                            </i-switch>
                        </template>
                    </ListItem>
                    <ListItem>
                        <ListItemMeta title="非好友音视频呼叫" description="开启后，允许非好友用户发起音视频呼叫"/>
                        <template slot="action">
                            <i-switch size="large" v-model="callStatus" @on-change="changeCall" true-color="#13ce66" false-color="#ff4949">
                                <span slot="open">允许</span>
                                <span slot="close">拒绝</span>
                            </i-switch>
                        </template>
                    </ListItem>
                    <ListItem>
                        <ListItemMeta title="同步聊天记录" description="开启后，在其他设备登录时，会同步最近的聊天记录信息"/>
                        <template slot="action">
                            <i-switch size="large" v-model="recordStatus" @on-change="changeRecord" true-color="#13ce66" false-color="#ff4949">
                                <span slot="open">允许</span>
                                <span slot="close">拒绝</span>
                            </i-switch>
                        </template>
                    </ListItem>
                </List>
            </TabPane>
            <TabPane label="群组设置" icon="ios-people">
                群组设置项
            </TabPane>
        </Tabs>
    </div>
</template>

<script>
    import conf from "../conf";

    export default {
        name: "userSettings",
        computed: {
            messageList: {
                get: function () {
                    return this.$store.state.messageList;
                },
                set: function (messageList) {
                    this.$store.commit("setMessageList", messageList);
                }
            }
        },
        data() {
            return {
                host: conf.getHostUrl(),
                user: {},
                userCurStatus: 'online',
                userStatus: [
                    {
                        value: 'online',
                        label: '在线'
                    },
                    {
                        value: 'busy',
                        label: '忙碌'
                    },
                    {
                        value: 'leave',
                        label: '离开'
                    }
                ],
                searchStatus: true,
                callStatus: false,
                recordStatus: false,
                headers: {
                    "Access-Control-Allow-Origin": "*"
                }
            };
        },
        methods: {
            changeSearch: function (status) {
                console.log("切换个人信息安全设置：", status)
            },
            changeCall: function (status) {
                console.log("切换音视频呼叫设置：", status)
            },
            changeRecord: function (status) {
                console.log("切换同步聊天记录设置：", status)
            }
        },
        created() {
            this.user = this.$store.state.user;
            console.log(this.user);
        }
    };
</script>

<style lang="scss">
    @import "@/static/styles/theme";

    .im-settings {
        flex: 1;
        display: flex;
        flex-direction: column;
        margin-top: 4rem;
        overflow: hidden;
        font-weight: bold;
    }

    .list_content {
        margin-left: 2rem;
        margin-right: 2rem;
    }

    .ivu-scroll-wrapper {
        margin: 0 !important;
    }

    .ivu-scroll-container {
        padding: 15px 15px 5px;
        overflow-y: visible !important;
    }

    /* 重新覆盖iview 里面的 model 小于768px 时候 宽度变100% 的问题 */
    @media (max-width: 768px) {
        .user-model {
            .ivu-modal {
                width: 30rem !important;
                margin: 0 auto;
            }
        }
    }

    .ivu-tabs-tab {
        font-size: 1.6rem;
    }

    .ivu-list-item-meta-title {
        font-weight: bold !important;
    }

    .ivu-list-item-meta-description {
        font-size: 0.8rem !important;
        color: rgba(0, 0, 0, 0.22) !important;
    }

    .ivu-drawer-body {
        padding: 0 !important;

        .messages {
            height: calc(100% - 3rem);
        }
    }
</style>
