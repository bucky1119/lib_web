<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="changeTab">
      <el-tab-pane label="用户管理" name="userManage">
        <el-table :data="userList" stripe>
          <el-table-column prop="username" label="用户名">
          </el-table-column>
          <el-table-column prop="role" label="用户角色">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEditDialog(scope.$index, scope.row,'user')">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row,'role')">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="角色管理" name="roleManage">
        <!-- <div> -->
        <el-button @click="handleAdd">添加</el-button>
        <!-- </div> -->
        <el-table :data="roleList" stripe>
          <el-table-column prop="role" label="角色">
          </el-table-column>
          <el-table-column prop="message" label="说明">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEditDialog(scope.$index, scope.row,'role')">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row),'role'">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="价值度管理" name="valueManage">价值度管理</el-tab-pane>
    </el-tabs>
    <!-- 编辑弹窗 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
      <div v-if="dialogTitle == '编辑用户角色'">
        <el-form ref="userRoleForm" :model="userRoleForm" label-width="80px">
          <el-form-item label="用户名">
            <el-input v-model="userRoleForm.userName" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户角色">
            <el-input v-model="userRoleForm.userRole"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="dialogTitle == '编辑角色信息'">
        <el-form ref="roleMessForm" :model="roleMessForm" label-width="80px">
          <el-form-item label="角色">
            <el-input v-model="roleMessForm.role"></el-input>
          </el-form-item>
          <el-form-item label="说明">
            <el-input v-model="roleMessForm.message"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="update">修 改</el-button>
      </span>
    </el-dialog>
    <!-- 添加角色弹窗 -->
    <el-dialog title="添加角色" :visible.sync="addDialog" width="50%" :before-close="handleClose">

      <el-form ref="addRoleForm" :model="addRoleForm" label-width="80px">
        <el-form-item label="角色">
          <el-input v-model="addRoleForm.role"></el-input>
        </el-form-item>
        <el-form-item label="说明">
          <el-input v-model="addRoleForm.message"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRole">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

  export default {
    name: 'UserManage',
    data ()
    {
      return {
        activeName: 'userManage',
        dialogTitle: '',
        dialogVisible: false,
        addDialog: false,
        userRoleForm: {
          userName: '',
          userRole: '',
        },
        roleMessForm: {
          id: 0,
          role: '',
          message: '',
        },
        addRoleForm: {
          id: 0,
          role: '',
          message: '',
        },
        roleList: [
          {
            id: 1,
            role: '用户',
            message: '普通用户',
          },
          {
            id: 2,
            role: '运营管理用户',
            message: '支持增删改查、统计等功能',
          },
          {
            id: 3,
            role: '专家学者用户',
            message: '支持评分功能',
          }
        ],
        userList: [
          {
            username: "Dr.ken",
            role: "专家学者用户，运营管理用户"
          },
          {
            username: "Dr.ken",
            role: "专家学者用户"
          },
          {
            username: "Dr.ken",
            role: "运营管理用户"
          },
          {
            username: "Dr.ken",
            role: "用户"
          },
          {
            username: "Dr.ken",
            role: "用户"
          }
        ]
      };
    },
    methods: {
      // 点击切换面板
      changeTab (tab, event)
      {
        this.activeName = tab.name
      },
      // 初始化
      init ()
      {
        // todo:初始化查询用户列表、角色列表、价值度指标列表
      },
      // 查询用户列表
      getUserList ()
      {

      },
      // 查询角色列表
      getRoleList ()
      {

      },
      // 查询价值度列表
      getRateList ()
      {

      },
      // 打开添加角色弹窗
      handleAdd ()
      {
        this.addDialog = true;
      },
      // 添加角色信息
      addRole ()
      {
        // todo:调用添加角色接口
      },
      // 删除
      handleDelete (index, item, val)
      {
        if (val === 'user') {
          // todo:调用删除用户接口
        } else if (val === 'role') {
          // todo:调用删除角色接口
        }
        this.init();
      },
      // 打开编辑用户角色弹窗
      handleEditDialog (index, item, val)
      {
        if (val === 'user') {
          this.dialogTitle = '编辑用户角色'
          this.userRoleForm.userName = item.username;
          this.userRoleForm.userRole = item.role
        } else if (val === 'role') {
          this.dialogTitle = '编辑角色信息';
          this.roleMessForm.id = item.id;
          this.roleMessForm.role = item.role;
          this.roleMessForm.message = item.message;
        }
        this.dialogVisible = true;
      },
      // 提交修改表单
      update ()
      {
        if (this.dialogTitle === '编辑用户角色') {
          // todo:调用编辑用户接口
        } else if (this.dialogTitle === '编辑角色信息') {
          // todo:调用编辑角色接口
        }
        this.init();
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
    },

  }
</script>