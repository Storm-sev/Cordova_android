/**
 * 头部导航
 * @type data 父组件需要传入一个对象
 * @type title 标题
 * @type isBack 返回上一页：(true显示) 不传值则不显示
 * @type backUrl 返回上一页的状态： 传值则跳转到相应的链接  不传值返回上一页
 * @type isShare 分享：传值则显示
 * @example
 * {title:"标题", isBack:true, isShare:true,backUrl:"可传链接也可不传值"}
 */
const wkHeader = {
    props: ['data'],
    template: `<div class="header" :class="{enter:isEnter}">
                    <header>
                      <div class="abs-l" @click.stop.prevent="onShow" v-if="!data.isBack"><span class="icon menu"></span></div>
                      <div class="abs-l" @click="onBack(data.backUrl)" v-if="data.isBack"><span class="icon back"></span></div>
                      <span>{{data.title}}</span>
                      <div class="abs-r" v-if="data.isShare" @click="onShare($event)"><span class="icon share"></span></div>
                  </header>
                  <aside class="drawer" :class="{enter:isEnter}">
                     <div class="content">
                            <div class="avatar">
                                <div class="img">
                                    <a :href="redirect"><img :src="avatar" alt=""></a>
                                </div>
                                <template v-if="!isShowLogin">
                                    <div class="link"><a :href="redirect">注册/登录</a></div>
                                    <div class="info">享受更多精彩</div>
                                </template>                               
                                <div v-if="isShowLogin" class="name"><a :href="baseUrl + '/pages/my/index.html'">{{user.name}}</a></div>
                            </div>
                            <div class="items">
                                <a :href="redirectMsg" class="item">消息中心</a>
                                <a :href="baseUrl + '/pages/my/opinion.html'" class="item">意见反馈</a>
                                <a :href="baseUrl + '/pages/my/setting/index.html'" class="item">设置</a>
                            </div>
                        </div>
                  </aside>
                  <div class="overlay" @click="onClose"></div>
                  <div class="modal-share" @touchmove.prevent v-show="share" @click="onCloseShare"><img :src="iconModalShare" alt=""></div>
                </div>`,
    data: function () {
        return {
            avatar: rootName + "/assets-wiki/images/login/icon-avatar-default@2x.png",
            isEnter: false,
            isLeave: false,
            isShowLogin: false, //是否登录  true已登录 false未登录
            redirect: "",
            messagesUrl: "", //
            opinionUrl: "",
            settingUrl: "",
            user: {
                name: ''
            },
            share: false,
            redirectMsg: '',
            baseUrl: rootName,
            iconModalShare:rootName + '/assets-wiki/images/modal/icon-modal-share@2x.png'
        }
    },
    mounted: function () {
        this.getLoginStatusToRedirect();
    },
    methods: {
        //点击展开个人中心抽屉
        onShow: function () {
            this.isEnter = true

        },
        //关闭
        onClose: function () {
            this.isEnter = false;
        },
        onBack: function (url) {
            if (!url) {
                window.history.back()
            } else {
                if (url == "ency") {
                    this.$emit('ency-list', false);
                } else {
                    window.location.href = url
                }

            }
        },
        onShare: function (e) {
            this.share = true
        },
        //关闭分享弹出层
        onCloseShare: function () {
            this.share = false
            // this.onTouchMove(false)
        },
        //获取登录状态
        getLoginStatusToRedirect: function () {
            var _info = myLocalStorage.getter('__user__')
            //未登录
            if (!_info) {
                this.isShowLogin = false
                this.redirect = rootName + "/pages/login/login.html"
                this.redirectMsg = rootName + "/pages/login/login.html"
            } else {//登录
                myLocalStorage.setter('__closeTip__', true)
                this.isShowLogin = true
                this.user = _info
                this.redirect = rootName + "/pages/my/index.html"
                this.redirectMsg = rootName + "/pages/my/messages/index.html"
            }
        },
        //读取和设置localStorage
        _localStorage: function (key, value) {
            //value true是设置 false 读取
            return !value ? localStorage.getItem(key) : localStorage.setItem(key, JSON.stringify(value))
        },
        onEncy: function () {
            this.$emit('ency-details', this.sex, this.height);
        }
    }
}

const wkFooter = {
    props: {
        selected: 0
    },
    template: `<footer>
               <div class="content">
                    <a :href="item.link" class="link" :class="{active:index==selected}" v-for="item,index in footData">
                        <i :class="item.icon"></i>
                        <i v-if="index==2" class="icon-midbg"></i>
                        <div class="txt">{{item.name}}</div>
                    </a>
                </div>
            </footer>`,
    data: function () {
        return {
            drawerData: [
                {name: "消息中心", link: rootName + "/pages/my/details.html"},
                {name: "意见反馈", link: rootName + "/pages/my/details.html"},
                {name: "设置", link: rootName + "/pages/my/setting.html"},
            ],
            footData: [
                {name: "首页", icon: "icon-home", link: rootName + "/pages/index/index.html"},
                {name: "资讯", icon: "icon-news", link: rootName + "/pages/news/index.html?channelId=0"},
                {name: "百科", icon: "icon-cyclopedia", link: rootName + "/pages/ency/index.html"},
                {name: "活动", icon: "icon-activity", link: rootName + "/pages/activity/index.html?channelId=1"},
                {name: "活化馆", icon: "icon-activate", link: rootName + "/pages/activate/index.html"}
            ]
        }
    },
    mounted: function () {

    },
    methods: {}
}

/**
 * 五个分类图标
 * @type
 */
