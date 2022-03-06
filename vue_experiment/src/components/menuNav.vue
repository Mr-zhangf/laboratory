<template>
  <section class="admin-layout-container">
      <div class="layout">
        <Layout>
            <Sider ref="sidebar" class="sidebar" hide-trigger collapsible width="230" :collapsed-width="78" v-model.trim="isCollapsed">
                <div class="logo" >
                    <div class="xfind-line" v-if="!isCollapsed">
                        <div class="line-h"></div>
                    </div>
                    <div v-if="!isCollapsed" class="logo-saiqu">    
                        <Avatar icon="ios-person" size="large" style="display:none;"/>    
                        <span class="user-name">实验教学管理系统</span>
                    </div>
                    <Avatar icon="ios-person" size="large" style="display:none;" v-else/>
                </div>
                <Menu
                    ref="side_menu"
                    :active-name="activeMenuName"
                    :open-names="openMenuName"
                    theme="light"
                    width="230px"
                    :class="menuitemClasses"
                    @on-select="choosedMenu"
                    v-if="!isCollapsed">
                    <template v-for="(menu,menu_index) in menus">
                        <Submenu :name="menu.name" v-if="menu.children" :key="menu_index">
                            <template slot="title">
                                <Icon :size="20" :type="menu.icon"></Icon>
                                <span>{{menu.title}}</span>
                            </template>
                            <MenuItem :name="child.name" v-for="(child ,child_index) in menu.children" :key="child_index">
                                <Icon :size="20" :type="child.icon"></Icon>
                                <span>{{child.title}}</span>
                            </MenuItem>
                        </Submenu>
                        <MenuItem :name="menu.name" v-if="!menu.children && menu.showInMenus" :key="menu_index">
                            <Icon :size="20" :type="menu.icon" :key="menu_index"></Icon>
                            <span>{{menu.title}}</span>
                        </MenuItem>
                    </template>
                </Menu>
                <div class="dropdown-wrap"  v-if="isCollapsed">
                    <div class="dw-content">
                        <template v-for="(menu,menu_index) in menus">
                            <Dropdown transfer placement="right-start" v-if="menu.children" @on-click="dropdownClick" :key="menu_index">
                                <Button class="dd-btn" type="text">
                                    <Icon :size="25" :type="menu.icon"></Icon>
                                </Button>
                                <DropdownMenu class="dd-menu" slot="list">
                                    <template v-for="(child, i) in menu.children">
                                        <DropdownItem :name="child.name" :key="i">
                                            <div class="ddi-wapper">
                                                <Icon :size="16" :type="child.icon"></Icon>
                                                <span class="ddi-text" style=" color: #fff;">
                                                    {{ child.title }}
                                                </span>
                                            </div>
                                        </DropdownItem>
                                    </template>
                                </DropdownMenu>
                            </Dropdown>
                            <Dropdown transfer v-if="!menu.children && menu.showInMenus" placement="right-start" @on-click="dropdownClick" :key="menu_index">
                                <Button class="dd-btn" type="text">
                                    <Icon :size="25" :type="menu.icon"></Icon>
                                </Button>
                                <DropdownMenu class="dd-menu" slot="list">
                                    <DropdownItem :name="menu.name">
                                        <div class="ddi-wapper">
                                            <Icon :size="16" :type="menu.icon"></Icon>
                                            <span class="ddi-text" style=" color: #fff;">
                                                {{ menu.title }}
                                            </span>
                                        </div>
                                    </DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                        </template>
                    </div>
                </div>
            </Sider>
            <Layout>
                <!-- :style="{width: isCollapsed?'calc(100% - 78px)':'calc(100% - 230px)'}" -->
                <Header class="layout-header-bar">
                    <div class="header-wapper">
                        <div class="header-left">
                           
                        </div>
                        <div class="header-right userInfo" style="margin-right:50px;">
                            <!-- <Button type="text" icon="person" size="large">个人中心</Button>
                            <Button type="text" icon="android-notifications" size="large" @click="clickNotice">消息通知</Button> -->
                            <Dropdown  @on-click="changeMenu">
                               <a href="javascript:void(0)" style="color:#fff;font-size:15px;">
                                    {{username}}
                                    <Icon type="ios-arrow-down"></Icon>
                                </a>
                                <DropdownMenu slot="list" style="margin-top: -5px;">
                                    <!-- <DropdownItem>驴打滚</DropdownItem> -->
                                    <DropdownItem name="loginOut">退出系统</DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                            <!-- <Button type="text" icon="md-exit" class="btn-blue" size="large" @click="quit"></Button> -->
                        </div>
                    </div>
                </Header>
                 <!-- :style="{width:isCollapsed?'calc(100% - 78px)':'calc(100% - 230px)'}" -->
                <Content class="main-content">
                    <Layout class="main-layout-con">
                        <div class="tags-nav-wapper" style="display:none;">
                            <div class="tags-nav">
                                <div class="btn-con left-btn">
                                    <Button type="text" @click="handleScroll(240)">
                                        <Icon :size="18" type="ios-arrow-back" />
                                    </Button>
                                </div>
                                <div ref="scrollOuter" class="scroll-outer" @DOMMouseScroll="handlescroll" @mousewheel="handlescroll">
                                    <div ref="scrollBody" class="scroll-body" :style="{left: tagBodyLeft + 'px'}">
                                        <transition-group name="taglist-moving-animation">
                                            <Tag type="dot"
                                                v-for="tab in tags"
                                                :closable="tab.closable"
                                                :color="tab.choosed? 'primary':'#e9eaec'"
                                                :name="tab.name"
                                                @click.native="clickTag(tab)"
                                                @on-close="closeTag" 
                                                :key="tab.name">
                                                    {{tab.title}}
                                                </Tag>
                                        </transition-group>
                                    </div>
                                </div>
                                <div class="btn-con right-btn">
                                    <Button type="text" @click="handleScroll(-240)">
                                        <Icon :size="18" type="ios-arrow-forward" />
                                    </Button>
                                </div>
                            </div>
                        </div>           
                        <Content class="content-wrapper">
                            <!-- <Spin size="large" fix v-if="spinShow">
                                <Circle2 />
                            </Spin> -->
                            <!--保存组件状态到内存，避免重新渲染-->
                            <keep-alive>
                                <router-view/>
                            </keep-alive>
                        </Content>
                    </Layout>                    
                </Content>
            </Layout>
        </Layout>
    </div>
  </section>
