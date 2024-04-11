<!-- 添加教师 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名" prop="teacherName" required>
            <el-input v-model="form.teacherName"></el-input>
          </el-form-item>
          <el-form-item label="学院" prop="institute" required>
            <el-input v-model="form.institute"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex" required>
            <el-input v-model="form.sex"></el-input>
          </el-form-item>
          <el-form-item label="电话号码" prop="tel" required>
            <el-input v-model="form.tel"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pwd" required>
            <el-input v-model="form.pwd"></el-input>
          </el-form-item>
        <el-form-item label="邮箱" prop="email" required>
          <el-input v-model="form.email"></el-input>
        </el-form-item>
          <el-form-item label="身份证号" prop="cardid" required>
            <el-input v-model="form.cardId"></el-input>
          </el-form-item>
          <el-form-item label="职称" prop="type" required>
            <el-input v-model="form.type"></el-input>
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
        teacherName: null,
        institute: null,
        tel: null,
        email: null,
        pwd: null,
        cardId: null,
        sex: null,
        role: 1,
        type:null,
        rules:{

        }
      }
    };
  },
  methods: {

    onSubmit(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.$axios({
            url: '/api/teacher',
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
              this.$router.push({path: '/teacherManage'})
            }
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

