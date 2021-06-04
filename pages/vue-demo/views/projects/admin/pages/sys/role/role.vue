<template>
  <div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
        >新增</el-button>
      </el-col>
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
    </el-row>
    <div style="width: 50%">
<!--      -->
      <el-table
          :data="tableData"
          row-key="id"
          border
          highlight-current-row
          :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column
            fixed
            prop="name"
            label="岗位名称"
            >
        </el-table-column>
        <el-table-column
            width="50"
            prop="status"
            label="状态">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作" align="center"
            width="200">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:role:edit']"
            >修改</el-button>
            <el-button
                size="mini"
                type="text"
                icon="el-icon-circle-check"
                @click="handleDataScope(scope.row)"
                v-hasPermi="['system:role:edit']"
            >数据权限</el-button>
            <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:role:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <br/>
    <el-table
        :data="tableData1"
        style="width: 100%"
        row-key="id"
        border
        lazy
        :load="load"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column
          prop="date"
          label="日期"
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址">
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData: [{
        id: 1,
        date: '2016-05-02',
        name: '系统管理',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        id: 2,
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        id: 3,
        date: '2016-05-01',
        name: '系统管理',
        address: '上海市普陀区金沙江路 1519 弄',
        children: [{
          id: 31,
          date: '2016-05-01',
          name: '菜单管理',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          id: 32,
          date: '2016-05-01',
          name: '权限组管理',
          address: '上海市普陀区金沙江路 1519 弄'
        }]
      }, {
        id: 4,
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      tableData1: [{
        id: 1,
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        id: 2,
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        id: 3,
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄',
        hasChildren: true
      }, {
        id: 4,
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  methods: {
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