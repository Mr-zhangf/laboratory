<template>
<div>
  <!-- 教学 -->
  <navMenu ></navMenu>
  <div class="main">
    <br><Alert>
      <Breadcrumb>
        <BreadcrumbItem to="home">首页</BreadcrumbItem>
        <BreadcrumbItem to="teaching">教学</BreadcrumbItem>
      </Breadcrumb>
    </Alert>
      <Row>
        <Col span="6">
            <Menu  active-name="1-2" :open-names="['1']">
              <Submenu name="1">
                  <template slot="title">分类列表</template>
                  <div >
                      <div @click="loadData(4,'实验')">实验</div>
                      <div @click="loadData(5,'大纲')">大纲</div>
                      <div @click="loadData(6,'实验讲义')">实验讲义</div>
                      <div @click="loadData(1,'实验讲义')">实验视频</div>
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
                <video controls :src="'http://localhost:9090'+article.video" v-if="isVideo"
                 style="width:700px;"></video>
                <p class="content" v-html="article.content">}</p>
                <Alert type="error" style="margin-top:15px;" v-if="!isVideo">
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
      isVideo: false,
      show1: true,
      show2: false,
    };
  },
  created() {
    this.loadData(4,'实验');
  }, 
  methods: {
    //加载数据
    loadData(type,title) {
      this.title = title;
      console.log(title)
      if(type == 1){
        this.show1 = false;
        this.show2 = true;
        this.isVideo = true;
        this.getVideo(type)
      }else{
        this.show1 = false;
        this.show2 = true;
        this.isVideo = false;
        this.getArticleList(type - 1)
      }
    },
    //实验视频
    getVideo(type){
      var thiz = this;
      var params = {
          page:1,
          pageSize:100,
          type: type
      };
      this.$get("video/getByPage", params)
      .then(function(msg) {
        console.log(msg);
        thiz.articleData = msg.data.list;
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
      if(this.isVideo){

      }
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

