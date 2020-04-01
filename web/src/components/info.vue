<template>
  <div id="app1">
    <div class="contents">
      <div style="width: 100%;height: 13.125rem;">
        <van-swipe
          :autoplay="10000"
          class="swiper"
          style="width: 100%;height: 13.125rem;"
        >
          <van-swipe-item v-for="(image, index) in images" :key="index">
            <img
              :src="image"
              style="width: 100%;height: 100%; background-size: cove"
            />
          </van-swipe-item>
        </van-swipe>
      </div>
      <div class="send_titles">
        <div class="send_title_texts">
          {{ enterpriseInfo.registerName }}
        </div>
        <div class="send_title_titels">
          <div class="send_title_titel_1s">
            <img
              :src="iosc"
              style="width: 0.875rem;height: 100%; background-size: cove ;margin-top:8px;margin-left: 24px;float: left"
            />
            <div class="send_title_titel_2s">
              诚信联盟商家
            </div>
          </div>

          <div v-if="false" class="send_title_titel_1s">
            <img
              :src="iosc"
              style="width: 0.875rem;height: 100%; background-size: cove ;margin-top:8px;margin-left: 24px;float: left"
            />
            <div class="send_title_titel_2s">
              支持到店诚信支付
            </div>
          </div>
        </div>
      </div>
      <div class="reads_contents">
        <div class="reads_content_titel_1s">
          {{ enterpriseInfo.score || '暂无' }}
        </div>
        <div class="reads_content_titel_2s">
          <van-rate
            v-model="enterpriseInfo.score / 2"
            allow-half
            readonly
            color="#FF7F00"
            size="1.13rem"
          />
        </div>
        <div class="rest_tests">
          诚信分<span style="margin-left:1rem ;"
            >规范:{{ enterpriseInfo.standardScore || '暂无' }}</span
          ><span style="margin-left:1rem ;"
            >品质:{{ enterpriseInfo.qualityScore || '暂无' }}</span
          ><span style="margin-left:1rem ;"
            >体验:{{ enterpriseInfo.experienceScore || '暂无' }}</span
          >
        </div>
      </div>
      <div class="dress_contents">
        <div class="dress_tests">
          商家地址
        </div>
        <div class="dress_urls">
          {{ enterpriseInfo.address }}
        </div>
      </div>
      <div
        style="width: 100%;height:0.125rem ;background: #EEEEEE;margin-top: 1.25rem;"
      ></div>
      <div class="call_contents">
        <div style="width:50%;height:100%;float:left">
          <div class="call_content_titel_1s">
            <img
              :src="phone"
              style="width: 1.125rem;height: 100%; background-size: cove ;margin-top:8px"
            />
          </div>
          <a
            class="call_content_titel_2s"
            :href="'tel:' + enterpriseInfo.mobile"
          >
            联系商家
          </a>
        </div>
        <div v-if="false" style="width:50%;height:100%;float:right">
          <div class="call_content_titel_1s">
            <img
              :src="ioscs"
              style="width: 1.125rem;height: 100%; background-size: cove ;margin-top:8px"
            />
          </div>
          <div class="call_content_titel_2s" @click="toRating">
            我要投诉
          </div>
        </div>
      </div>
      <div style="width: 100%;height:0.5rem ;background: #EEEEEE;"></div>
      <div class="read_peops">
        <div class="title_reads">游客评价({{ total }})</div>
      </div>
      <van-list
        v-model="loading"
        :finished="finished"
        :immediate-check="false"
        finished-text="-- 我也是有底线的 --"
        @load="onLoad"
      >
        <div class="credit_conents" v-for="item in evaluateList" :key="item.id">
          <div style="width:100%;height:2.2rem">
            <div class="people_rings">
              <van-image
                round
                width="1.5rem"
                height="1.5rem"
                :src="moren"
                style="margin-left: 1.5rem;float: left;margin-top: 0.25rem;"
              >
              </van-image>
            </div>

            <div class="name_tests">
              <div class="name_test_te1s">
                {{ item.userName }}
              </div>
              <div class="name_test_te1">
                <van-rate
                  v-model="item.totalScore/2"
                  readonly
                  allow-half
                  size="12"
                  color="#FF7F00"
                />
              </div>
            </div>
            <div class="date_critds">
              <span style="float:right;margin-right:1rem">{{
                formatDate(item.createDate)
              }}</span>
            </div>
          </div>

          <div style="margin-left: 1.3rem;">
            <van-tag
              size="medium"
              plain
              v-for="tag in item.tags"
              :key="tag"
              style="margin: 4px;"
              >{{ tag }}</van-tag
            >
          </div>
          <div :style="item.isOpen ? 'max-height: 100px;overflow: hidden;margin-bottom: 5px' : ''">
            <div class="red_tesx">
              {{ item.evaluateContent || '暂无留言' }}
            </div>
          </div>
          <div v-if="isShow(item.evaluateContent)">
            <div
              style="margin-left:24px;font-size:14px;
              font-family:PingFangSC-Light,PingFang SC;
              font-weight:300;
              color:rgba(47,191,232,1);
              line-height:16px;"
              @click="item.isOpen = false"
              v-if="item.isOpen"
            >
              全文
            </div>
            <div
              style="margin-left:24px;font-size:14px;
                font-family:PingFangSC-Light,PingFang SC;
                font-weight:300;
                color:rgba(47,191,232,1);
                line-height:16px;"
              @click="item.isOpen = true"
              v-else
            >
              收起
            </div>
          </div>
          <div style="margin-left:1rem">
            <van-image
              v-for="(img,index) in item.photoUrls"
              width="80px"
              height="80px"
              :src="img"
              radius="5px"
              style="margin-left:8px;margin-bottom:1rem"
              @click="imagePreview(item.photoUrls,index)"
            />
          </div>
          <div
            style="
                    height:1px;background:#EBEBEB;margin:24px"
          ></div>
        </div>
      </van-list>
      <div style="height:1.75rem"></div>
    </div>
  </div>
