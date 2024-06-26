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
            <span style="float: right;">为您检索到{{total}}条记录</span>
          </div>
          <div style="margin-top: 15px;">
            <el-input placeholder="请输入内容" v-model="searchForm.text" @change="handlSearch">
              <el-button slot="append" icon="el-icon-search" @click="handlSearch"></el-button>
            </el-input>
          </div>
        </div>
        <!-- 新闻列表 -->
        <div class="article-container" v-loading="loading">
          <div v-for="item in tableList" :key="item.id" class="article-card">
            <div class="card-title">
              <!-- 原标题 -->
              <a :href="item.linkUrl" target="_blank">{{item.title}}</a>
            </div>
            <div class="card-title_cn">
              <!-- 中文标题 -->
              <!-- {{$t('item.title_cn')}} -->
              <!-- 中文标题 -->
            </div>
            <div class="card-tags">
              <!-- 日期、地区、机构、期刊名称 -->
              <span class="tag">{{item.date}}</span>
              <span class="tag">{{item.nation}}</span>
              <span class="tag">{{item.postAgency}}</span>
            </div>
            <div class="card-abstract">
              <!-- 摘要 -->
              {{item.text}}
              <!-- 前端直接截取正文 -->
            </div>
            <el-row class="card-actions">
              <el-col>
                <a :href="item.linkUrl" target="_blank" class="blue-text-underline">
                  <!-- read more -->
                  read more
                </a>
              </el-col>
              <el-col>
                <span class="action2" @click="goMark(item.id)" v-if="userRole=='EXPERT' || userRole=='ADMIN'">
                  <!-- 评分 -->
                  评分>>>
                </span>
                <span class="btn-del" @click="handleDelete(item.id)"
                  v-if="userRole=='LIBRARIAN' ||userRole=='ADMIN'">删除</span>
                <span class="btn-edit" @click="handleUpdate(item)"
                  v-if="userRole=='LIBRARIAN' ||userRole=='ADMIN'">编辑</span>


              </el-col>
            </el-row>
          </div>
          <!-- 分页 -->
          <div>
            <el-pagination :current-page.sync="searchForm.pageNumber" :page-size="12" layout="prev, pager, next, jumper"
              :total="total">
            </el-pagination>
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
                <el-form-item label="日期">
                  <el-date-picker v-model="daterange" type="daterange" range-separator="至" start-placeholder="开始日期"
                    end-placeholder="结束日期">
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="标题">
                  <el-input v-model="searchForm.title"></el-input>
                </el-form-item>
                <el-form-item label="作者">
                  <el-input v-model="searchForm.author"></el-input>
                </el-form-item>
                <el-form-item label="发布机构">
                  <el-input v-model="searchForm.postAgency"></el-input>
                </el-form-item>
                <el-form-item label="国家地区">
                  <el-input v-model="searchForm.nation"></el-input>
                </el-form-item>
                <!-- <el-form-item label="价值度">
                  <el-input></el-input>
                </el-form-item> -->
                <el-form-item label="信息类型" v-if="typeList.length">
                  <el-radio-group v-model="searchForm.info_type" @click.native="pendingRes_type($event)">
                    <el-radio v-for="item in typeList" :key="item.value" :label="item.value">{{item.label}}</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="领域" v-if="domainList.length">
                  <el-radio-group v-model="searchForm.domain" @click.native="pendingRes_domain($event)">
                    <el-radio v-for="item in domainList" :key="item.value" :label="item.value">{{item.label}}</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="学科" v-if="subjectList.length">
                  <el-radio-group v-model="searchForm.subject" @click.native="pendingRes_subject($event)">
                    <el-radio v-for="item in subjectList" :key="item.value"
                      :label="item.value">{{item.label}}</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-form>
            </div>
          </el-card>
        </div>

      </el-col>
    </el-row>
    <!-- 编辑文章弹窗 -->
    <el-dialog title="编辑" :visible.sync="updateDialog" width="80%" :before-close="handleClose">
      <div>
        <el-form ref="updateForm" :model="updateForm" label-width="80px">
          <el-form-item label="标题">
            <el-input v-model="updateForm.title"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="updateForm.author"></el-input>
          </el-form-item>
          <el-form-item label="发布机构">
            <el-input v-model="updateForm.postAgency"></el-input>
          </el-form-item>
          <el-form-item label="网址">
            <el-input v-model="updateForm.linkUrl"></el-input>
          </el-form-item>
          <el-form-item label="国家地区">
            <el-input v-model="updateForm.nation"></el-input>
          </el-form-item>
          <el-form-item label="信息类型" v-if="typeList.length">
            <el-radio-group v-model="updateForm.infoType">
              <el-radio v-for="item in typeList" :key="item.value" :label="item.value">{{item.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="领域" v-if="domainList.length">
            <el-radio-group v-model="updateForm.domain">
              <el-radio v-for="item in domainList" :key="item.value" :label="item.value">{{item.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="学科" v-if="subjectList.length">
            <el-radio-group v-model="updateForm.subject">
              <el-radio v-for="item in subjectList" :key="item.value" :label="item.value">{{item.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <!-- <el-form-item label="正文">
            <el-input v-model="updateForm.text" type="text"></el-input>
          </el-form-item> -->
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialog = false">取 消</el-button>
        <el-button type="primary" @click="updateArticle">提 交</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { typeList, domainList, subjectList } from "@/utils/constant.js";
  import { convertDateToISO, param2Obj } from '@/utils/index.js';
  import { getRole } from "@/utils/auth";

  export default {
    name: 'Dashboard',
    computed: {
      ...mapGetters([
        'articleList'
      ])
    },
    data ()
    {
      return {
        userRole: '',
        loading: false,
        daterange: '',
        // date: '',
        typeList: typeList,
        domainList: domainList,
        subjectList: subjectList,
        searchForm: {
          postAgency: '',
          startDate: '',
          endDate: '',
          title: '',
          author: '',
          text: '',
          info_type: '',
          nation: '',
          domain: '',
          subject: '',
          pageNumber: 1,
          pageSize: 12,
        },
        total: 0,
        tableList: [],
        updateDialog: false,
        updateForm: {
          id: '',
          postAgency: '',
          title: '',
          author: '',
          text: '',
          infoType: '',
          nation: '',
          domain: '',
          subject: '',
          date: '',
          linkUrl: '',

        },
      }
    },

    // 监听搜索表单，表单字段值修改即触发查询，更新数据
    watch: {
      "searchForm.author":
      {
        deep: true,
        handler (newVal, oldVal)
        {
          // 实现input连续输入，只发一次请求
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() =>
          {
            if (newVal != oldVal) {
              this.handlSearch()
            }
          }, 500)
        }
      },
      "searchForm.title":
      {
        deep: true,
        handler (newVal, oldVal)
        {
          // 实现input连续输入，只发一次请求
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() =>
          {
            if (newVal != oldVal) {
              this.handlSearch()
            }
          }, 500)
        },
      },
      "searchForm.postAgency":
      {
        deep: true,
        handler (newVal, oldVal)
        {
          // 实现input连续输入，只发一次请求
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() =>
          {
            if (newVal != oldVal) {
              this.handlSearch()
            }
          }, 500)
        }
      },
      daterange (newVal, oldVal)
      {
        if (newVal) {
          if (newVal.length || newVal != oldVal) {
            this.searchForm.startDate = convertDateToISO(newVal[0]);
            this.searchForm.endDate = convertDateToISO(newVal[1]);
            this.handlSearch();
          }
        } else {
          this.searchForm.startDate = '';
          this.searchForm.endDate = '';
          this.handlSearch();
        }


      },
      // "searchForm.info_type":
      // {
      //   deep: true,
      //   handler (newVal, oldVal)
      //   {
      //     if (newVal || newVal != oldVal) {
      //       this.handlSearch();
      //     }
      //   }
      // },
      "searchForm.nation":
      {
        deep: true,
        handler (newVal, oldVal)
        {
          // 实现input连续输入，只发一次请求
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() =>
          {
            if (newVal != oldVal) {
              this.handlSearch()
            }
          }, 500)
        }
      },
      "searchForm.subject":
      {
        deep: true,
        handler (newVal, oldVal)
        {
          if (newVal || newVal != oldVal) {
            this.handlSearch();
          }
        }
      },
      "searchForm.pageNumber":
      {
        deep: true,
        handler (newVal, oldVal)
        {
          if (newVal || newVal != oldVal) {
            this.handlSearch();
          }
        }
      },
      "searchForm.pageSize":
      {
        deep: true,
        handler (newVal, oldVal)
        {
          if (newVal || newVal != oldVal) {
            this.handlSearch();
          }
        }
      },
    },

    methods: {
      // 单选框设置-信息类型
      pendingRes_type (event)
      {
        if (event.target.tagName === "INPUT") {
          let id = event.target.value
          if (this.searchForm.info_type != id) {
            this.searchForm.info_type = id;
            this.handlSearch();
          } else {
            this.searchForm.info_type = ''
            this.handlSearch();
          }
        }
      },

      // 单选框设置-领域
      pendingRes_domain (event)
      {
        if (event.target.tagName === "INPUT") {
          let id = event.target.value
          if (this.searchForm.domain != id) {
            this.searchForm.domain = id;
            this.handlSearch();
          } else {
            this.searchForm.domain = ''
            this.handlSearch();
          }
        }
      },

      // 单选框设置-学科
      pendingRes_subject (event)
      {
        if (event.target.tagName === "INPUT") {
          let id = event.target.value
          if (this.searchForm.subject != id) {
            this.searchForm.subject = id;
            this.handlSearch();
          } else {
            this.searchForm.subject = ''
            this.handlSearch();
          }
        }
      },

      // 文章列表初始化
      async init ()
      {
        // todo:初始化查询列表，无搜索条件，调用getArticleList()，获取全量数据，赋值

        await this.getArctiles();

      },
      // 获取文章列表
      getArctiles ()
      {
        // todo:发送查询文章列表接口，上送参数
        this.loading = true;
        this.$store.dispatch('dashboard/getArticleList', this.searchForm).then(() =>
        {
          this.tableList = this.articleList.records;
          this.total = this.articleList.total;
          this.loading = false;
        })
      },
      // 点击搜索
      async handlSearch ()
      {
        // todo:根据搜索条件，调用getArticleList()，按需查询，展示搜索结果
        await this.getArctiles();
      },
      // 删除文章
      deleteArticle (id)
      {
        this.$store.dispatch('dashboard/deleteArticle', id).then(() =>
        {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.handlSearch()
        })
      },
      // 点击删除
      handleDelete (id)
      {
        this.$confirm('此操作将删除该文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () =>
        {
          await this.deleteArticle(id)
        }).catch(() =>
        {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      // 编辑文章
      updateArticle ()
      {
        this.$store.dispatch('dashboard/updateArticle', this.updateForm).then(() =>
        {
          this.$message({
            type: 'success',
            message: '更新成功!'
          });
          this.updateDialog = false;
          this.handlSearch()
        }).catch(() =>
        {
          this.$message({
            type: 'warning',
            message: '更新失败,请稍后再试'
          })
        })
      },
      // 点击编辑
      handleUpdate (item)
      {
        this.updateDialog = true;
        this.updateForm.id = item.id;
        this.updateForm.author = item.author;
        this.updateForm.linkUrl = item.linkUrl;
        this.updateForm.date = item.date;
        this.updateForm.text = item.text;
        this.updateForm.title = item.title;
        this.updateForm.postAgency = item.postAgency;
        this.updateForm.nation = item.nation;
        this.updateForm.domain = item.domain;
        this.updateForm.subject = item.subject;
        this.updateForm.infoType = item.infoType;
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
      },
      // 点击评分，跳转至评分页面
      goMark (id)
      {
        this.$router.push(`/articleMark/${id}`)
      }
    },

    created ()
    {
      this.init();
      this.userRole = getRole();
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
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
  }

  .card-actions {
    display: flex;
  }

  .action2 {
    float: right;
    cursor: pointer;
  }

  .btn-edit {
    float: right;
    cursor: pointer;
    color: #409EFF;
    margin-right: 20px;
  }

  .btn-del {
    float: right;
    cursor: pointer;
    color: red;
    margin-right: 20px;
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

  .el-date-editor--daterange.el-input__inner {
    width: 100%;
  }
</style>