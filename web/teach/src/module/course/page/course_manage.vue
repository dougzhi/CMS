<template>
  <div>

    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#eee"
      text-color="#000"
      style="margin-bottom: 20px"
      router
      active-text-color="#000">
      <el-menu-item index="1" :route="{path:'/course/manage/summary/'+this.courseid}">课程首页</el-menu-item>
      <el-menu-item index="2" :route="{path:'/course/manage/baseinfo/'+this.courseid}">基本信息</el-menu-item>
      <el-menu-item index="3" :route="{path:'/course/manage/picture/'+this.courseid}">课程图片</el-menu-item>
      <el-menu-item index="4" :route="{path:'/course/manage/marketinfo/'+this.courseid}">课程营销</el-menu-item>
      <el-menu-item index="5" :route="{path:'/course/manage/plan/'+this.courseid}">课程计划</el-menu-item>
      <el-menu-item index="6" :route="{path:'/course/manage/teacher/'+this.courseid}">教师信息</el-menu-item>
      <el-menu-item index="7" :route="{path:'/course/manage/pub/'+this.courseid}">发布课程</el-menu-item>
    </el-menu>
    <router-view class="main"></router-view>
  </div>
</template>
<script>
  import * as courseApi from '../api/course';
  import utilApi from '../../../common/utils';
  export default {
    data() {
      return {
        activeIndex:'2',
        courseid:''
      }
    },
    methods: {

    },
    mounted(){

      //课程id
      this.courseid = this.$route.params.courseid

      console.log("courseid=" + this.courseid)
      //跳转到课程基本信息
      this.$router.push({ path: '/course/manage/baseinfo/'+this.courseid})

    }
  }
</script>
<style scoped>


</style>
