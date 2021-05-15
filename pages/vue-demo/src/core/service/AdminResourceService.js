/*
服务类，某一个模块对象的所有相关方法。
返回值都为promise。所以需要前端自己then解决问题
正常且成功情况下，需要调用.then 自己在方法体内赋值
*/

class AdminResourceService {
    // 敏感信息：--》角色--》敏感字段---》需要脱敏  @DataDesensitization
    //@JsonSerialize(using = PhoneJsonSerializer.class)
    //具体业务逻辑中判定是否需要脱敏
    // 菜单    按钮（api） 下拉框值 输入框 时间框 起止时间框 （api对应指定字段及字段可输入值）
    // 页面 显示可以都显示，还是需要后台判断执行功能
    getMenu() {
    }

    getResource() {
    }
    addResource(){}
    deleteResource(){}
    updateResource(){}

    getButton() {
    }

}

const adminTabService = new AdminTabService()

export default adminTabService
