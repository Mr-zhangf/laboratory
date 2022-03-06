<template>
<div>
  <!-- 概况 -->
  <navMenu ></navMenu>
  <div class="main">
    <br><Alert>
      <Breadcrumb>
        <BreadcrumbItem to="home">首页</BreadcrumbItem>
        <BreadcrumbItem to="general">概况</BreadcrumbItem>
      </Breadcrumb>
    </Alert>
      <Row>
        <Col span="6">
            <Menu  active-name="1-2" :open-names="['1']">
              <Submenu name="1">
                  <template slot="title">分类列表</template>
                  <div >
                      <div @click="loadData(1,'简介')">简介</div>
                      <div @click="loadData(2,'规划')">规划</div>
                      <div @click="loadData(3,'人员配置')">人员配置</div>
                  </div>
              </Submenu>
          </Menu>
        </col>
        <Col span="18" class="list">
          <Row>
            <Col span="24" style="padding:10px 20px;font-size: 20px;font-weight: 700; text-align: center;">
              {{title}}
            </Col>
            <Col span="24" v-if="show1" style="padding:15px 20px;">
                <p style="padding:15px 20px;font-size: 18px; text-align: center;">{{article.title}}</p>
                <p class="content" v-html="article.content">}</p>
                <Alert type="error" style="margin-top:15px;">
                  文件：<a :href="'http://localhost:9090'+ article.file" v-if="article.file">{{article.file}}</a>
                </Alert> 
            </Col>
            <Col span="24" v-for="item in articleData" :key="item.id" class="line-title" v-show="show2"
             @click.native="toDetail(item)">
                <p class="left-title" :title="item.title">{{item.title}}</p>
                <p>{{item.ctime}}</p>
            </Col>
          </Row>
        </col>
      </Row>
  </div>
</div>
</template>
<script>
import navMenu from "@/views/home/nav/nav";
import MasterPage from "@/components/Master";
export default {
  components: {MasterPage,navMenu},
  data() {
    return {
      title:'简介',
      articleData:[],
      article:{},
      csid:'1',
      url: "http://localhost:8080",
      row:{},
      show1: true,
      show2: false,
    };
  },
  created() {
    this.loadData(1,'简介');
  }, 
  methods: {
    //加载数据
    loadData(type,title) {
      this.title = title;
      console.log(title)
      if(type == 1){
        this.show1 = true;
        this.show2 = false;
        this.getArticle(type)
      }else{
        this.show1 = false;
        this.show2 = true;
        this.getArticleList(type - 1)
      }
    },
    //简介
    getArticle(type){
      var thiz = this;
      var params = {
          page:1,
          pageSize:100,
          type: type
      };
      this.$get("article/getByPage", params)
      .then(function(msg) {
        console.log(msg);
        thiz.article = msg.data.list[0];
      });
    },
    //列表
    getArticleList(type) {
      var thiz = this;
      var params = {
          page:1,
          pageSize:100,
          type: type
      };
      this.$get("articleList/getByPage", params)
        .then(function(msg) {
          console.log(msg);
          thiz.articleData = msg.data.list;
        });
    },
    toDetail(row){
      console.log(row)
      this.article = row;
      this.show1 = true;
      this.show2 = false;
    }
  }
};
</script>
<style lang="postcss" scoped>
.main{
  width:1000px;
  margin:0 auto;
}
.ivu-menu-vertical .ivu-menu-item, .ivu-menu-vertical .ivu-menu-submenu-title{
  padding:5px 24px;
  background: #f8f8f8;
  font-size: 16px;
  font-weight: 700;
}
.list{
  margin-top: 10px;
}
 
.line-title{
  display: flex;
  justify-content: space-between;
  line-height: 40px;
  font-size: 15px;
  cursor: pointer;
  border-bottom:1px solid #ddd;
  padding:0 15px;
}
.line-title:hover{
  color: #2d8cf0;
}
.line-title .left-title{
  width: 520px;
  overflow: hidden;white-space: nowrap;text-overflow: ellipsis;
  display: inline-block;
}
.ivu-menu{
  text-align: left;
    line-height: 35px;
    cursor: pointer;
    
}
.ivu-menu div{
  border-bottom: 1px solid #ddd;
  padding-left: 13px;
}
.ivu-row{
  margin-bottom: 50px;
}
.content{
  line-height: 30px;
  font-size: 15px;
  color: #666;
}
</style>

