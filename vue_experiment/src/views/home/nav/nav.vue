<template>
<div>
  <div class="top-nav">
    <div class="flex-box">
      <div>实验室管理系统</div>
      <div><router-link to='/'>登录</router-link></div>
    </div>
  </div>
  <img src="@/assets/top.png" style="width: 100%;height: 83px;"/>
  <div class="top-title">新能源学院实验教学中心</div>
  <div class=" nav-main" style="margin-top:90px;">
    <div class="main"> 
       <Menu mode="horizontal" :theme="theme1" :active-name="cur">
        <MenuItem name="1">
            <router-link to='home'>首页</router-link>
        </MenuItem>
         <MenuItem name="5">
            <router-link to='general'>概况</router-link>
        </MenuItem>
        <MenuItem name="2">
            <router-link to='teaching'>教学</router-link>
        </MenuItem>
        <MenuItem name="3">
            <router-link to='equipment'>设备</router-link>
        </MenuItem>
        <MenuItem name="4">
            <router-link to='system'>制度</router-link>
        </MenuItem>
         <MenuItem name="6">
            <router-link to='results'>成果</router-link>
        </MenuItem>
      </Menu>
    </div>
  </div>
</div>
</template>
<script>
import MasterPage from "@/components/Master";
export default {
  components: {
    MasterPage
  },
  data() {
    return {
        theme1: 'light',
        cur:"1",
        account:'',
        noLogin:true,
        isLogin:false,
        searchText:""
    };
  },
  created() {
    this.account = localStorage.getItem("user") || "";
    if(this.account == ""){
      this.noLogin = true;
      this.isLogin = false;
    }else{
      this.noLogin = false;
      this.isLogin = true;
    }
  },
  mounted() {
    this.curNav();
  },
  methods: {
    searchArticle(){
      this.$emit("searchArticle",this.searchText)
    }, 
    curNav(){
      var url = location.href;
      if(url.indexOf("cart") > -1){
        this.cur = "2";
      }else if(url.indexOf("myOrder") > -1){
        this.cur = "3";
      }else if(url.indexOf("myInfo") > -1){
        this.cur = "4";
      }else if(url.indexOf("news") > -1){
        this.cur = "5";
      }else {
        this.cur = "1";
      }
    },
   toOut(){
     this.$post("users/loginOut")
        .then(function(msg) {
          localStorage.setItem("user","");
          location.href = "#/";
        });
   }
  }
};
</script>
<style lang="postcss" scoped>
.main{
  width:1000px;
  margin:0 auto;
}

</style>
<style scoped>
.top-title{
  width:1000px;
  margin: -70px auto;
  font-size: 30px;
  color:#fff;

}
.nav-main{
  background: #67C23A;
}
.main>>>.ivu-icon-ios-search{
      color: #fff !important;
    background: #1bb349;
    padding: 0 10px;
    border-radius: 5px;
    cursor: pointer;
}
.ivu-menu-light{
  background: #9ef838;
}
.nav-main>>>a{
  color:#fff;
  font-size: 16px;
}
.top-nav{
line-height: 30px;
  background: #f8f8f8;
}
.flex-box{
  display: flex;
  justify-content: space-between;
  font-size: 16px;
  width:1000px;
  margin: 0 auto;
  padding:0 20px;
}
</style>
