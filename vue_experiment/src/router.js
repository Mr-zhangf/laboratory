import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routerPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error=> error)
}

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('@/views/Login'),
      children:[
        {
          path:'',
          name:'login',
          meta:{title:'登录'},
          component: () => import('@/views/Login')
        }
      ]
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('@/components/menuNav'),
      children:[
        {//简介
          path: 'introdu',
          name: 'introdu',
          component: () => import('@/views/manager/article/article')
        },{//规则制度
          path: 'rules',
          name: 'rules',
          component: () => import('@/views/manager/article/article')
        },{//安全准则
          path: 'security',
          name: 'security',
          component: () => import('@/views/manager/article/article')
        },{//规划
          path: 'planning',
          name: 'planning',
          component: () => import('@/views/manager/articleList/articleList')
        },{//人员配置
          path: 'staffing',
          name: 'staffing',
          component: () => import('@/views/manager/articleList/articleList')
        },{//实验
          path: 'experiment',
          name: 'experiment',
          component: () => import('@/views/manager/articleList/articleList')
        },{//大纲
          path: 'outline',
          name: 'outline',
          component: () => import('@/views/manager/articleList/articleList')
        },{//实验讲义
          path: 'notes',
          name: 'notes',
          component: () => import('@/views/manager/articleList/articleList')
        },{//实验视频
          path: 'video',
          name: 'video',
          component: () => import('@/views/manager/video/video')
        },{//实验设备
          path: 'equipment',
          name: 'equipment',
          component: () => import('@/views/manager/articleList/articleList')
        },{//教学成果
          path: 'achievements',
          name: 'achievements',
          component: () => import('@/views/manager/articleList/articleList')
        },{//论文
          path: 'paper',
          name: 'paper',
          component: () => import('@/views/manager/articleList/articleList')
        },{//教材
          path: 'material',
          name: 'material',
          component: () => import('@/views/manager/articleList/articleList')
        },{//公告管理
          path: 'notice',
          name: 'notice',
          component: () => import('@/views/manager/notice/notice')
        }, 
        {
          path: 'news',
          name: 'news',
          component: () => import('@/views/manager/news/news')
        },  {
          path: 'picture',
          name: '首页图片',
          component: () => import('@/views/manager/picture/picture')
        }
      ]
    }
    ,{
      path:'/home',
      name:'home',
      meta:{  title:'首页'},
      component: () => import('@/views/home/home')
    },{
      path:'/general',
      name:'general',
      meta:{  title:'概况'},
      component: () => import('@/views/home/general')
    },{
      path:'/teaching',
      name:'teaching',
      meta:{  title:'教学'},
      component: () => import('@/views/home/teaching')
    },{
      path:'/equipment',
      name:'equipment',
      meta:{  title:'设备'},
      component: () => import('@/views/home/equipment')
    },{
      path:'/system',
      name:'system',
      meta:{  title:'制度'},
      component: () => import('@/views/home/system')
    },{
      path:'/results',
      name:'results',
      meta:{  title:'成功'},
      component: () => import('@/views/home/results')
    },{
      path:'/news',
      name:'news',
      meta:{  title:'新闻和公告'},
      component: () => import('@/views/home/news')
    }
  ]
})
