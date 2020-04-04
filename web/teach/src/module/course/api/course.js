import http from './../../../base/api/public'
import querystring from 'querystring'

//查询课程列表
//我的课程列表
export const findCourseList = (page,size,params) => {
//使用工具类将json对象转成key/value
  let queries = querystring.stringify(params)
  return http.requestQuickGet("/course/coursebase/list/"+page+"/"+size+"?"+queries)
}

//查询课程分类
export const category_findlist= () => {
  return http.requestQuickGet('/category/list')
}
/*添加课程基础信息*/
export const addCourseBase = params => {
  return http.requestPost('/course/coursebase/add',params)
}
/*查询课程计划*/
export const findTeachplanList = courseid => {
  return http.requestQuickGet('/course/teachplan/list/'+courseid)
}
/*添加课程计划*/
export const addTeachplan = teachplah => {
  return http.requestPost('/course/teachplan/add',teachplah)
}

//保存课程图片地址到课程数据 库
export const addCoursePic= (courseId,pic) => {
  return http.requestPost('/course/coursepic/add?courseId='+courseId+"&pic="+pic)
}
//查询课程图片
export const findCoursePicList = courseId => {
  return http.requestQuickGet('/course/coursepic/list/'+courseId)
}

//删除课程图片
export const deleteCoursePic= courseId => {
  return http.requestDelete('/course/coursepic/delete?courseId='+courseId)
}
/*预览课程*/
export const preview = id => {
  return http.requestPost('/course/preview/'+id);
}
/*发布课程*/
export const publish = id => {
  return http.requestPost('/course/publish/'+id);
}
//查询课程信息
export const findCourseView = courseId => {
  return http.requestQuickGet('/course/courseview/'+courseId)
}

/*保存媒资信息*/
export const savemedia = teachplanMedia => {
  return http.requestPost('/course/savemedia',teachplanMedia);
}
