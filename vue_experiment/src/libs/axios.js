import axios from 'axios';
// 引入qs模块，用来序列化post类型的数据
import QS from 'qs'
import { Spin,Message  } from 'iview';

export default{
    install(Vue){
      Vue.prototype.$get = get;
      Vue.prototype.$post = post;
    }
}

const baseUrl =  process.env.NODE_ENV == 'development' ? '/api/' : '';

  /**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get(url, params){    
    return new Promise((resolve, reject) =>{        
        axios.get(baseUrl+url, {            
            params: params        
        }).then(res => {
            resolve(res.data);
        }).catch(err =>{
            //reject(err.data)  
            Spin.hide();
            Message.error('接口异常！');      
        })    
    });
}

/** 
 * post方法，对应post请求 
 * @param {String} url [请求的url地址] 
 * @param {Object} params [请求时携带的参数] 
 */
export function post(url, params) {
    return new Promise((resolve, reject) => {
        console.log(baseUrl+url,999)
         axios.post(baseUrl+url, QS.stringify(params))
        .then(res => {
            resolve(res.data);
        })
        .catch(err =>{
            Spin.hide();
            Message.error('接口异常！');
            //reject(err.data)
        })
    });
}
