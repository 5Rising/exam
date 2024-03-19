import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'login', //登录界面
      component: () => import('@/components/common/login'),
      meta: {
        needLogin: false
      }
    },
    {
      path: '/index', //教师主页
      component: () => import('@/components/admin/index'),
      meta: {
        needLogin: true
      },
      children: [
        {
          path: '/', //首页默认路由
          component: () => import('@/components/common/hello'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/grade', //学生成绩
          component: () => import('@/components/charts/grade'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/selectExamToPart', //学生分数段
          component: () => import('@/components/teacher/selectExamToPart'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/scorePart',
          component: () => import('@/components/charts/scorePart'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/allStudentsGrade', //所有学生成绩统计
          component: () => import('@/components/teacher/allStudentsGrade'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/examDescription', //考试管理功能描述
          component: () => import('@/components/teacher/examDescription'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/selectExam', //查询所有考试
          component: () => import('@/components/teacher/selectExam'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/addExam', //添加考试
          component: () => import('@/components/teacher/addExam'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/answerDescription', //题库管理功能介绍
          component: () => import('@/components/teacher/answerDescription'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/selectAnswer', //查询所有题库
          component: () => import('@/components/teacher/selectAnswer'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/addAnswer', //增加题库主界面
          component: () => import('@/components/teacher/addAnswer'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/addAnswerChildren', //点击试卷跳转到添加题库页面
          component: () => import('@/components/teacher/addAnswerChildren'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/studentManage', //学生管理界面
          component: () => import('@/components/teacher/studentManage'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/addStudent', //添加学生
          component: () => import('@/components/teacher/addStudent'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/teacherManage',
          component: () => import('@/components/admin/tacherManage'),
          meta: {
            needLogin: true
          }
        },
        {
          path: '/addTeacher',
          component: () => import ('@/components/admin/addTeacher'),
          meta: {
            needLogin: true
          }
        }
      ]
    },
    {
      path: '/student',
      component: () => import('@/components/student/index'),
      meta: {
        needLogin: true
      },
      children: [
        {path: "/", component: () => import('@/components/student/myExam'),
          meta: {
            needLogin: true
          }},
        {path: '/practice', component: () => import('@/components/student/practice'),
          meta: {
            needLogin: true
          }},
        {path: '/manager', component: () => import('@/components/student/manager'),
          meta: {
            needLogin: true
          }},
        {path: '/examMsg', component: () => import('@/components/student/examMsg'),
          meta: {
            needLogin: true
          }},
        {path: '/message', component: () => import('@/components/student/message'),
          meta: {
            needLogin: true
          }},
        {path: '/studentScore', component: () => import("@/components/student/answerScore"),
          meta: {
            needLogin: true
          }},
        {path: '/scoreTable', component: () => import("@/components/student/scoreTable"),
          meta: {
            needLogin: true
          }}
      ]
    },
    {
      path: '/answer', component: () => import('@/components/student/answer'),
      meta: {
        needLogin: true
      },
      children: [
        {path: '/', component: () => import('@/components/student/code'),
          meta: {
            needLogin: true
          }}
      ]
    }
  ]
})

export default router;
