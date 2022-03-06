<template>
  <!-- 新闻管理 -->
  <section class="home-container">
    <MasterPage title>
      <div slot="title-icon">
        <Icon type="ios-game-controller-b" />
      </div>
      <div slot="paddingContent">
         <Form :model="formItem" :label-width="100" ref="formValidate" :rules="ruleInline" >
          <div class="border1">
            <Row>
              <Col span="24">
                <FormItem label="上传文件："> 
                     <input type="file" ref="clearFile" @change="getFile($event)" multiple="multiplt" 
                  class="add-file-right-input" accept=".xlsx,.xls,.doc,.docx,.pdf" id="upload-ele"><br>
                  <a :href="'http://localhost:9090/'+ formItem.file" v-if="formItem.file">{{formItem.file}}</a>
                </FormItem>
              </Col>
              <Col span="24">
                <FormItem label="内容：" prop="content">
                  <div id="div1" ></div>
                </FormItem>
              </Col>
              <Col span="24" style="text-align:center;">
                <Button type="primary" @click="saveData">保存</Button>
              </Col>
            </Row>
          </div>
        </Form>
      </div>
    </MasterPage>
  </section>
</template>
<script>
import MasterPage from "@/components/Master";
import inp from "wangeditor"
export default {
  components: {
    MasterPage
  },
  data() {
    return {
      formItem: {},
      ruleInline: {
        title: [{ required: true, message: "不能为空" }],
        content: [{ required: true, message: "不能为空" }],
      }, 
      editor:'',
    };
  },
  watch: {
    $route: {
      handler: function(val, oldVal){
        this.loadData()
      }
    }
  },
  mounted()  {
    this.$nextTick(() => {
      this.editor = new inp('#div1')
      this.editor.config.onchange = (html) => {
        this.formItem.content = html
      }
		  this.editor.create();
    })
      this.loadData();
  },
  methods: {
    //加载数据
    loadData() {
      console.log()
      var thiz = this;
      var type = this.$route.query.type;
      this.formItem.type = type;
      this.$get("article/getByPage", {type: type,page:1,pageSize:2})
        .then(function(msg) {
          console.log(msg);
          thiz.formItem = msg.data.list[0];
          thiz.editor.txt.html(thiz.formItem.content)
          thiz.total = msg.data.total;
        });
    },
    //获取文件 上传
    getFile(event) {
      var file = ''
      if(typeof(event.target.files[0]) != "undefined"){
        file = event.target.files[0]
      }else{
        file = document.getElementById('upload-ele').files[0];
      }
      var thiz = this;
      var url = "/api/file/savefile";;
      var formData = new FormData();
      formData.append('file', file); 
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data',
        }
      };
      this.axios.post(url,formData,config)
      .then(function(msg) {
        console.log(msg)
        thiz.formItem.file = msg.data.data.src;
      });
    }, 
    //保存
    saveData() { 
      this.$refs["formValidate"].validate(val => {
        if (val) {
          var thiz = this;
          var url = "article/edit";
          this.$post(url, this.formItem)
          .then(function(msg) {
            thiz.$Message.info("保存成功");
            thiz.addModel = false;
            thiz.loadData();
          });
        } else {
          this.$refs.addModel.visible = true;
          //this.modalLoading = false;
          this.addModel = true;
          return false;
        }
      });
    } 
  }
};
</script>
<style lang="postcss" scoped>
.home-container {
}
.searchBtn {
  float: right;
}
.btnBox button {
  margin-right: 15px;
}
.ivu-icon-ios-game-controller-b {
  display: none;
}
.ivu-modal {
  width: 50% !important;
}
.border1 {
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 5px;
}
.ivu-tabs-nav .ivu-tabs-tab-active {
  color: #2d8cf0;
  font-size: 16px;
}
.titleName {
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
  margin-bottom: 15px;
  color: #01a7e8;
  font-size: 16px;
}
.ivu-table-tbody tr td .ivu-table-cell{
  max-height:70px !important;
}
</style>