const wkCategory = {
    template: `<section class="category-links">
                    <a class="item" v-for="item in links" @click="toList(item.link)">
                        <img :src="item.imgUrl" alt="">
                        <div class="txt" v-html="item.title"></div>
                    </a>
                </section>`,
    data: function () {
        return {
            links: [
                {
                    title: '口头传说和<br>表述',
                    link: rootName + '/pages/ency/list.html?gbCategory=A',
                    imgUrl: rootName + '/assets-wiki/images/index/category-icon-1.png'
                },
                {
                    title: '社会风俗和<br>礼仪、节庆',
                    link: rootName + '/pages/ency/list.html?gbCategory=C',
                    imgUrl: rootName + '/assets-wiki/images/index/category-icon-2.png'
                },
                {
                    title: '表演艺术',
                    link: rootName + '/pages/ency/list.html?gbCategory=B',
                    imgUrl: rootName + '/assets-wiki/images/index/category-icon-3.png'
                },
                {
                    title: '有关自然界<br>和宇宙的知识的知识的知识',
                    link: rootName + '/pages/ency/list.html?gbCategory=D',
                    imgUrl: rootName + '/assets-wiki/images/index/category-icon-4.png'
                },
                {
                    title: '传统手工艺<br>技能',
                    link: rootName + '/pages/ency/list.html?gbCategory=E',
                    imgUrl: rootName + '/assets-wiki/images/index/category-icon-5.png'
                }
            ]
        }
    },
    methods: {
        toList: function (url) {
            var e = mySessionStorage.getter("encyListData");
            if (e) {
                mySessionStorage.setter("encyListData", null)
            }
            window.location.href = url;
        }
    }
}


/**
 * 搜索框
 * @type {{template: string, methods: {}}}
 */
const wkSearch = {
    template: `<section class="search"><a @click="onRedirect()" href="javascript:void(0)" class="input">请输入搜索关键词</a></section>`,
    methods: {
        onRedirect: function (index) {
            console.log(1)
            mySessionStorage.setter("searchListData", null);
            window.location.href = rootName + '/pages/search/index.html?page=index'
        }
    }
}

/**
 * 资讯列表
 * @type item.link 需要传入完整的详情链接地址  ***
 */
const newsItem = {
    props: {
        data: []
    },
    template: `<section class="news-item">
                    <a class="link"  :href="item.href" v-for="item in data">
                        <div class="desc">
                            <i class="icon-hot" v-if="item.flag"></i>
                            <h3>{{item.title}}</h3>
                            <div class="info">
                                <span class="source">{{item.source}}</span>
                                <span class="time">{{item.time}}</span>
                            </div>
                        </div>
                        <div class="img" :class="item.className">
                           <img v-for="img in item.imgUrls" class="lazy" :data-original="img">
                        </div>
                    </a>
                </section>`,
    methods: {}
}


/**
 * 活动列表
 * @type {{props: {activity: Array}, template: string}}
 */
const wkActivity = {
    props: {
        activity: []
    },
    template: `<section class="activity-item">
                    <a class="link" :href="item.href" v-for="item in activity">
                        <img class="lazy" style="height: 100%;" :data-original="item.imgUrls ? item.imgUrls :'../../assets-wiki/images/default/head694_320.png'" alt="">
                        <div class="desc">
                            <i class="icon-hot"></i>
                            <h3>{{item.title}}</h3>
                            <div class="info">
                                <span class="coord" v-show="item.address" v-html="item.address"></span>
                                <span class="time">{{item.time}}</span>
                            </div>
                        </div>
                    </a>
                </section>`,
}

/**
 * 百科列表
 * @type {{props: {datas: Array}, template: string}}
 * @param datas 迭代数据集合
 * @param recommend 是否显示推荐  true/false  默认false
 :src="ency.img ? ency.img :'../../assets-wiki/images/default/694*374.png'"
 <div class="label">
 <p>{{ency.typeName}}</p>
 </div>

 */
const wkEncy = {
    props: {
        datas: [],
        recommend: false
    },
    template: `<div class="contents" id="dataList" v-cloak>
                <div class="content" v-for="(ency, index) in datas" @click="toDetails(ency.id, ency.type)">
                    <div class="cover">
                        <img class="lazy" :data-original="ency.img ? ency.img :'../../assets-wiki/images/default/head694_374.png'">
                        <div class="myLabel" :data-type="ency.type">
                            <img v-if="ency.type == 0" src="../../assets-wiki/images/icon_xiangmu@2x.png"/>
                            <img v-if="ency.type == 1" src="../../assets-wiki/images/icon_chuanchengren@2x.png"/>
                            <img v-if="ency.type == 2" src="../../assets-wiki/images/icon_zuopin@2x.png"/>
                        </div>
                    </div>
                    <div class="des">
                        <div class="title">
                            <h4>{{ency.title}}</h4>
                            <p v-if="ency.marking" class="type">#{{ency.marking}}</p>
                            <p v-if="ency.info" class="info">{{ency.info}}</p>
                            <i v-show='recommend'></i>
                        </div>
                    </div>
                </div>
              </div>`,
    methods: {
        toDetails: function (id, type) {
            window.location.href = rootName + "/pages/ency/details.html?type=" + type + "&id=" + id; // 跳转详情
        }
    }
}


/**
 * 正在加载
 * @type {{}}
 */
var shopLoad = {
    props: {
        msg: ""
    },
    template: `<div class="shop_load" v-if="msg">
                <img :src="rootName + '/assets-wiki/images/shop/Load_sya.gif'" alt="">
              </div>`
};