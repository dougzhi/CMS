<template>
  <div id="app2">
    <div class="content">
      <div class="send_title">
        <div class="img_hear">
         <van-image width="50" height="50" :src="enterpriseImg" />
        </div>
        <div class="send_title_text">
          {{enterpriseInfo.registerName}}
        </div>
        <div class="send_title_titel">
          <div class="send_title_titel_1">
            诚信分 <b style="color: #222222">{{enterpriseInfo.score|| '暂无'}}</b>
          </div>
          <div class="send_title_titel_2">
            <van-rate v-model="enterpriseInfo.score/2" size="12" allow-half readonly color="#FF7F00"  void-icon="star" void-color="#eee"/>
          </div>
          <div style="float: right;color: #2FBFE8;font-size:12px;margin-right: 1.5rem;" @click="toInfo">详情<van-icon name="arrow" style="float: right;color: #2FBFE8;font-size:12px;padding:3px;" size="11"/>
            
          </div>
        </div>

      </div>
      <div style="width:100%;height:0.5rem;background:rgba(247,247,247,1);margin-top:24px">

      </div>
      <div class="dress_content">
        <div class="dress_test">
          体验怎么样，点评一下分享吧
        </div>
        <div class="dress_url" v-for="item in groups" :key="item.id">
          <div class="critding">
            {{item.name}}
          </div>
          <div class="credit_red" @click="onChange(item)">
            <van-rate v-model="item.star" color="#FF7F00" size="1.4rem"/>
          </div>
          <div class="awesome" v-show="item.star === 5">
            超赞
          </div>
        </div>
      </div>
      <div class="selectItems">
        <label v-for="(item,index) in items.tags" :key="item">
          <input type="checkbox" name="hobby" :checked="items.checkedTags[index]" @click="selectItem(item)">
          <div style="padding:0px 8px">{{item}}</div>
        </label>
      </div>
      <!-- <div style="width:100%;height:24px"></div> -->
      <div class="selecTags">
         <span v-for="selected in selectedItems">
          <van-tag color="#f2826a" plain style="margin-right: 5px" v-for="item in selected.tags" :key="item">{{item}}</van-tag>
        </span>
      </div>
      <div class="textarea_conetn">
        <van-field v-model="message" rows="4" autosize type="textarea" maxlength="200" placeholder="亲，您可以分享下环境、服务⽅方⾯面的感受噢" show-word-limit
                  style="background-color: #F7F7F7;height: 100%;" />
      </div>
      <div class="dress_test">添加图片</div>
      <div style="margin-left: 1.5rem;margin-top: 1rem;">
        <van-uploader v-model="uploader" multiple :max-count="3" accept="image/*" :after-read="uploadImg"/>
      </div>
      <div style="margin-left: 1.5rem;margin-top: 1.5rem;margin-bottom:24px">
          <van-checkbox v-model="anonymously" checked-color="#2FBFE8">匿名填写</van-checkbox>
      </div>
      <div style="margin: 1.5rem">
    <van-button color="#2FBFE8"
                  block
                  round
                  :loading="loading"
                  loading-text="提交中..."
                  style=""
                  @click="submit">提交评价</van-button> 
      </div>
 
      </div>
    <van-overlay :show="isSuccess">
      <div class="wrapper" @click.stop>
        <div class="block">
          <van-image width="40" height="40" style="margin-left: 50%;left: -22px;margin-top:34px" :src="successImg" />
          <p>感谢您的评价</p>
        </div>
      </div>
    </van-overlay>
    <van-overlay :show="isError">
      <div class="wrapper" @click.stop>
        <div class="block">
          <van-image width="40" height="40" style="margin-left: 50%;left: -22px;margin-top:34px" :src="errorImg" />
          <p>{{errorMsg}}</p>
        </div>
      </div>
    </van-overlay>
   </div>
</template>

<script>
import {Dialog} from "vant";
import wu from '../assets/ic_moreng_touxiang.png'
import yes from '../assets/yes.png'
import error from "../assets/delete.svg";