</template>

<script>
import {Dialog, ImagePreview} from 'vant'
import wu from '../assets/zhan_img.png'
import iosc from '../assets/ic-cell-check.png'
import phone from '../assets/ic-cell-phone.png'
import ioscs from '../assets/ic-cell-navigation.png'
import moren from '../assets/my_moren.png'
import { formatDate } from '../config/formatDate '

export default {
  name: 'index',
  data() {
    return {
      images: [wu],
      iosc: iosc,
      phone: phone,
      ioscs: ioscs,
      moren: moren,
      getEnterpriseInfoUrl: '/api/enterprise/get',
      list: '/api/evaluate/list',
      enterpriseInfo: {
        enterpriseId: null,
        registerName: '',
        score: 0,
        qualityScore: 0,
        standardScore: 0,
        experienceScore: 0,
        address: '',
        mobile: '',
        industryCategory: '',
        posLon: null,
        posLat: null,
      },
      total: 0,
      evaluateList: [],
      loading: false,
      finished: false,
      pageNumber: 0,
    }
  },
  components: {
    Dialog,
    ImagePreview
  },
  mounted() {
    // 获取企业基本信息
    let id = this.getQueryString("id") ?? this.$store.getters.getEnterpriseId;
    if (id === null) {
      Dialog.alert({ message: '企业id不存在' })
      return
    }
    this.enterpriseInfo.enterpriseId = id
    this.getList()
    // 获取企业信息
    this.$axios({
      method: 'get',
      changeOrigin: true, // 这个参数代表发送跨域请求
      url: this.getEnterpriseInfoUrl,
      //注意：post方式提交 参数这里可以写data get方式提交用params
      params: {
        enterpriseId: id,
      },
    }).then(response => {
      if (response.data.ret === 1) {
        Dialog.alert({ message: response.data.msg ?? '企业信息查询失败' })
        return
      }
      this.enterpriseInfo = response.data.data
      if (
        this.enterpriseInfo.score === ''
      ) {
        this.enterpriseInfo.score = null
      }
    })
  },
  methods: {
    getQueryString(name) {
        let reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
        let r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    },
    toRating() {},
      isShow(item) {
          let dom = document.createElement('div');
          dom.setAttribute("class", "red_tesx")
          dom.textContent = item;
          document.body.appendChild(dom)
          let height = dom.clientHeight
          document.body.removeChild(dom)
          return height > 100;
      },
    addPrefix(url) {
      if (
        url === null ||
        !url.startsWith('https://') ||
        !url.startsWith('http://')
      ) {
        return 'https://' + url
      }
      return url
    },
    async getList() {
      // 列表
      let result = await this.$axios({
        method: 'get',
        changeOrigin: true, // 这个参数代表发送跨域请求
        url: this.list,
        //注意：post方式提交 参数这里可以写data get方式提交用params
        params: {
          enterpriseId: this.enterpriseInfo.enterpriseId,
          pageSize: 10,
          pageNumber: this.pageNumber,
        },
      })
      if (result.data.data.ret === 1) {
        Dialog.alert({ message: result.data.data.msg ?? '评价信息查询失败' })
        return
      }
      //图片处理
      let _this = this
      result.data.data.content.forEach(e => {
          e.photoUrls = e.photoUrls.map(img => _this.addPrefix(img));
          e.isOpen = true;
      })
      this.evaluateList = this.evaluateList.concat(result.data.data.content)
      this.total = result.data.data.totalElements
      this.finished = result.data.data.last
      this.loading = false
    },
    formatDate(time) {
      let date = new Date(time.replace("T"," ").replace("+0800","").replace(/-/g,'/'))
      return formatDate(date, 'yyyy-MM-dd')
    },
    onLoad() {
      this.pageNumber++
      this.loading = true
      this.getList()
    },
      imagePreview(imgs, index) {
          ImagePreview({
              images: imgs,
              startPosition: index,
              closeable: true
          });
      }
  },
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

#app1 {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left !important;
  color: #2c3e50;
  margin-top: 0px !important;
}

