<!-- 添加考试 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="试卷名称" prop="source" required>
        <el-input v-model="form.source"></el-input>
      </el-form-item>
      <el-form-item label="介绍" prop="description" required>
        <el-input v-model="form.description"></el-input>
      </el-form-item>
      <el-form-item label="所属学院" prop="institute" required>
        <el-select v-model="form.institute" filterable placeholder="请选择">
          <el-option
            v-for="item in form.optionsInstitute"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属专业" prop="major" required>
        <el-select v-model="form.major" filterable placeholder="请选择">
          <el-option
            v-for="item in form.optionsMajor"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="年级" prop="grade" required>
        <el-select v-model="form.grade" placeholder="请选择年级">
          <el-option label="大一" value="大一"></el-option>
          <el-option label="大二" value="大二"></el-option>
          <el-option label="大三" value="大三"></el-option>
          <el-option label="大四" value="大四"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学期" prop="term" required>
        <el-select v-model="form.term" placeholder="请选择学期">
          <el-option label="上学期" value="1"></el-option>
          <el-option label="下学期" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="考试类型" prop="type" required>
        <el-select v-model="form.type" placeholder="请选择考试类型">
          <el-option label="期末考试" value="期末考试"></el-option>
          <el-option label="测试" value="测试"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="考试日期" prop="examDate" required>
          <el-date-picker placeholder="选择日期" v-model="form.examDate" type="datetimerange" start-placeholder="开始时间" end-placeholder="结束时间" :default-time="['12:00:00']" style="width: 400px;"></el-date-picker>
      </el-form-item>
      <el-form-item label="总分" prop="totalScore" required>
        <el-input v-model="form.totalScore"></el-input>
      </el-form-item>
      <el-form-item label="考生提示" prop="tips" required>
        <el-input type="textarea" v-model="form.tips"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
        <el-button type="text" @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      form: { //表单数据初始化
        source: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        term: null,
        examDate: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
        optionsInstitute: [{
            value: '全部学院',
            label: '全部学院'
          }, {
          value: '计算机与信息学院',
          label: '计算机与信息学院'
        }, {
          value: '电子信息学院',
          label: '电子信息学院'
        }, {
          value: '商学院',
          label: '商学院'
        }, {
          value: '外国语学院',
          label: '外国语学院'
        }, {
          value: '化学化工学院',
          label: '化学化工学院'
        }],
        optionsMajor: [{
          value: '计算机科学技术',
          label: '计算机科学技术'
        }, {
          value: '软件工程',
          label: '软件工程'
        }, {
          value: '数学',
          label: '数学'
        }, {
          value: '汉语言',
          label: '汉语言'
        }, {
          value: '英语',
          label: '英语'
        }, {
          value: '化学',
          label: '化学'
        }],
      },
      rules:{

      }
    };
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
    onSubmit(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          // alert('submit!');
          let examDateEnd =''
          for (let i = 0; i < this.form.examDate.length; i++) {
            let examDate = this.formatTime(this.form.examDate[i])
            examDateEnd += examDate + ' '
          }
          this.form.examDate = examDateEnd
          console.log(this.form.examDate)
          this.$axios(`/api/examManagePaperId`).then(res => {
            this.form.paperId = res.data.data.paperId + 1 //实现paperId自增1
            this.$axios({
              url: '/api/exam',
              method: 'post',
              data: {
                ...this.form
              }
            }).then(res => {
              if(res.data.code == 200) {
                this.$message({
                  message: '数据添加成功',
                  type: 'success'
                })
                this.$router.push({path: '/selectExam'})
              }
            })
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    cancel() { //取消按钮
      this.form = {}
    },

  }
};
</script>
<style lang="scss" scoped>
.add {
  padding: 0px 40px;
  width: 400px;
}
</style>

