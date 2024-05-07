<template>
  <div class="Ace">
    <div class="code_tool_bar">
      <span style="position: relative;left: 10px; top: 16px; font-size: 18px;">请在这里进行调试代码</span>
      <el-button icon="el-icon-s-tools" @click="dialogTableVisible = true" style="float: right;margin: 10px;"></el-button>
      <el-button icon="el-icon-refresh" @click="refresh_code" style="float: right; margin: 10px;"></el-button>
      <el-select v-model="selectLanguageValue" @change="changSelectValue1" filterable style="float: right;margin: 10px;">
        <el-option
          v-for="item in languagesOptions"
          :key="item.label"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-dialog :visible.sync="dialogTableVisible"
                 :append-to-body="true"
                 top="40px"
                 width="39.194%"
                 :destroy-on-close="true"
                 :show-close="false"
                 custom-class="code-editor-config-dialog">
        <el-card style="margin: -59px -20px 0 -20px;"
                 shadow="never">
          <div slot="header" class="clearfix">
            <span>代码编辑器设置</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="dialogTableVisible = false">x</el-button>
          </div>
          <div class="row">
            <el-row>
              <el-col :span="16">
                <div class="code-editor-option-title">主题</div>
                <div class="code-editor-option-description">对白色界面感到厌倦了吗？可以尝试其他的背景和代码高亮风格。</div>
              </el-col>
              <el-col :span="8">
                <el-select v-model="selectThemeValue"
                           @change="changSelectValue"
                           filterable>
                  <el-option
                    v-for="item in themesOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
            </el-row>
            <hr>
          </div>
          <div class="row">
            <el-row>
              <el-col :span="16">
                <div class="code-editor-option-title">编辑类型</div>
                <div class="code-editor-option-description">更喜欢Vim或者Emacs的输入方式吗？我们也为你提供了这些选项。</div>
              </el-col>
              <el-col :span="8">
                <el-select v-model="selectEditorValue"
                           @change="setEditorMode"
                           filterable>
                  <el-option
                    v-for="item in editorOption"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
            </el-row>
            <hr>
          </div>
          <div class="row">
            <el-row>
              <el-col :span="16">
                <div class="code-editor-option-title">缩进长度</div>
                <div class="code-editor-option-description">选择代码缩进的长度。默认是4个空格。</div>
              </el-col>
              <el-col :span="8">
                <el-select v-model="selectTabValue"
                           @change="setTabSize"
                           filterable>
                  <el-option
                    v-for="item in tabOption"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
            </el-row>
            <hr>
          </div>
          <div class="row">
            <el-row>
              <el-col :span="16">
                <div class="code-editor-option-title">主题</div>
                <div class="code-editor-option-description">对白色界面感到厌倦了吗？可以尝试其他的背景和代码高亮风格。</div>
              </el-col>
              <el-col :span="8">
                <el-select v-model="selectThemeValue"
                           @change="changSelectValue"
                           filterable>
                  <el-option
                    v-for="item in themesOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
            </el-row>
          </div>
        </el-card>
        <el-button @click="dialogTableVisible = false" style="margin: 20px 480px -12px 480px">确 定</el-button>
      </el-dialog>
    </div>
    <div ref="ace" class="ace"></div>
    <el-button class="debuggerBtn" round @click="debuggerCode" ><i class="fa fa-play-circle-o" aria-hidden="true"></i>调试代码</el-button>
    <div>
      <el-input
        type="textarea"
        :autosize="{ minRows: 4, maxRows: 8}"
        v-model="codeResult"></el-input>
    </div>
  </div>
</template>

<script>
import ace from 'ace-builds'
import 'ace-builds/webpack-resolver'; // 在 webpack 环境中使用必须要导入
//解决添加提示时控制台警告(提示必须引入的包)
import "ace-builds/src-noconflict/ext-language_tools"
import "ace-builds/src-noconflict/ext-emmet"
//语言提示
import 'ace-builds/src-noconflict/snippets/javascript'
import 'ace-builds/src-noconflict/snippets/c_cpp'
import 'ace-builds/src-noconflict/snippets/java'
import 'ace-builds/src-noconflict/snippets/golang'
import 'ace-builds/src-noconflict/snippets/python'
//输入类型
import 'ace-builds/src-noconflict/keybinding-emacs'
import 'ace-builds/src-noconflict/keybinding-vim'
import 'ace-builds/src-noconflict/keybinding-vscode'