.contents {
  width: 100%;
  height: 100%;
  /* background-color: #F7F7F7; */
}
.send_titles {
  width: 100%;
  margin-top: 1.5rem;
  font-weight: 500;
  height: auto;
  background-color: #ffffff;
}

.send_title_texts {
  /* height: 1.5rem; */
  font-weight: 600;
  font-size: 1rem;
  margin-left: 24px;
  font-family: PingFangSC-Semibold, PingFang SC;
}

.send_title_titels {
  width: 100%;
  height: 1rem;
}

.send_title_titel_1s {
  margin-top: 0.25rem;
  float: left;
}

.send_title_titel_2s {
  margin-top: 0.3rem;
  margin-left: 4px;
  float: left;
  font-size: 0.875rem;
  font-family: PingFangSC-Light, PingFang SC;
  color: #999999;
}

.reads_contents {
  /* width: 95%; */
  height: 5.5rem;
  margin: 12px;
  margin-top: 1.5rem;
  background: #f7f7f7;
}

.reads_content_titel_2s {
  float: left;
  margin-left: 1.5rem;
  margin-top: 1.1875rem;
}

.reads_content_titel_1s {
  float: left;
  font-size: 1.5rem;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #333333;
  margin-left: 0.875rem;
  margin-top: 1rem;
}

.rest_tests {
  margin-left: 0.875rem;
  width: 90%;
  /* position: relative; */
  float: left;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  font-size: 0.875rem;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: #999999;
}

.dress_contents {
  /* width: 100%; */
  margin: 24px;
  height: auto;
}

.dress_tests {
  width: 80%;
  height: 1rem;
  color: #999999;
  font-size: 0.75rem;
  font-weight: 300;
  font-family: PingFangSC-Light, PingFang SC;
  /* margin-left: 0.875rem; */
  margin-top: 1.5rem;
}

.dress_urls {
  /* width: 80%; */
  text-align: left;
  vertical-align: text-top;
  height: auto;
  color: #333333;
  font-size: 1rem;
  /* margin-left: 0.875rem; */
  margin-top: 0.5rem;
  line-height: 150%;
  font-family: PingFangSC-Light, PingFang SC;
}

.call_contents {
  height: 4.0625rem;
  width: 100%;
}

.call_content_titel_1s {
  margin-left: 1.5rem;
  margin-top: 0.8rem;
  float: left;
}

.call_content_titel_2s {
  margin-top: 1.125rem;
  margin-left: 4px;
  float: left;
  font-size: 1rem;
  font-weight: 400;
  font-family: PingFangSC-Light, PingFang SC;
  color: #666666;
}

.read_peops {
  width: 100%;
  height: auto;
}

.title_reads {
  width: 80%;
  height: 3rem;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  margin-top: 1.5rem;
  margin-left: 1.5rem;
}

.credit_conents {
  /* background: #1989FA; */
  width: 100%;
  height: auto;
}

.conetet_Rings {
  /* margin-top:1.5rem ; */
  width: 100%;
  height: 2.5rem;
}

.people_rings {
  width: 5%;
  /* background: #0570DB; */
  float: left;
  width: 3.2rem;
  height: 100%;
}

.name_tests {
  /* background: #07C160; */
  float: left;
  margin-left: 8px;
  width: 50%;
  height: 100%;
}

.name_test_te1s {
  width: 100%;
  /* height: 50%; */
  /* margin-left: 8px; */
  color: #333333;
  font-size: 0.75rem;
  font-family: AlNile;
}
.red_tesx {
  text-align: left;
  vertical-align: text-top;
  line-height: 150%;
  word-break: break-all;
  white-space: normal;
  margin-left: 1.5rem;
  margin-right: 1.5rem;
  margin-top: 12px;
  margin-bottom: 12px;
  font-family: PingFangSC-Light, PingFang SC;
  font-weight: 300;
  color: rgba(51, 51, 51, 1);
  font-size: 0.875rem;
  height: auto;
}
.date_critds {
  float: right;
  /* margin-bottom: 24px; */
  width: 30%;
  height: 100%;
  font-size: 0.625rem;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 300;
  color: rgba(102, 102, 102, 1);
  /* background-color:blue; */
}
</style>
