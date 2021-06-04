<template>
  <div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
        >新增</el-button>
      </el-col>
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
    </el-row>
    <div style="width: 100%">
<!--      -->
      <el-table
          :data="resources"
          row-key="id"
          border
          highlight-current-row
          :tree-props="{children: 'subs', hasChildren: 'hasChildren'}">
        <el-table-column
            fixed
            prop="name"
            label="节点"
            width="120">
        </el-table-column>
        <!--      <el-table-column
                  prop="name"
                  label="名称"
                  width="180">
              </el-table-column>-->
        <el-table-column
            prop="url"
            label="URL">
        </el-table-column>
        <el-table-column
            width="50"
            prop="status"
            label="状态">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100">
          <template slot-scope="scope">
            <el-button @click="handleUpdate(scope.row)" type="text" size="small">查看</el-button>
            <el-button @click="handleUpdate(scope.row)" type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 添加或修改资源对话框  :rules="rules"-->
    <el-dialog title="资源信息" :visible.sync="showResourceInfo" width="600px" append-to-body>
      <el-form ref="form" :model="resourceInfo"  label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="上级ID">
              <el-input v-model="resourceInfo.parent" placeholder="请输入资源上级ID" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资源名称" prop="menuName">
              <el-input v-model="resourceInfo.name" placeholder="请输入资源名称" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="资源类型" prop="resourceInfo.type">
              <el-radio-group v-model="resourceInfo.type">
                <el-radio label="P">页面</el-radio>
                <el-radio label="F">按钮</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
<!--          <el-col :span="12">
            <el-form-item v-if="resourceInfo.icon != 'F'" label="资源图标">
              <el-popover
                  placement="bottom-start"
                  width="460"
                  trigger="click"
                  @show="$refs['iconSelect'].reset()"
              >
                <IconSelect ref="iconSelect" @selected="selected" />
                <el-input slot="reference" v-model="resourceInfo.icon" placeholder="点击选择图标" readonly>
                  <svg-icon
                      v-if="resourceInfo.icon"
                      slot="prefix"
                      :icon-class="resourceInfo.icon"
                      class="el-input__icon"
                      style="height: 32px;width: 16px;"
                  />
                  <i v-else slot="prefix" class="el-icon-search el-input__icon" />
                </el-input>
              </el-popover>
            </el-form-item>
          </el-col>-->


          <el-col :span="24">
            <el-form-item v-if="resourceInfo.type != 'F'" label="URL地址" prop="path">
              <el-input v-model="resourceInfo.url" placeholder="请输入URL地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="orderNum">
              <el-input-number v-model="resourceInfo.sequence" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="resourceInfo.type != 'F'" label="显示状态">
              <el-radio-group v-model="resourceInfo.visible">
                <el-radio label="0">隐藏</el-radio>
                <el-radio label="1">显示</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitResource">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import adminMenuService from "@/core/service/AdminMenuService";

export default {
  data() {
    return {
      showResourceInfo:false,
      resourceInfo:{},
      resources: []
    }
  },
  methods: {
    /** 新增按钮操作 */
    handleAdd() {
      this.resourceInfo={}
      this.showResourceInfo = true;
    },
    /** 新增按钮操作 */
    handleUpdate(data) {
      if(data){
        this.resourceInfo=data
      }
      this.showResourceInfo = true;
    },
    submitResource(){
      console.log(this.resourceInfo)
      this.showResourceInfo = false;
    },
    load(tree, treeNode, resolve) {
      setTimeout(() => {
        resolve([
          {
            id: 31,
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            id: 32,
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }
        ])
      }, 1000)
    }
  },
  mounted() {
    adminMenuService.getMenu().then(response => {
      this.resources = response
    })
  },
}
</script>
<style lang="scss" scoped>
::v-deep .el-table td, ::v-deep .el-table th {
  padding: 0 0;
}
::v-deep .el-button--small, ::v-deep .el-button--small.is-round {
  padding: 0 0;
}
::v-deep .el-button+.el-button {
  margin-left: 2px;
}
</style>