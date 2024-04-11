<template>
  <div class="test2" style="width:600px;height:400px;">
    <div v-if="isNull">
      <h1>该门考试还没人参考哦,请提醒学生参加考试。</h1>
    </div>
    <div id="myChart4" style="width:100%;height:250px;float:left;" ref="box"></div>
  </div>
</template>

<script>
export default {
  name: 'test2',
  data () {
    return {
      name: null,
      isNull: false,
      A: 0,
      B: 0,
      C: 0,
      D: 0,
      E: 0,
      opinion: ['90分以上', '80-90分','70-79分','60-69分','60分以下'],
    }
  },
  mounted: function () {
    this.drawLine4()
  },
  methods: {
    drawLine4 () {
      let examCode = this.$route.query.examCode
      this.name = this.$route.query.source
      this.$axios(`/api/scores/${examCode}`).then(res => {
        let data = res.data.data
        if(data.length > 0) {
          let box = this.$refs['box']
          let charts = this.$echarts.init(box)
          data.forEach(element => {
            console.log(element.etScore)
            let A = this.A
            let B = this.B
            let C = this.C
            let D = this.D
            let E = this.E
            switch(Math.floor(element.etScore / 10)) {
              case 10:
              case 9:
                this.A = A + 1
                break
              case 8:
                this.B = B + 1
                break
              case 7:
                this.C = C + 1
                break
              case 6:
                this.D = D + 1
                break
              default:
                this.E = E + 1
            }
          });
          console.log(this.A)
          console.log(this.B)
          console.log(this.C)
          console.log(this.D)
          console.log(this.E)
          let option = {
            title: {
              text: this.name+"分数段直方图"
            },
            tooltip: {
              trigger: 'item',
              formatter: '{a} <br/>{b} : {c}%'
            },
            xAxis: {
              data: this.opinion
            },
            yAxis: {},
            series: [{
              name: '评估概率',
              type: 'bar',
              data:  [
                {value:this.A, name:'90分及以上'},
                {value:this.B, name:'80-89分'},
                {value:this.C, name:'70-79分'},
                {value:this.D, name:'60-69分'},
                {value:this.E, name:'60分以下'}
              ],
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                },
                color: function (params) {
                  // 自定义颜色
                  var colorList = ['#1ab394', 'red']
                  return colorList[params.dataIndex]
                }
              }
            }]
          };
          charts.setOption(option)
        }else{
          this.isNull = true
        }
      })
    }
  }
}
</script>