</template>
<script>
export default {
    data(){
        return{
            isCollapsed: false,
            // ------------------------------  菜单操作开始  --------------------------------
            title:'首页',
            activeMenuName:'admin',
            openMenuName:[],
            username:localStorage.getItem("tName") || "admin",
            tagBodyLeft: 0,
            rightOffset: 40,
            outerPadding: 4,
            contextMenuLeft: 0,
            contextMenuTop: 0,
            visible: false,
            menus:[
                {
                    title:'概述管理',
                    num:1,
                    icon:'md-book',
                    name:'gaishu', 
                    children:[
                        {
                            title:'简介管理',
                            name:'introdu',
                            href:'/admin/introdu?type=1' 
                        },{
                            title:'规划管理',
                            name:'planning', 
                            href:'/admin/planning?type=1' 
                        },{
                            title:'人员配置管理',
                            name:'staffing', 
                            href:'/admin/staffing?type=2' 
                        }
                    ]
                },  {
                    title:'教学管理',
                    name:'jiaoxue',
                    icon:'md-book',
                    children:[
                        {
                            title:'实验管理',
                            name:'experiment',
                            href:'/admin/experiment?type=3',
                            num:3,
                        },{
                            title:'大纲管理',
                            name:'outline',
                            href:'/admin/outline?type=4',
                            num:4,
                        },{
                            title:'实验讲义管理',
                            name:'notes',
                            href:'/admin/notes?type=5',
                            num:4,
                        },{
                            title:'实验视频管理',
                            name:'video',
                            href:'/admin/video',
                            num:4,
                        }
                    ]
                },{
                    title:'设备管理',
                    name:'shebei',
                    icon:'md-book',
                    children:[
                        {
                            title:'实验设备管理',
                            name:'equipment',
                            href:'/admin/equipment?type=6',
                            num:3,
                        } 
                    ]
                }, {
                    title:'制度管理',
                    name:'zhidu',
                    icon:'md-book',
                    children:[
                        {
                            title:'规则制度管理',
                            name:'rules',
                            href:'/admin/rules?type=2',
                            num:3,
                        },{
                            title:'安全准则管理',
                            name:'security',
                            href:'/admin/security?type=3',
                            num:4,
                        }
                    ]
                },{
                    title:'成果管理',
                    name:'chengguo',
                    icon:'md-book',
                    children:[  
                        {
                            title:'教学成果管理',
                            name:'achievements',
                            href:'/admin/achievements?type=7',
                            num:3,
                        },{
                            title:'论文管理',
                            name:'paper',
                            href:'/admin/paper?type=8',
                            num:4,
                        },{
                            title:'教材管理',
                            name:'material',
                            href:'/admin/material?type=9',
                            num:4,
                        } 
                    ]
                },
                 
                {
                    title:'首页图片',
                    num:1,
                    name:'picture',
                    icon:'md-book',
                    href:'/admin/picture',
                    closable:true,
                    showInTags:true,
                    showInMenus:true,
                    choosed:true
                },{
                    title:'新闻管理',
                    num:1,
                    name:'news',
                    icon:'md-book',
                    href:'/admin/news',
                    closable:true,
                    showInTags:true,
                    showInMenus:true,
                    choosed:true
                },{
                    title:'公告管理',
                    num:1,
                    name:'notice',
                    icon:'md-book',
                    href:'/admin/notice',
                    closable:true,
                    showInTags:true,
                    showInMenus:true,
                    choosed:true
                }
            ]
            // ------------------------------  菜单操作结束  --------------------------------
        }
    },
    mounted(){

    },
    computed: {
        // 筛选menus中选中的menu
        tags(){
            let tags = [];
            // 将menus中showInTags=true的标签放到tags数组中
            this.menus.forEach(menu=>{
                if(menu.showInTags){
                    tags.push(menu);
                }else if(menu.children){
                    menu.children.forEach(child=>{
                        if(child.showInTags){
                            tags.push(child)
                        }
                    })
                }
            });
            // console.log('tags=>',tags)

            //标签数组排序，从小到到
            tags.sort((a,b)=>{
                return (a.num - b.num)
            })
            return tags;
        },
        rotateIcon () {
            return [
                'menu-icon',
                this.isCollapsed ? 'rotate-icon' : ''
            ];
        },
        menuitemClasses () {
            return [
                'menu-item',
                this.isCollapsed ? 'collapsed-menu' : ''
            ]
        }
    },
    // ------------------------------  菜单操作开始  --------------------------------
    //刷新页面之后保存并选中最后一次菜单和标签
    beforeRouteEnter (to, from, next) {
        next(vm => {
            // 通过 `vm` 访问组件实例
            let activeMenuName = localStorage.activeMenuName;
            vm.activeMenuName = activeMenuName;

            let tags_last_num = vm.tags[vm.tags.length - 1].num;

            if(activeMenuName && activeMenuName.length != 0){
                vm.menus.forEach(_menu=>{
                    if(activeMenuName == _menu.name){
                        _menu.choosed = true;
                        _menu.showInTags = true;
                        _menu.num = tags_last_num + 1;
                    }
                    else if(_menu.children){
                        _menu.children.forEach(child=>{
                            if(activeMenuName == child.name){
                                child.choosed = true;
                                child.showInTags = true;
                                child.num = tags_last_num + 1;
                                vm.openMenuName = [_menu.name];
                            }
                        })
                    }
                    else{
                        // 排除首页
                        if(_menu.name != 'admin'){
                            _menu.choosed = false;
                            _menu.showInTags = false;
                        }else{
                            _menu.choosed = false;
                        }
                    }
                })
            }
        })
    },
    // ------------------------------  菜单操作结束  --------------------------------
    methods: {
        //退出
        changeMenu(name){
            var thiz = this;
            if (name == 'loginOut') {
                thiz.$router.push("/");
            }
        },
        /*tags 滚动事件 */
        handlescroll (e) {
            var type = e.type
            let delta = 0
            if (type === 'DOMMouseScroll' || type === 'mousewheel') {
                delta = (e.wheelDelta) ? e.wheelDelta : -(e.detail || 0) * 40
            }
            this.handleScroll(delta)
        },
        /*tags 滑动事件 */
        handleScroll (offset) {
            const outerWidth = this.$refs.scrollOuter.offsetWidth
            const bodyWidth = this.$refs.scrollBody.offsetWidth
            if (offset > 0) {
                this.tagBodyLeft = Math.min(0, this.tagBodyLeft + offset)
            } else {
                if (outerWidth < bodyWidth) {
                    if (this.tagBodyLeft < -(bodyWidth - outerWidth)) {
                        this.tagBodyLeft = this.tagBodyLeft
                    } else {
                        this.tagBodyLeft = Math.max(this.tagBodyLeft + offset, outerWidth - bodyWidth)
                    }
                } else {
                    this.tagBodyLeft = 0
                }
            }
        },
        quit(){
            this.$router.push('/login')
        },
        clickNotice(){
            this.choosedMenu('notice');
        },
        collapsedSider() {
            this.$refs.sidebar.toggleCollapse();
        },
        // ------------------------------  菜单操作开始  --------------------------------
        closeTag(event, name){
            // 判断该标签是否是选中状态
            // 如果是那么就要设置标签数组中最后一个标签成选中状态
            // 如果否那么就直接删除就好
            let is_choosed = false;
            this.menus.forEach((menu)=>{
                if(menu.name == name){
                    is_choosed = menu.choosed;
                    menu.showInTags = false;
                }else if(menu.children){
                    menu.children.forEach(child=>{
                        if(child.name == name){
                            is_choosed = child.choosed;
                            child.showInTags = false;
                        }
                    })
                }
            })
            // 关闭标签并选中tags中最后一个标签高亮
            if(is_choosed){
                if(this.tags.length > 0){
                    let last_tag = this.tags[this.tags.length-1];
                    last_tag.choosed = true;
                    this.$router.push(last_tag.href);
                    this.activeMenuName = last_tag.name;
                    localStorage.activeMenuName = this.activeMenuName;
                }
            }
        },
        clickTag(tag){
            this.tags.forEach(_tag=>{
                if(_tag.name == tag.name){
                    _tag.choosed=true;
                }else{
                    _tag.choosed= false;
                }
            })
            // 设置菜单选中name
            this.activeMenuName = tag.name;
            localStorage.activeMenuName = this.activeMenuName;
            // 刷新菜单
            this.$nextTick(()=>{
                if(this.$refs.side_menu){
                    this.$refs.side_menu.updateActiveName()
                }
            });
            //点击tab跳转
            this.$router.push(`${tag.href}`);
        },
        choosedMenu(name){
            // 获取标签数组最后一个元素的num
            let tags_last_num = 0;
            if(this.tags.length > 0){
                tags_last_num = this.tags[this.tags.length - 1].num;
            }
            // 设置选中菜单name
            this.activeMenuName = name;
            localStorage.activeMenuName = this.activeMenuName;

            //根据name查找对应的菜单对象
            let menu = null;
            this.menus.forEach(_menu=>{
                if(_menu.name == name){
                    // 只有不在tags数组中的元素才能设置num
                    if(!_menu.showInTags){
                        _menu.num = tags_last_num + 1;
                    }
                    menu = _menu;
                    _menu.showInTags = true;
                    _menu.choosed = true;

                }
                else if(_menu.children){
                    _menu.children.forEach(child=>{
                        if(child.name == name){
                            // 只有不在tags数组中的元素才能设置num
                            if(!_menu.showInTags){
                                child.num = tags_last_num + 1;
                            }
                            menu = child;
                            child.showInTags = true;
                            child.choosed = true;

                        }else{
                            child.choosed = false;
                        }
                    })
                }
                else {
                    _menu.choosed = false;
                }
            })
            this.$router.push(`${menu.href}`);
        },
        dropdownClick(name){
            this.choosedMenu(name);
        }
        // ------------------------------  菜单操作结束  --------------------------------
    }
}
</script>
<style lang="postcss" scoped>
@import '../assets/common.pcss';
.ivu-btn-text:hover{
    background-color: rgba(255, 255, 255, .2) !important;
}
.ivu-layout.ivu-layout-has-sider{
    height: 100%;
}
.ivu-layout-sider{
    background: #fff;
    transition: none;
}
.ivu-layout-header{
    height: 60px;
    line-height: 60px;
}
.ivu-menu{
    height: calc(100% - 60px);
    overflow-y: auto;
}
.ivu-menu-item{
    white-space: nowrap;
}
.ivu-menu-submenu{
    white-space: nowrap;
    & .ivu-menu-submenu-title{
        white-space: nowrap;
    }
}