export default {
  name: 'CodeEditor',
  methods: {
    changSelectValue(value) {
      this.aceEditor.setTheme(`ace/theme/${value}`);
    },
    refresh_code() {
      this.aceEditor.session.setValue('');
    },
    changSelectValue1(value) {
      this.aceEditor.session.setMode(`ace/mode/${value}`);
    },
    debuggerCode() {
      console.log(this.selectLanguageValue)
      this.codeResult = null
      let value = this.aceEditor.session.getValue();
      let formData = new FormData;
      formData.append('code',value)
      if (this.selectLanguageValue == 'java'){
        this.$axios.post(`/api/javaComplier`,formData).then(res => {
          this.codeResult = res.data.data
        }).catch(error => {
          console.log(error)
        })
      }
      if (this.selectLanguageValue == 'c'){
        console.log("进入c")
        this.codeResult = null
        this.$axios.post(`/api/cComplier`,formData).then(res => {
          console.log(this.codeResult)
          this.codeResult = res.data.data
        }).catch(error => {
          console.log(error)
        })
      }
      if (this.selectLanguageValue == 'c++'){
        console.log("进入c++")
        this.codeResult = null
        this.$axios.post(`/api/cpComplier`,formData).then(res => {
          console.log(this.codeResult)
          this.codeResult = res.data.data
        }).catch(error => {
          console.log(error)
        })
      }
      console.log(value);
    },
    setTabSize(size) {
      this.aceEditor.session.setTabSize(size);
    },
    setEditorMode(value) {
      this.aceEditor.setKeyboardHandler(`ace/keyboard/${value}`);
    }
  },
  data() {
    return {
      codeResult: null,
      dialogTableVisible: false,
      themeValue: 'ambiance',
      aceEditor: null,
      themePath: 'ace/theme/crimson_editor', // 不导入 webpack-resolver，该模块路径会报错
      modePath: 'ace/mode/c_cpp', // 同上
      codeValue: this.value || '',
      editorOption:[{
        value: 'vscode',
        label: 'Standard'
      },{
        value: 'vim',
        label: 'Vim'
      },{
        value: 'emacs',
        label: 'Emacs'
      }],
      selectEditorValue: 'Standard',
      tabOption:[{
        value: '2',
        label: '2个空格'
      },{
        value: '4',
        label: '4个空格'
      },{
        value: '6',
        label: '6个空格'
      }],
      selectTabValue: '4个空格',
      themesOptions: [{
        value: 'crimson_editor',
        label: 'CrimsonEditor'
      },{
        value: 'monokai',
        label: 'Monokai'
      },{
        value: 'terminal',
        label: 'Terminal'
      },{
        value: 'xcode',
        label: 'Xcode'
      }],
      selectThemeValue: 'CrimsonEditor',
      languagesOptions: [{
        value: 'c++',
        label: 'C++'
      },{
        value: 'c',
        label: 'C'
      },{
        value: 'java',
        label: 'Java'
      },{
        value: 'python',
        label: 'Python'
      }],
      selectLanguageValue: 'c++'
    };
  },
  mounted() {
    this.aceEditor = ace.edit(this.$refs.ace,{
      maxLines: 1000, // 最大行数，超过会自动出现滚动条
      minLines: 22, // 最小行数，还未到最大行数时，编辑器会自动伸缩大小
      fontSize: 14, // 编辑器内字体大小
      theme: this.themePath, // 默认设置的主题
      mode: this.modePath, // 默认设置的语言模式
      tabSize: 4, // 制表符设置为 4 个空格大小
      readOnly: false, //只读
      highlightActiveLine: true,
      value: this.codeValue
    });
    this.aceEditor.setOptions({
      enableSnippets: true,
      enableLiveAutocompletion: true,
      enableBasicAutocompletion: true
    });
    // 快捷键
    // this.aceEditor.commands.addCommand({
    //   name: 'myCommand',
    //   bindKey: {win: 'Ctrl-M',  mac: 'Command-M'},
    //   exec: function(editor) {
    //     //...
    //   },
    //   readOnly: true // false if this command should not apply in readOnly mode
    // });
  },
  watch: {
    value(newVal) {
      console.log(newVal);
      this.aceEditor.setValue(newVal);
    }
  }
};
</script>

<style scoped lang="scss">
.code_tool_bar {
  height: 60px;
  width: 100%;
  background: #f8f9fa;
  border: 1px solid #c2c7d0;
  margin-bottom: 0;
}

.code-editor-option-title {
  font-size: 17px;
  margin-bottom: 10px;
}

.code-editor-option-description {
  font-size: 13px;
  color: grey;
}

hr {
  background: none !important;
  height: 1px !important;
  border: 0 !important;
  border-top: 1px solid #eee !important;
  margin-top: 20px;
  margin-bottom: 20px;
}

.ace {
  position: relative !important;
  border: 1px solid lightgray;
  margin: auto;
  height: auto;
  width: 100%;
}
.debuggerBtn {
  float: right;
  margin: 13px 20px 0 0;
}
</style>
