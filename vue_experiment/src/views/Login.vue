<template>
<section class="login-container">
    <div class="bg-wrap" :style="{backgroundImage:`url(${login_img})`}">
        <div class="card-wrap">
        <Card style="width:350px;background: #ffd484;" :dis-hover="true">
            <p slot="title" style="text-align: center;">
                欢迎登录
            </p>
            <Form ref="userForm" :model="userForm" :rules="ruleCustom">
                <FormItem prop="username">
                    <Input v-model.trim="userForm.username" placeholder="请输入" size="large">
                        <Icon type="ios-person-outline" slot="prepend" class="icon-cls"></Icon>
                    </Input>
                </FormItem>
                <FormItem prop="password">
                    <Input type="password" v-model.trim="userForm.password" placeholder="请输入密码" size="large">
                        <Icon type="ios-lock-outline" slot="prepend"  class="icon-cls"></Icon>
                    </Input>
                </FormItem>
                <FormItem>
                    <Button type="primary" @click="btn_login()" long :loading="login_loading">登录</Button>
                </FormItem>
                <FormItem>
                    <Button type="primary" @click="toLink()" long >实验教学首页</Button>
                </FormItem>
            </Form>
        </Card>
    </div>
    </div>
</section>
</template>
<script>

export default {
    data(){
        return {
            login_loading:false,
            login_img:require("@/assets/login-bg.jpg"),
            userForm:{
                username:'admin',
                password:'admin',
                roleId:'1'
            },
            ruleCustom: {
                username: [
                    { required: true, message: '用户名不能为空', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' }
                ]
            }
        }
    },
    methods:{
        btn_login(){
            
            //this.$router.push('/')
            var thiz = this;
            var url = "users/login";
            var params = {
                account:this.userForm.username,
                password:this.userForm.password
            };
            this.$post(url, params)
            .then(function(msg) {
                console.log(msg,7890)
                if(msg.resultCode == "200"){
                    localStorage.setItem("admin",msg.data.account);
                    thiz.$router.push("/admin/introdu?type=1");
                }else{
                    thiz.$Message.error("用户名或密码错误！");
                }
            });
        },
        toLink(){
            this.$router.push("home")
        }
    }
}
</script>
<style lang="postcss" scoped>
.login-container{
    display:flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: calc(100%);
    & .bg-wrap{
        width: 100%;
        height: 100%;
        background-size: cover;
        background-position: center;
        position: relative;
    }
    & .card-wrap{
        position: absolute;
        right:40%;
        top:200px;
    }
    & .icon-cls{
        font-weight:bold;
        width:20px;
        font-size:18px;
    }
}
</style>
