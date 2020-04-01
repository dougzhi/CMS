import http from './public'
/*数据字典 */
export const sys_getDictionary= dType => {
  return http.requestQuickGet('/sys/dictionary/get/'+dType)
}

/*存储文件元数据*/
export const sys_saveFileMetedata = params => {
  return http.requestPost('/filesystem/saveFileMetedata',params)
}

/*删除文件*/
export const sys_deleteFile = params => {
  return http.requestPost('/filesystem/delete',params)
}


