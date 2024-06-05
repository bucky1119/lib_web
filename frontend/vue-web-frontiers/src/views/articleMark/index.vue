<template>
  <div class="app-container" style="background: #f2f2e4;    height: 100vh;">
    <!-- 价值度分值展示 -->
    <div class="block">
      <div style="display: flex;align-items: center;">
        <div v-if="value" class="value-label">价值度</div>
        <div class="value">{{value?value:'暂无评分'}}</div>
      </div>
      <!--  -->
    </div>
    <el-row :gutter="20">
      <!-- 评分记录 -->
      <el-col :span="9">
        <div class="title-part">评分记录</div>
        <div style="height: 500px; display: flex;">
          <el-scrollbar wrap-class="scrollbar-wrapper" style="width: 80%; height: 100%; ">
            <!-- <div style="height: 500px;"> -->
            <!-- <div>价值指标</div>
        <el-rate v-if="value" v-model="value" disabled text-color="#ff9900" :max=" 10" /> -->

            <div v-if="valueList.length">
              <div v-for="(item, index) in valueList " :key="index">
                <div class="user-info">
                  <div class="logo"> </div>
                  <div class="username">{{item.userName}}</div>
                </div>
                <div class="value-part" v-for="(rate,ind) in item.rateValue" :key="ind">
                  <div style="color: #9a9a96;">{{rate.label}}</div>
                  <el-slider class="read-slider" v-model="rate.value" :max=10 disabled />

                </div>
              </div>
            </div>
            <div v-else class="value">暂无记录</div>
            <!-- </div> -->
          </el-scrollbar>
        </div>

      </el-col>

      <!-- 文章评分 -->
      <el-col :span="15">
        <div class="rate-part">
          <div class="title-part" style="cursor: pointer;" @click="handleRateSetting">
            <span style="margin-right: 18px;">文章评分</span>
            <i class="el-icon-setting"></i>
          </div>
          <div class="label">创新度</div>
          <el-slider class="write-slider" v-model="rateForm.create_tag" :max=10 :min="1" />
          <div class="label">颠覆性</div>
          <el-slider class="write-slider" v-model="rateForm.overturn_tag" :max=10 :min="1" />
          <div class="label">前沿性</div>
          <el-slider class="write-slider" v-model="rateForm.frontier_tag" :max=10 :min="1" />
          <div class="label">产业影响</div>
          <el-slider class="write-slider" v-model="rateForm.influnce_tag" :max=10 :min="1" />
          <el-button class="btn" @click="onSubmit">提交</el-button>
        </div>
      </el-col>
    </el-row>
    <el-dialog title="价值度指标设置" :visible.sync="settingDialog" width="80%" :before-close="handleClose">
      <div>
        修改价值度指标内容
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="settingDialog = false">取 消</el-button>
        <el-button type="primary" @click="settingDialog = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import { valueList } from "@/utils/constant.js";
  export default {
    name: 'ArticleMark',
    data ()
    {
      return {
        value: 9.1,
        valueList: valueList,
        rateForm: {
          create_tag: 0,
          overturn_tag: 0,
          frontier_tag: 0,
          influnce_tag: 0,
        },
        settingDialog: false,

      }
    },
    methods: {
      // 评分信息初始化
      init ()
      {
        // todo:查询价值度
        // todo:查询评分记录
      },
      // 提交评分
      onSubmit ()
      {
        this.$message('submit!')
        // todo:修改并提交评分
      },
      // 查询价值度
      getValue ()
      {
        // todo:调用价值度接口
      },
      // 查询评分记录
      getRateHist ()
      {
        // todo:调用评分记录接口
      },
      // 打开价值度指标设置弹窗
      handleRateSetting ()
      {
        this.settingDialog = true;
      },
      // 关闭弹窗
      handleClose (done)
      {
        this.$confirm('确认关闭？')
          .then(_ =>
          {
            done();
          })
          .catch(_ => { });
      }
    },
    created ()
    {
      this.init()
    }
  }
</script>

<style scoped>
  .line {
    text-align: center;
  }

  .block {
    padding: 30px 0;

  }

  .title-part {
    color: #9a9a96;
    font-weight: 600;
    margin-bottom: 20px;
  }

  .value-label {
    font-size: 24px;
    font-weight: 500;
  }

  .btn {
    margin-top: 24px;
    float: right;
    color: #fff;
    background: #8c8c88;
    ;
  }

  .value {
    margin-left: 24px;
    font-size: 42px;
    font-weight: 600;
    color: #327442;
  }

  .user-info {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }

  .logo {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    background: #327442;
    margin-right: 24px;
  }

  .username {
    color: #838380;
    font-weight: 600;
  }

  .value-part {
    padding: 0 74px;
  }

  .rate-part {
    padding-right: 200px;
  }

  .read-slider>>>.el-slider__runway {
    margin: 8px 0;
    height: 3px;
    width: 80%;
  }

  .read-slider>>>.el-slider__bar {
    height: 3px;
  }

  .read-slider>>>.el-slider__button-wrapper {
    top: -16;
  }

  .read-slider>>>.el-slider__button {
    width: 8px;
    height: 8px;
  }

  .label {
    font-weight: 800;
    margin-top: 24px;
  }

  .write-slider>>>.el-slider__runway {
    background-color: #c4d59b;
    margin: 20px 0;
  }

  .write-slider>>>.el-slider__bar {
    background-color: #696969;
  }

  .write-slider>>>.el-slider__button {
    width: 18px;
    height: 18px;
    background-color: #696969;
    border: 2px solid #696969;
  }
</style>