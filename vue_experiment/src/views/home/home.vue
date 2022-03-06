<template>
<div>
  <navMenu ></navMenu>
  <Carousel autoplay v-model="value2" loop>
    <CarouselItem>
        <div class="demo-carousel">
          <img src="@/assets/01.jpg" style="width: 100%;height: 350px;"/>
        </div>
    </CarouselItem>
    <CarouselItem>
        <div class="demo-carousel">
          <img src="@/assets/02.jpg" style="width: 100%;height: 350px;"/>
        </div>
    </CarouselItem> 
    <CarouselItem>
        <div class="demo-carousel">
          <img src="@/assets/03.jpg" style="width: 100%;height: 350px;"/>
        </div>
    </CarouselItem> 
    <CarouselItem>
        <div class="demo-carousel">
          <img src="@/assets/04.jpg" style="width: 100%;height: 350px;"/>
        </div>
    </CarouselItem> 
  </Carousel>
  <div class="main">
    <Row >
      <Col span="12" style="padding:10px">
        <Carousel autoplay v-model="value" loop>
          <CarouselItem v-for="item in picList" :key="item.id">
              <div class="demo-carousel">
                <img :src="url+item.pic" style="width: 100%;height: 300px;"/>
              </div>
          </CarouselItem> 
      </Carousel>
      </Col>
      <Col span="12" style="padding:10px">
        <Card>
            <p slot="title">公告</p>
            <a href="#" slot="extra" @click.prevent="changeLimit">
                更多 <Icon type="ios-arrow-forward" />
            </a>
            <div v-for="item in noticeList" :key="item.id" class="line-title" @click="toDetail(item)">
              <p class="left-title" :title="item.title">{{item.title}}</p>
              <p>{{item.ctime.substring(0,10)}}</p>
            </div>
        </Card>
      </Col>
      <Col span="24" style="padding:10px">
        <Card>
            <p slot="title">新闻</p>
             <a href="#" slot="extra" @click.prevent="changeLimit">
                更多 <Icon type="ios-arrow-forward" />
            </a>
            <div v-for="item in newsList" :key="item.id" class="line-title" @click="toDetail(item)"
             style="margin-bottom: 15px;padding:0 30px;">
              <img :src="url+item.pic" style="width: 200px;height:120px;"/>
              <div style="width: 660px;">
                <p class="left-title" :title="item.title"
                 style="white-space: inherit;width:100%">{{item.title}}</p>
                <p>发布时间：{{item.ctime}}</p>
              </div>
            </div>
        </Card>
      </Col>
    </Row>
    <Row >
      <Card style="height: 340px;">
        <Col span="6" style="padding:10px" class="left-code">
          <div><Icon type="ios-archive-outline" />实验室</div>
          <div>智能管理平台</div>
          <img src="../../assets/saomaOne.jpg" />
        </Col>
        <Col span="4" style="padding:10px" class="link-bg bg1">
          <p>设备预约</p>
          <Icon type="ios-clock-outline" />
        </Col>
        <Col span="4" style="padding:10px" class="link-bg bg2">
          <p>申请预约</p>
          <Icon type="md-checkbox-outline" />
        </Col>
        <Col span="4" style="padding:10px" class="link-bg bg3">
          <p>设备归还</p>
          <Icon type="ios-log-in" />
        </Col>
        <Col span="4" style="padding:10px" class="link-bg bg4">
          <p>校园卡挂失</p>
          <Icon type="logo-whatsapp" />
        </Col>
      </Card>
     </Row>
     <img src="@/assets/bottom.png" style="width: 100%;height: 100px;"/>
     <br><br>
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
      value: 0,
      value2: 0,
      noticeList: [],
      picList: [],
      newsList:[],
      csid:'1',
      url: "http://localhost:9090",
      row:{},
      searchText:''
    };
  },
  created() {
    this.getNotice();
    this.loadData( );
    this.getPic()
  },
  mounted() {
    
  },
  methods: {
 
    //获取新闻
    loadData() {
      var thiz = this;
      var params = {
          page:1,
          pageSize: 4
      };
      this.$get("news/findNews", params)
        .then(function(msg) {
          thiz.newsList = msg.data.list;
        });
    },
    //获取公告
    getNotice() {
      var thiz = this;
      var params = {
          page:1,
          pageSize:7 
      };
      this.$get("notice/getByPage", params)
        .then(function(msg) {
          thiz.noticeList = msg.data.list;
        });
    },
     //获取图片列表
    getPic() {
      var thiz = this;
      var params = {
          page:1,
          pageSize:10 
      };
      this.$get("picture/getByPage", params)
        .then(function(msg) {
          thiz.picList = msg.data.list;
        });
    },
    //跳转到新闻和公告
    changeLimit(){
      this.$router.push("news")
    },
    toDetail(row){
      console.log(row)
      localStorage.setItem("detail",JSON.stringify(row));
      location.href = "/#/news?id="+row.id;
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
}
.list{
  margin-top: 10px;
}
.titleName{
  text-align: center;
  font-size: 14px;
  overflow: hidden;
    width: 100%;
    white-space: nowrap;
    text-overflow: ellipsis;
}
.line-title{
  display: flex;
  justify-content: space-between;
  line-height: 30px;
  font-size: 15px;
  cursor: pointer;
}
.line-title:hover{
  color: #2d8cf0;
}
.line-title .left-title{
  width: 340px;
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
  padding-left: 43px;
}
.ivu-row{
  margin-bottom: 50px;
}
.left-code{
  font-size: 20px;
}
.left-code .ivu-icon{
  font-size: 27px;
  font-weight: 700;
}
.left-code img{
  width: 200px;
  margin-left: -20px;
}
.link-bg{
  margin-left:20px;
}
.link-bg p{
  width: 10px;
  font-size: 20px;
  color:#fff;
  margin-left: 60px;
  margin-top: 20px;
  
}
.link-bg .ivu-icon{
  font-size: 50px;
  color:#fff;
  position: absolute;
  bottom: 20px;
  text-align: center;
  left: 34%;
}
.bg1{
  background-image: linear-gradient(#373dfa, #9198e5);
  height: 300px;
  text-align: center;
}
.bg2{
  background-image: linear-gradient(#07a0ea, #5dc3f4);
  height: 300px;
  text-align: center;
}
.bg3{
  background-image: linear-gradient(#7f10d4, #ae6ae2);
  height: 300px;
  text-align: center;
}
.bg4{
  background-image: linear-gradient(#17c714, #6ace69);
  height: 300px;
  text-align: center;
} 
.link-bg:hover{
  margin-top:-5px;
}
</style>