export default {
  name: 'rating',
  data() {
      return {
          getEnterpriseInfoUrl: "/api/enterprise/get",
          experiences: '/api/experience/getExperiences',
          tags: '/api/experience/getTags',
          save: '/api/evaluate/save',
          saveFile: '/api/attach/save',
          checkImg: '/api/attach/check',
          enterpriseImg: wu,
          successImg: yes,
          errorImg: error,
          enterpriseInfo: {
              enterpriseId: null,
              registerName: '',
              score: 10,
              qualityScore: 10,
              standardScore: 10,
              experienceScore: 10,
              address: '',
              mobile: '',
              industryCategory: ''
          },
          loading: false,
          isSuccess: false,
          isError: false,
          errorMsg: '',
          checked: true,
          anonymously: true,
          message: '',
          uploader: [],
          radio: 1,
          groups: [],
          items: {
              id: null,
              leve: 0,
              tags: [],
              checkedTags: []
          },
          itemMap: [],
          selectedItems: [],
      }
  },
    components: {
        Dialog,
    },
    mounted() {
        // 获取企业基本信息
        let id = this.getQueryString("id") ?? this.$store.getters.getEnterpriseId;
        if (id === null) {
            this.isError = true;
            this.errorMsg = '二维码信息异常';
            return
        }
        if (this.$store.getters.getEnterpriseId !== id) {
            this.$store.commit("reduceEnterpriseId", id);
        }
        this.enterpriseInfo.enterpriseId = id;
        this.init();
    },
    methods: {
        init(){
          this.$axios({
              method: "get",
              changeOrigin: true, // 这个参数代表发送跨域请求
              url: this.getEnterpriseInfoUrl,
              //注意：post方式提交 参数这里可以写data get方式提交用params
              params: {
                  enterpriseId: this.enterpriseInfo.enterpriseId
              }
          }).then((response) => {
              if (response.data.ret === 1) {
                  Dialog.alert({message: response.data.msg ?? "企业信息查询失败"})
                  return
              }
              this.enterpriseInfo = response.data.data;
          });
          //分组
          this.$axios({
              method: "get",
              changeOrigin: true, // 这个参数代表发送跨域请求
              url: this.experiences,
              //注意：post方式提交 参数这里可以写data get方式提交用params
              params: {
                  enterpriseId: this.enterpriseInfo.enterpriseId
              }
          }).then((response) => {
              if (response.data.ret === 1) {
                  Dialog.alert({message: response.data.msg ?? '评分分组信息查询失败'});
                  return
              }
              this.groups = response.data.data.map(item => {
                  let info = {}
                  info.id = item.id;
                  info.name = item.groupName;
                  info.star = 0;
                  return info;
              });
          });
          // 标签
          this.$axios({
              method: "get",
              changeOrigin: true, // 这个参数代表发送跨域请求
              url: this.tags,
              //注意：post方式提交 参数这里可以写data get方式提交用params
              params: {
                  enterpriseId: this.enterpriseInfo.enterpriseId
              }
          }).then((response) => {
              if (response.data.ret === 1) {
                  Dialog.alert({message: response.data.msg ?? '标签信息查询失败'});
                  return
              }
              this.itemMap = response.data.data;
          });
        },
        getQueryString(name) {
            let reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            let r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        },
        onChange(item) {
            this.items.tags = []
            this.items.checkedTags = []
            let filter = this.itemMap.filter(e => e.id === item.id);
            if (filter.length > 0){
                let tagGroup = filter[0].tagGroup.filter(e => parseInt(e.leve) === item.star);
                if (tagGroup.length > 0) {
                    this.items.tags = tagGroup[0].tags;
                }
            }
            this.items.id = item.id
            this.items.leve = item.star
            // 同级别时， 移除其他标签
            let selected = this.selectedItems.filter(e => e.id === this.items.id)
            // 不存在相同时
            if (selected.length > 0){
                if (selected[0].leve === this.items.leve) {
                    this.items.checkedTags = this.items.tags.map(e => selected[0].tags.includes(e));
                } else {
                    selected[0].tags = [];
                    selected[0].checkedTags = []
                }
            }
        },

        selectItem(item) {
            let selected = this.selectedItems.filter(e => e.id === this.items.id)
            // 不存在相同时
            if (selected.length === 0 ){
                let info = {}
                info.id = this.items.id
                info.leve = this.items.leve
                info.tags = [item];
                this.selectedItems.push(info)
            } else {
                // 同星级
                if (selected[0].leve === this.items.leve) {
                    // 存在，移除
                    let index = selected[0].tags.indexOf(item);
                    if (index > -1) {
                        selected[0].tags.splice(index, 1);
                    } else {
                        selected[0].tags.push(item);
                    }
                }
                // 不同星级 ， 覆盖
                else {
                    selected[0].leve = this.items.leve
                    selected[0].tags = [item]
                }
            }
        },
        uploadImg(file) {
            if (!(file instanceof Array)) {
              file = [file];
            }
            file.forEach(e => this.setFileStatus(e));
        },
        async setFileStatus(file) {
            file.status = 'uploading';
            file.message = '上传中...';
            //图片鉴黄
            let forms = new FormData();
            forms.append("files", file.file);
            this.$axios.post(this.checkImg, forms, {headers: {'Content-Type': 'application/json'}}).then((response) => {
                if (response.data.ret === 0) {
                    switch (response.data.data) {
                      case "NORMAL":
                          file.status = 'success';
                          file.message = '上传成功';
                          break;
                      case "POLITICS":
                          file.status = 'failed';
                          file.message = '政治敏感图片，禁止上传';
                          break;
                      case "SEX":
                          file.status = 'failed';
                          file.message = '色情图片禁止上传';
                          break;
                      case "DRUG":
                          file.status = 'failed';
                          file.message = '涉毒违法图片禁止上传';
                          break;
                      case "RAIL":
                          file.status = 'failed';
                          file.message = '含辱骂信息禁止上传';
                          break;
                      case "ADVERTISING":
                          file.status = 'failed';
                          file.message = '广告图片禁止上传';
                          break;
                      case "VIOLENCE":
                          file.status = 'failed';
                          file.message = '图片涉嫌恐怖暴力禁止上传';
                          break;
                    }
                }
            });
        },
        toInfo(){
            this.$router.push({
                name: 'info',
                query: {
                    id: this.$store.getters.getEnterpriseId
                }
            })
        },
        async saveFiles() {
            // 标签
            let tags = this.selectedItems.reduce((prev, cur) => {
                return prev.concat(cur.tags);
            }, []);
            let results = await Promise.all(this.uploader.filter(e => e.status === 'success' && e.url === undefined).map(e => {
                let forms = new FormData();
                forms.append("files", e.file);
                return this.$axios.post(this.saveFile, forms, {headers: {'Content-Type': 'application/json'}});
            }));
            // 判断
            let flag = false;
            let errorMsg;
            results.forEach(e => {
                this.uploader.filter(item => e.data.data === item.file.name).forEach(item => {
                    if (e.data.ret === 0){
                        item.url = e.data.msg;
                    } else {
                        item.status = 'failed';
                        item.message = '图片上传失败';
                        flag = true;
                        errorMsg = e.data.msg;
                    }
                });
            });
            if (flag) {
                this.loading = false;
                Dialog.alert({message: errorMsg ?? '图片上传失败'});
                return;
            }

            let urlList = this.uploader.filter(e => e.status === 'success' && e.url !== null).map(e => e.url);
            console.log(this.uploader)
            //提交
            this.$axios({
                method: "post",
                changeOrigin: true, // 这个参数代表发送跨域请求
                url: this.save,
                //注意：post方式提交 参数这里可以写data get方式提交用params
                data: {
                    enterpriseId: this.enterpriseInfo.enterpriseId,
                    evaluateContent: this.message,
                    photoUrls: urlList,
                    scoreItems: this.groups,
                    tags: tags,
                }
            }).then((response) => {
                if (response.data.ret === 1) {
                    let message = '评论信息涉嫌违规，评论失败';
                    switch (response.data.data) {
                        case "POLITICS":
                            message = '留言中含政治敏感信息，评价失败';
                            break;
                        case "SEX":
                            message = '留言中含色情信息，评论失败';
                            break;
                        case "DRUG":
                            message = '留言中含涉毒违法信息，评论失败';
                            break;
                        case "RAIL":
                            message = '留言中含辱骂信息，评论失败';
                            break;
                        case "ADVERTISING":
                            message = '留言中含广告信息，评论失败';
                            break;
                        case "VIOLENCE":
                            message = '留言中含恐怖暴力信息，评论失败';
                            break;
                    }
                    Dialog.alert({message: message});
                    return
                }
                this.isSuccess = true;
                setTimeout(() => {
                    this.toInfo()
                }, 2000);
            }).catch((response) => {
                Dialog.alert({message: response.data.msg ?? '评价失败'});
            }).finally(() => this.loading = false);
        },
        submit() {
            // 分组校验
            let checkGroup = this.groups.filter(e => e.star === 0);
            if (checkGroup.length > 0) {
                Dialog.alert({message: '请对【' + checkGroup[0].name + '】进行评价'});
                return false;
            }
            this.loading = true;
            this.saveFiles();
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  body,
  html {
    /* background-color: #F7F7F7; */
    width: 100%;
    height: 100%;

  }

  .textarea_conetn {
    /* width: 100%; */
    /* height: 8.75rem; */
    margin: 0.5rem 1.5rem 1.5rem;
  }

  #app2 {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: left !important;
    color: #2c3e50;
    margin-top: 0px !important;
}

  .content {
    width: 100%;
    height: 100%;
    /* background-color: #F7F7F7; */
  }

  .left_title_ioc,
  .right_title_ioc {
    width: 32px;
    height: 100%;
    position: absolute;
    top: 0px;
  }

  .font_title {
    margin: 0px 32px;
    height: 100%;
    text-align: center;
    padding-top: 10px;
    color: #333333;
    font-size: 1rem;
    /* margin-right: - 20px; */
  }

  .left_title_ioc {
    left: 0px;
  }

  .right_title_ioc {
    right: 0px;

    /* margin-left: - 100px; */
  }

  .send_title {
    width: 100%;
    margin-top: 1.5rem;
    font-weight: 500;
    height: auto;
    background-color: #FFFFFF;
  }

  .img_hear {
    height: 3.125rem;
    width: 3.125rem;
    float: left;
    margin-left: 1.5rem;
  }

  .send_title_text {
    height: 1rem;
    font-weight: 600;
    font-size: 1rem;
    margin-left: 5.5rem;
    font-family: PingFangSC-Semibold, PingFang SC;
  }

  .send_title_titel {
    width: 100%;
    height: 1rem;
  }

  .send_title_titel_1 {
    margin-left: 1rem;
    margin-top: 1rem;
    float: left;
    font-size: 0.75rem;
    font-family: PingFangSC-Light, PingFang SC;
    color: #999999;

  }

  .send_title_titel_2 {
    margin-top:1rem;
    margin-left: 0.5rem;
    float: left;
    font-size: 0.875rem;
    font-family: PingFangSC-Light, PingFang SC;
    color: #999999;
  }

  .reads_content {
    width: 100%;
    height: 0.5rem;
    margin-top: 1.5rem;
    background: #F7F7F7;
  }

  .dress_content {
    width: 100%;
    /* margin: 24px; */
    /* height: 10rem; */
  }

  .dress_test {
    width: 80%;
    height: 1.5rem;
    color: #333333;
    font-size: 1rem;
    font-weight: 600;
    font-family: PingFangSC-Light, PingFang SC;
    margin-left: 1.5rem;
    margin-top: 1.5rem;
    margin-bottom: 0.5rem;
  }

  .dress_url {
    width: 100%;
    height: 2.5rem;
    position: relative;
  }

  .critding {
    position: absolute;
    float: left;
    width: 2.25rem;
    height: 0.875rem;
    font-size: 0.875rem;
    color: #9B9B9B;
    font-family: PingFangSC-Light, PingFang SC;
    font-weight: 300;
    margin-left: 1.5rem;
    margin-top: 0.9rem;
  }

  .awesome{
    float: left;
    margin-left: 0.5rem;
    margin-top: 0.9rem;
  font-size:14px;
font-family:PingFangSC-Light,PingFang SC;
font-weight:300;
color:rgba(255,140,26,1);
  }

  .credit_red {
    margin-left: 4rem;
    float: left;
    height: auto;
    margin-top: 0.75rem;
  }

  .call_content {
    height: 4.0625rem;
    width: 100%;

  }

  .call_content_titel_1 {
    margin-left: 1.5rem;
    margin-top: 0.8rem;
    float: left;
  }

  .call_content_titel_2 {
    margin-top: 1.125rem;
    margin-left: 0.5rem;
    float: left;
    font-size: 1rem;
    font-weight: 400;
    font-family: PingFangSC-Light, PingFang SC;
    color: #666666;
  }

  .read_peop {
    width: 100%;
    height: auto;
  }

  .title_read {
    width: 80%;
    height: 3rem;
    font-weight: 600;
    color: #333333;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-size: 1rem;
    margin-top: 1.5rem;
    margin-left: 1.5rem;
  }

  .credit_conent {
    /* background: #1989FA; */
    width: 100%;
    height: 1.875rem;
  }

  .conetet_Ring {
    /* margin-top:1.5rem ; */
    width: 100%;
    height: 2.5rem;
  }

  .people_ring {
    width: 5%;
    /* background: #0570DB; */
    float: left;
    width: 3.2rem;
    height: 100%;
  }

  .name_test {
    /* background: #07C160; */
    float: left;
    /* margin-left:16%; */
    width: 50%;
    height: 100%;
  }

  .name_test_te1 {
    width: 100%;
    height: 50%;
    color: #333333;
    font-size: 0.75rem;
    font-family: AlNile;
  }

  .date_critd {
    float: right;
    width: 20%;
    height: 100%;
    font-size: 0.625rem;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    /* background-color:blue; */
  }

  .ul_box {
    margin: 0 auto;
    padding: 0;
    list-style: none;
    width: 600px;
  }

  .ul_box>li {
    padding: 10px 10px 0 10px;
    overflow: hidden;
    border-bottom: #e5e5e5 solid 1px;
  }

  .ul_box>li:last-child {
    border-bottom: none;
  }

  .ul_box>li>div {
    float: left;
  }

  .ul_box>li>div:nth-child(1) {
    width: 100px;
  }

  .ul_box>li>div:nth-child(2) {
    width: 480px;
    overflow: hidden;
  }

  .selectItems,.selecTags{
    margin-left: 1.5rem;
    margin-top: 1rem;;
  }

  .selectItems>label {
    display: inline-block;
  }
  .selectItems>label>input{
    display: none;
  }
  .selectItems>label>div{
    border: 1px solid #dddddd;
    color: #666666;
    font-size: 14px;
    border-radius: 5px;
    margin-right: 10px;
  }

  .selectItems>label>input:checked+div {
    border: #2FBFE8 solid 1px;
    color: #2FBFE8;
  }

  .wrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
  }

  .block {
    width: 8rem;
    background:rgba(255,255,255,1);
    box-shadow:0px 0px 20px 0px rgba(0,0,0,0.21);
    border-radius:4px;
    position: relative;
  }
  .block>p {
    width: 6rem;
    font-size:16px;
    font-family:PingFangSC-Regular,PingFang SC;
    font-weight:400;
    color:rgba(51,51,51,1);
    line-height:16px;
    margin-left: 50%;
    left: -3rem;
    position: relative;
  }
</style>
