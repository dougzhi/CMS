import http from './../../../base/api/public'
import querystring from 'querystring'

/*页面列表*/
export const media_list = (page,size,params) => {
  //params为json格式
  //使用querystring将json对象转成key/value串
  let querys = querystring.stringify(params)
  return http.requestQuickGet('/media/file/list/'+page+'/'+size+'/?'+querys)
}
