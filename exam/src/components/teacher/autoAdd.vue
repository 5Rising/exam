<template>

  <div class="demo-input-size">
    <el-input
      suffix-icon="el-icon-date"
      v-model="changeNumber">
    </el-input>
    <el-input
      placeholder="请输入填空题数量"
      suffix-icon="el-icon-date"
      v-model="fillNumber">
    </el-input>
    <el-input
      placeholder="请输入判断题数量"
      suffix-icon="el-icon-date"
      v-model="judgeNumber">
    </el-input>
    <el-input
      placeholder="请输入简答题数量"
      suffix-icon="el-icon-date"
      v-model="shortanswerNumber">
    </el-input>
    <el-input
      placeholder="请输入代码题题数量"
      suffix-icon="el-icon-date"
      v-model="codeNumber">
    </el-input>
    <el-button type="primary" @click="create()">立即组卷</el-button>
  </div>



</template>

<script>
export default {
  data() {
    return {
      changeNumber: null, //选择题出题数量
      fillNumber: null, //填空题出题数量
      judgeNumber: null, //判断题出题数量
      shortanswerNumber:null, //简答题出题数量
      codeNumber:null,  //代码题出题数量
      paperId: null,
      subject: null,
    }
  },

  created() {
    this.getParams()
  },

  methods:{
    create() {
      if (this.changeNumber!=null){
        this.$axios({
          url: '/api/item',
          method: 'post',
          data: {
            changeNumber: this.changeNumber,
            fillNumber: this.fillNumber,
            judgeNumber: this.judgeNumber,
            shortanswerNumber: this.shortanswerNumber,
            codeNumber: this.codeNumber,
            paperId: this.paperId,
            subject: this.$route.query.subject
          }
        }).then(res => {
          console.log(res)
          console.log(this.codeNumber)
          let data = res.data
          if(data.code==200){
            setTimeout(() => {
              this.$router.push({path: '/selectAnswer'})
            },1000)
            this.$message({
              message: data.message,
              type: 'success'
            })
          }else if(data.code==400){
            this.$message({
              message: data.message,
              type: 'error'
            })
          }else if (data.code==500){
            this.$message({
              message: '程序貌似崩溃了',
              type: 'error'
            })
          }

        })
      }else {
        this.$message({
          message:'请输入完整信息'
        })
      }

    },
    getParams() {
      let subject = this.$route.query.subject //获取试卷名称
      let paperId = this.$route.query.paperId //获取paperId
      console.log(subject)
      console.log(paperId)
      this.paperId = paperId
      this.subject = subject
    },
  }
}
</script>

<style scoped>

</style>
