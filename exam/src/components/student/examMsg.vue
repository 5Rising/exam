// 点击试卷后的缩略信息
<template>
<div class="examMsg">
  <el-descriptions title="考试信息">
    <el-descriptions-item label="科目名称">{{examData.source}}</el-descriptions-item>
    <el-descriptions-item label="总分">100</el-descriptions-item>
    <el-descriptions-item label="考试时间">{{examData.examDate}}</el-descriptions-item>
    <el-descriptions-item label="考生须知">
      <el-tag size="big">{{examData.tips}}</el-tag>
      <el-tag size="big">{{examData.type}}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="所属学院">{{examData.institute}}</el-descriptions-item>
    <el-descriptions-item label="考试地点">黔南民族师范学院</el-descriptions-item>
    <el-descriptions-item label="">
      <el-button size="big" @click="toAnswer(examData.examCode)" v-if="isDateValid">开始答题</el-button>
      <el-tag color="" v-else>不在考试时间范围内或者已经提交过试卷</el-tag>
    </el-descriptions-item>
  </el-descriptions>
</div>
<!--  <div id="msg">-->
<!--    <div class="title">-->
<!--      <span>试卷列表</span>-->
<!--      <span>/  {{examData.source}}</span>-->
<!--    </div>-->
<!--    <div class="wrapper">-->
<!--      <ul class="top">-->
<!--        <li class="example">{{examData.source}}</li>-->
<!--        <li><i class="iconfont icon-pen-"></i></li>-->
<!--        <li><i class="iconfont icon-share"></i></li>-->
<!--        <li class="right">-->
<!--          <div>-->
<!--            <span class="count">总分</span>-->
<!--            <span class="score">{{score[0]+score[1]+score[2]+score[3]+score[4]}}</span>-->
<!--          </div>-->
<!--        </li>-->
<!--      </ul>-->
<!--      <ul class="bottom">-->
<!--        <li>{{examData.examDate}}</li>-->
<!--        <li>来自 {{examData.institute}}</li>-->
<!--        <li class="btn">{{examData.type}}</li>-->
<!--        <li class="right" v-if="isDateValid"><el-button @click="toAnswer(examData.examCode)">开始答题</el-button></li>-->
<!--        <li class="right" v-else><p>不在考试时间范围内或者已提交试卷</p></li>-->
<!--      </ul>-->
<!--    </div>-->
<!--    <el-dialog-->
<!--      title="考生须知"-->
<!--      :visible.sync="dialogVisible"-->
<!--      width="30%">-->
<!--      <span>{{examData.tips}}</span>-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--        <el-button @click="dialogVisible = false">知道了</el-button>-->
<!--      </span>-->
<!--    </el-dialog>-->

<!--  </div>-->
</template>

<script>
import {formatTime} from "echarts/lib/util/format";
import {command} from "node-notifier/lib/utils";
import {isDate} from "vis/lib/util";

export default {
  data() {
    return {
      answered: false,
      dialogVisible: false, //对话框属性
      activeName: '0',  //默认打开序号
      topicCount: [],//每种类型题目的总数
      score: [],  //每种类型分数的总数
      examData: { //考试信息
        // source: null,
        // totalScore: null,
      },
      topic: {  //试卷信息

      },
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    formatTime(date) { //日期格式化
      let year = date.getFullYear()
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },

    //初始化页面数据
    init() {
      // console.log(this.addedDate)
      let examCode = this.$route.query.examCode //获取路由传递过来的试卷编号
      let studentId = this.$cookies.get('cid')
      console.log(studentId)
      this.$axios(`/api/exam/${examCode}`).then(res => {  //通过examCode请求试卷详细信息
        this.examData = { ...res.data.data}
        let paperId = this.examData.paperId
        this.$axios(`/api/paper/${paperId}`).then(res => {  //通过paperId获取试题题目信息
          this.$axios(`/api/scores/${examCode}/${studentId}`).then(res => {
            console.log(res.data.data.length)
            console.log(res.data)
            console.log(examCode)
            if (res.data.data.length == 0){
              console.log(this.answered)
              this.answered = true
            }
          })
          this.topic = {...res.data}
          console.log('topic')
          console.log(this.topic)
          let keys = Object.keys(this.topic) //对象转数组
          keys.forEach(e => {
            let data = this.topic[e]
            this.topicCount.push(data.length)
            let currentScore = 0
            for(let i = 0; i< data.length; i++) { //循环每种题型,计算出总分
              currentScore += data[i].score
            }
            this.score.push(currentScore) //把每种题型总分存入score
          })
        })
      })
    },
    toAnswer(id) {
      this.$router.push({path:"/answer",query:{examCode: id}})
    },
  },
  computed: {
    isDateValid() {
      if (this.examData.examDate){
        let startTime = this.examData.examDate.substring(0,19)
        let endTime = this.examData.examDate.substring(20,39)
        console.log(startTime)
        console.log(endTime)
        console.log(this.formatTime(new Date()))
        return (this.formatTime(new Date())>startTime && this.formatTime(new Date())<endTime) && this.answered
      }

    }
  }
}
</script>

<style lang="scss" scoped>
.examMsg{
  margin: 0 auto;
  width: 80%;

}
</style>