.dd-menu{
    width: 200px;
    min-width: 200px;
    height: 100%;
    & .ddi-wapper{
        display:flex;
        align-items:center;
        & .ddi-text{
            padding-left:10px;
        }
    }
}
.admin-layout-container{
    position: absolute;
    width: 100%;
    height: 100%;
    & .layout{
        background: #f5f7f9;
        position: relative;
        overflow: hidden;
        height: 100%;
        & .sidebar{
            background: #fff;
        }
        & .dropdown-wrap{
            height: 100%;
            position: relative;
            display: flex;
            flex-direction: column;
            background: #fff;
            z-index: 11;
            & .dw-content{    
                height: 100%;
                & .dd-btn{
                    width: 83px;
                    margin-left: -5px;
                    padding:10px 0;
                }          
            }              
        }
        & .logo{
            width: auto;
            height: 60px;
            display: flex;
            text-align: center;
            align-items: center;
            justify-content: flex-start;
            cursor: pointer;
            border-bottom: 1px solid #363e4f;
            position: relative;
            padding-left:20px;
            background:#061461;
            & .logo-saiqu{
                display: flex;
                align-items: center;
                justify-content: flex-start;
                position: relative;     
                
                & .user-name{
                    color:#fff;
                    font-size:16px;
                    margin-left:15px;
                }
            }
           
        }
    }
    & .layout-header-bar{
        background: #fff;
        padding: 0;
        position: relative;
        display: flex;
        flex-direction: column;
        z-index: 20;
        width:100%;
        & .header-wapper{
            display:flex;
            align-tems:center;
            justify-content:space-between;
            position: relative;
            z-index: 1;
            background: #061461;
            color: #fff;
            & .header-left{
                display:flex;
                align-items:center;
                & .header-title{
                    font-size:18px;
                    font-weight:bold
                }
            }
            & .header-right{
                margin-right:20px;                
                & .btn-blue{
                    color: #fff;
                    font-size:16px;
                }
            }
        }
    }

    & .main-content{
        width: 100%;
        height: calc(100% - 60px);
        position: relative;
        overflow: auto;
        & .main-layout-con{
            height: 100%;
            overflow: hidden;
            & .content-wrapper{
                overflow: auto;
                padding: 10px;
                position: relative;
                & .spinCls{
                    width: 60px !important;
                    height: 60px !important;
                    &::after{
                        background: linear-gradient(#061461, #061461);
                    }
                }
            }
            & .tags-nav-wapper{
                background: #f0f0f0;
                height: 40px;
                padding: 0;
                & .tags-nav{
                    height: 100%;
                    width: 100%;
                    position: relative;
                    border-top: 1px solid #F0F0F0;
                    border-bottom: 1px solid #F0F0F0;
                    & .btn-con{
                        position: absolute;
                        top: 0px;
                        height: 100%;
                        background: #fff;
                        padding-top: 3px;
                        z-index: 10;            
                    }
                    & button{
                        padding: 6px 4px;
                        line-height: 14px;
                        text-align: center;
                    }
                    & .left-btn{
                        left: 0px;
                        border-right: 1px solid #F0F0F0;
                    }
                    & .right-btn{
                        right: 0px;
                        border-left: 1px solid #F0F0F0;
                    }
                    & .scroll-outer{
                        position: absolute;
                        left: 28px;
                        right: 28px;
                        top: 0;
                        bottom: 0;
                        
                        & .scroll-body{
                            height: calc(100% - 1px);
                            display: inline-block;
                            padding: 1px 4px 0;
                            position: absolute;
                            overflow: visible;
                            white-space: nowrap;
                            transition: left .3s ease;
                            .ivu-tag-dot-inner{
                                transition: background .2s ease;
                            }
                        }
                    }
                    & .contextmenu {
                        position: absolute;
                        margin: 0;
                        padding: 5px 0;
                        background: #fff;
                        z-index: 100;
                        list-style-type: none;
                        & li {
                            margin: 0;
                            padding: 5px 15px;
                            cursor: pointer;
                            &:hover {
                                background: #eee;
                            }
                        }
                    }
                }
            }
        }
    }

    & .layout-logo-left{
        width: 90%;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        margin: 15px auto;
    }   
    
    & .menu-icon{
        cursor: pointer;
        transition: all .3s;
        margin: 0 20px 0;
    }
    & .rotate-icon{
        transform: rotate(-90deg);
    }
    & .menu-item {
        position: absolute;
        overflow: auto;
        &:after{
            width: 0;
        }
        & span{
            display: inline-block;
            white-space: nowrap;
            vertical-align: bottom;
            transition: width .2s ease .2s;
            color:#fff;
        }        
        & i{
            transform: translateX(0px);
            transition: font-size .2s ease, transform .2s ease;
            vertical-align: middle;
            font-size: 16px;
        }
    }
    & .collapsed-menu {
        & span{
            width: 0px;
            transition: width .2s ease;
        }
        & i{
            transform: translateX(5px);
            transition: font-size .2s ease .2s, transform .2s ease .2s;
            vertical-align: middle;
            font-size: 22px;
        }
    }
}
.home-container{
    height: 100%;
}
.content-wrapper ,.ivu-layout-content{
    padding: 0 !important;
}
.ivu-menu-light{
background: #e7eaed;
}
.userInfo >>>.ivu-dropdown .ivu-select-dropdown{
    margin-top: -5px;
}
.admin-layout-container .layout .logo{
    border-bottom: none;height: 61px;
}
.adminImg{
    width: 32px;
    border-radius: 32px;
    vertical-align: middle;
}
.home-container >>>.ivu-select{display: none !important;}
.ivu-menu-light{
    background: #3c4679;
    color: #fff;
}
</style>
<style scoped>
.admin-layout-container .menu-item .ivu-menu-item-active>>> span{

}
.ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu){
    background: #2d8cf0;
}
</style>
