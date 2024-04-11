<template>

  <el-result subTitle= "本次考试分数" >
    <template slot="icon">
      <h1>{{this.score}}</h1>
    </template>
    <template slot="extra">
      <h3>开始时间：{{this.startTime}}</h3>
      <h3>结束时间：{{this.endTime}}</h3>
      <el-button type="primary" size="medium" @click="exit">退出</el-button>
    </template>
  </el-result>

</template>

<script>
import * as auth from '../../utiles/auth'
export default {
  data() {
    return {
      score: 0, //总分
      imgShow: false, //不及格图片显示
      startTime: null, //考试开始时间
      endTime: null, //考试结束时间
      totalScore: 0,
      subject : null
    }
  },
  created() {
    this.getScore()
    auth.removeAdminInfo()
  },
  methods: {
    exit() {  //退出登录
      auth.removeAdminInfo()
      this.$cookies.remove("cname") //清除cookie
      this.$cookies.remove("cid")
      this.$router.push({path:"/"}) //跳转到登录页面
    },
    getScore() {
      let subject = this.$route.query.subject
      let score = this.$route.query.score
      let startTime = this.$route.query.startTime
      let endTime = this.$route.query.endTime
      let totalScore = this.$route.query.totalScore
      for (let i = 0; i < totalScore.length; i++) {
        this.totalScore += totalScore[i]
      }
      console.log(totalScore)
      console.log(subject)
      this.score = score
      this.startTime = startTime
      this.endTime = endTime
      this.subject = subject
    }
  }
}
</script>

<style lang="scss" scoped>
.show {
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    width: 160px;
    height: 160px;
  }
  .img1Transform {
    opacity: 1 !important;
    transform: translateX(30px) !important;
    transition: all 0.6s ease !important;
  }
  .img2Transform {
    opacity: 1 !important;
    transform: translateX(-30px) !important;
    transition: all 0.6s ease !important;
  }
  .img1 {
    margin-top: 70px;
    opacity: 0;
    transform: translateX(0px);
    transition: all 0.6s ease;
  }
  .img2 {
    margin-top: 30px;
    opacity: 0;
    transform: translateX(0px);
    transition: all 0.6s ease;
  }
}
.time {
  padding: 0px 70px;
  li {
    display: flex;
    justify-content: space-around;
    padding: 10px;
    margin: 20px 0px;
  }
  li:nth-child(1) {
    background-color: white;
  }
  li:nth-child(2) {
    background-color: white;
  }
}
//.border {
//  border: 6px solid #36aafd !important;
//  transition: all 2s ease;
//  width: 160px !important;
//  height: 160px !important;
//  transform: rotate(360deg) !important;
//  opacity: 1 !important;
//}
.score {
  max-width: 800px;
  margin: 0 auto;
  .title {
    margin: 60px 0px 30px 0px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    .name {
      font-size: 26px;
      color: inherit;
      font-weight: 500;
    }
    .description {
      font-size: 14px;
      color: #888;
    }
  }
  .total {
    border: 1px solid #dbdbdb;
    background-color: #fff;
    padding: 40px;
    .look {
      border-bottom: 1px solid #dbdbdb;
      padding: 0px 0px 14px 14px;
      color: #36aafd;
    }
    .number {
      opacity: 0;
      border: 6px solid #fff;
      transform: rotate(0deg);
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      margin: 0 auto;
      width: 160px;
      height: 160px;
      border-radius: 50%;
      margin-top: 80px;
      margin-bottom: 20px;
      transition: all 1s ease;

      span:nth-child(1) {
        font-size: 36px;
        font-weight: 600;
      }
      span:nth-child(2) {
        font-size: 14px;
      }
    }
  }
}
</style>

