<template>
  <!-- 管理 -->
  <section class="home-container">
    <MasterPage title>
      <div slot="title-icon">
        <Icon type="ios-game-controller-b" />
      </div>
      <div slot="searchContent" class="search-content-slot">
        <div class="btnBox">
          <Button type="primary" @click="addData">新增</Button>
        </div>
      </div>
      <div slot="paddingContent">
        <Table stripe :columns="columns1" :data="tableDate" size="small"></Table>
      </div>
      <div slot="pager">
        <Page
          :total="total"
          :page-size="pagecount"
          :current="page"
          show-sizer
          show-total
          class="paging"
          @on-change="changepage"
          @on-page-size-change="pagesize"
        />
      </div>
    </MasterPage>
    <!-- 新增弹窗 -->
    <Modal
      v-model="addModel"
      ref="addModel"
      scrollable
      :title="titleText"
      width="700px"
      @on-ok="saveData"
      class-name="vertical-center-modal"
    >
      <Form :model="formItem" :label-width="100" ref="formValidate" :rules="ruleInline">
        <div class="border1">
          <Row>
            <Col span="24">
              <FormItem label="标题：" prop="title">
                <Input v-model="formItem.title" placeholder="请输入"></Input>
              </FormItem>
            </Col>
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
          </Row>
        </div>
      </Form>
    </Modal>
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
      titleText: "",
      page: 1,
      pagecount: 10,
      total: 0,
      addModel: false,
      eidtOrAdd: "",
      formItem: {
        title :"",
        content :"",
        id:"",
      },
      ruleInline: {
        title: [{ required: true, message: "不能为空" }],
        content: [{ required: true, message: "不能为空" }],
      },
      columns1: [
        {
          type: "index2",
          title: "序号",
          width: 60,
          align: "center",
          render: (h, params) => {
            return h(
              "span",
              params.index + (this.page - 1) * this.pagecount + 1
            );
          }
        },
        { title: "标题", key: "title" },
        { title: "文件", key: "file" ,width:"180" },
        { title: "添加时间", key: "ctime" ,width:"160"},
        { title: "内容", key: "content" ,
           render: (h, params) => {
              return h('div', [
                h('span', {
                    style: {
                        display: 'inline-block',
                        width: '100%',
                        overflow: 'hidden',
                        textOverflow: 'ellipsis',
                        whiteSpace: 'nowrap'
                    },
                    domProps: {
                        title: params.row.content
                    }
                }, params.row.content)
            ]);
                
          }
        },
        {
          title: "操作",
          key: "action",
          width: 200,
          align: "center",
          render: (h, params) => {
            return h("div", [
              ,h(
                "Button", {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  style: {
                    marginRight: "10px"
                  },
                  on: {
                    click: () => {
                      this.editData(params.row);
                    }
                  }
                },  "修改"
              ),
              h(
                "Button", {
                  props: {
                    type: "error",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      this.remover(params.row.id);
                    }
                  }
                }, "删除"
              )
            ]);
          }
        }
      ],
      tableDate: [],
      type:[],
      editor:''
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
    this.loadData();;
  },
  methods: {
    //加载数据
    loadData() {
      var thiz = this;
      var type = this.$route.query.type;
      this.type = type;
      var params = {
          page:this.page,
          pageSize:this.pagecount,
          type: type
      };
      this.$get("articleList/getByPage", params)
        .then(function(msg) {
          console.log(msg);
          thiz.tableDate = msg.data.list;
          thiz.total = msg.data.total;
        });
    },
    //获取图片
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
    //下一页上一页
    changepage(val) {
      this.page = val;
      this.loadData();
    },
    pagesize(val) {
      this.pagecount = val;
      this.loadData();
    },
    //删除
    remover(id) {
      var thiz = this;
      this.$Modal.confirm({
        title: "提示",
        content: "<p>确定要删除吗？</p>",
        onOk: () => {
          thiz.$post("articleList/delete", {id: id})
            .then(function(msg) {
              console.log(msg);
              thiz.$Message.info("删除成功！");
              thiz.loadData();
            });
        },
        onCancel: () => {}
      });
    },
   //保存
    saveData() { 
      this.$refs["formValidate"].validate(val => {
        if (val) {
          var thiz = this;
          var url = "";
          if (this.eidtOrAdd == 1) {
            url = "articleList/add";
          } else if (this.eidtOrAdd == 2) {
            url = "articleList/edit";
          }
          this.formItem.type = this.type;
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
    },
    //添加清空表单数据
    addData() {
      this.titleText = "添加";
      this.addModel = true;
      this.eidtOrAdd = 1;
      this.formItem = {
        title :"",
        content :"",
        file :"",
        id:''
      };
    },
    //修改获取表单数据
    editData(row) {
      this.titleText = "修改";
      this.addModel = true;
      this.eidtOrAdd = 2;
      this.cityObj = [];
      this.formItem = JSON.parse(JSON.stringify(row));
      this.editor.txt.html(row.content)
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

