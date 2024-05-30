<template>
  <div class="dashboard-container">
    <el-row>
      <!-- 网站介绍 -->
      <div class="first-part">
        <div class="web-title">科学前沿港</div>
        <div class="web-title-en">Science Frontiers Hub</div>
      </div>

      <!-- 新闻列表展示区 -->
      <el-col :span="16">
        <!-- 搜索框 -->
        <div>
          <div>
            <i class="el-icon-loading"></i>
            <span> 搜索您感兴趣的内容</span>
          </div>
          <div style="margin-top: 15px;">
            <el-input placeholder="请输入内容" v-model="searchForm.text">
              <el-button slot="append" icon="el-icon-search" @click="handlSearch"></el-button>
            </el-input>
          </div>
        </div>
        <!-- 新闻列表 -->
        <div class="article-container">
          <div v-for="item in articleList" :key="item.id" class="article-card">
            <div class="card-title">
              <!-- 原标题 -->
              <a :href="item.link" target="_blank">{{item.title}}</a>
            </div>
            <div class="card-title_cn">
              <!-- 中文标题 -->
              {{item.title_cn}}
            </div>
            <div class="card-tags">
              <!-- 日期、地区、机构、期刊名称 -->
              <span class="tag">{{item.time}}</span>
              <span class="tag">{{item.area}}</span>
              <span class="tag">{{item.publ_agency}}</span>
            </div>
            <div class="card-abstract">
              <!-- 摘要 -->
              {{item.abstract}}
            </div>
            <el-row class="card-actions">
              <el-col>
                <a :href="item.link" target="_blank" class="blue-text-underline">
                  <!-- read more -->
                  read more
                </a>
              </el-col>
              <el-col>
                <div class="action2" @click="goMark">
                  <!-- 评分 -->
                  评分>>>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>

      </el-col>
      <!-- 搜索区 -->
      <el-col :span="8">

        <div class="search-part">
          <el-card>
            <el-divider>更多搜索</el-divider>
            <!-- 操作按钮 -->
            <!-- <div class="action-btns">
            <el-button>重置</el-button>
            <el-button>查询</el-button>
          </div> -->
            <!-- 价值度、信息类型、发布机构、国家地区、年度、领域、学科 -->
            <div>
              <el-form label-position="left" ref="form" v-model="searchForm" label-width="80px">
                <el-form-item label="年度">
                  <el-date-picker type="year" placeholder="选择年">
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="发布机构">
                  <el-input v-model="searchForm.text"></el-input>
                </el-form-item>
                <el-form-item label="国家地区">
                  <el-input></el-input>
                </el-form-item>
                <el-form-item label="价值度">
                  <el-input></el-input>
                </el-form-item>
                <el-form-item label="信息类型">
                  <el-checkbox-group v-model="searchForm.type">
                    <el-checkbox v-for="item in typeList" :key="item.value" :label="item.label"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
                <el-form-item label="领域">
                  <el-checkbox-group v-model="searchForm.domain">
                    <el-checkbox v-for="item in domainList" :key="item.value" :label="item.label"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
                <el-form-item label="学科">
                  <el-checkbox-group v-model="searchForm.subject">
                    <el-checkbox v-for="item in subjectList" :key="item.value" :label="item.label"></el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
              </el-form>
            </div>
          </el-card>
        </div>

      </el-col>
    </el-row>



  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { typeList, domainList, subjectList } from "@/utils/constant.js";

  export default {
    name: 'Dashboard',
    computed: {
      ...mapGetters([
        'name',
        'roles'
      ])
    },
    data ()
    {
      return {
        typeList: typeList,
        domainList: domainList,
        subjectList: subjectList,
        searchForm: {
          text: '',
          type: '',
          domain: '',
          subject: '',
        },
        articleList: [
          {
            id: '1',
            link: 'https://www.agriculture.gov.au/about/news/hys-data-interoperability',
            title: 'Have Your Say on the Data Interoperability Framework',
            title_cn: '对数据互操作性框架有发言权',
            time: '03 May 2024',
            area: '澳大利亚',
            publ_agency: '澳大利亚政府',
            text: '',
            abstract: 'The Department of Agriculture, Fisheries and Forestry has today released a public consultation paper seeking feedback on the first Data…The Department of Agriculture, Fisheries and Forestry has today released a public consultation paper seeking feedback on the first Data…'
          },
          {
            id: '2',
            link: 'https://www.agriculture.gov.au/about/news/hys-data-interoperability',
            title: 'Have Your Say on the Data Interoperability Framework',
            title_cn: '对数据互操作性框架有发言权',
            time: '03 May 2024',
            area: '澳大利亚',
            publ_agency: '澳大利亚政府',
            text: '',
            abstract: 'The Department of Agriculture, Fisheries and Forestry has today released a public consultation paper seeking feedback on the first Data…The Department of Agriculture, Fisheries and Forestry has today released a public consultation paper seeking feedback on the first Data…'
          },
          {
            id: '3',
            link: 'https://www.agriculture.gov.au/about/news/hys-data-interoperability',
            title: 'Have Your Say on the Data Interoperability Framework',
            title_cn: '对数据互操作性框架有发言权',
            time: '03 May 2024',
            area: '澳大利亚',
            publ_agency: '澳大利亚政府',
            text: '',
            abstract: 'The Department of Agriculture, Fisheries and Forestry has today released a public consultation paper seeking feedback on the first Data…The Department of Agriculture, Fisheries and Forestry has today released a public consultation paper seeking feedback on the first Data…'
          }
        ],
      }
    },
    watch: {
      searchForm (val)
      {
        // todo:监听搜索表单，表单字段值修改即触发查询，更新数据
      }
    },

    methods: {
      // 文章列表初始化
      init ()
      {
        // todo:初始化查询列表，无搜索条件，调用getArticleList()，获取全量数据，赋值
      },
      // 获取文章列表
      getArctileList ()
      {
        // todo:发送查询文章列表接口，上送参数
      },
      // 点击搜索
      handlSearch ()
      {
        // todo:根据搜索条件，调用getArticleList()，按需查询，展示搜索结果
      },
      // 点击评分，跳转至评分页面
      goMark ()
      {
        this.$router.push('/articleMark')
      }
    },

    created ()
    {
      this.init()
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard {
    &-container {
      margin: 20px 68px;
    }
  }

  .first-part {
    display: flex;
    height: 160px;
    padding: 100px 20px 12px 20px;
    background-color: #327442;
    margin-bottom: 18px;

    .web-title {
      font-size: 40px;
      color: white;
      font-weight: 800;
    }

    .web-title-en {
      margin-left: 18px;
      font-size: 32px;
      color: #dcd8a3;
      font-weight: 400;
      align-content: end;
      padding-left: 10px;
      border-left-style: solid;
    }
  }

  .article-container {
    margin-top: 18px;
  }

  .article-card {
    margin-bottom: 20px;
    background: #c9e0bf;
    transition-duration: 0.5s;
    padding: 18px;
    border-radius: 2px;
  }

  .article-card:hover {
    -webkit-box-shadow: #ccc 0px 10px 10px;
    -moz-box-shadow: #ccc 0px 10px 10px;
    box-shadow: #ccc 0px 10px 10px;
  }

  .card-title {
    margin-bottom: 8px;
  }

  .card-title:hover {
    color: #409EFF;
    text-decoration: underline;
  }

  .card-title_cn {
    margin-bottom: 8px;
    font-weight: 200;
  }

  .card-tags {
    margin-bottom: 12px;
  }

  .tag {
    background-color: #919392;
    color: white;
    padding: 5px;
    font-size: 12px;
    border-radius: 10px;
    margin-right: 12px;
  }

  .card-abstract {
    margin-bottom: 12px;
    border-left-style: solid;
    padding-left: 8px;
  }

  .card-actions {
    display: flex;
  }

  .action2 {
    float: right;
    cursor: pointer;
  }

  .blue-text-underline {
    color: #409EFF;
    /* 设置字体颜色为蓝色 */
    text-decoration: underline;
    /* 添加下划线 */
    cursor: pointer;
  }

  .search-part {
    margin-left: 12px;
    padding: 12px 0 0 12px;
    /* margin-top: 12px; */
  }

  .action-btns {
    width: 100%;
    margin-bottom: 20px;
    text-align: right;
  }

  .el-date-editor.el-input {
    width: 100%;
  }
</style>