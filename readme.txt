site:
site:site_id,name_key,logo_key,status
    site_relation 子站，暂时不管


业务涉及逻辑
    1。涉及的对象自身的增删查改
    2。关联关系通过事件的方式进行增删查改
    resource(menu,button,select)encrypt, resource   对应前端的资源编号 ： 页面 页面中的元素
    api,
        api-resource:接口是哪些资源触发的
    position：岗位职责（集权继承，个人额外，个人无权）
        position-role:创建一个岗位等于多一个角色
        position-user
        role-resource
        role-data
    个人权限设置，岗位权限设置
    个人（岗位权限+个人权限）
    role
        岗位角色，个人角色
        role-resource:关联资源编号
        role-data:数据权限：脱敏，敏感 联系方式 身份证号码，银行卡号，实名信息，用户名
        role-user:用户关联的角色
        站长：ALL
        财务：
        风控：
        推广：
        客服：
        运营：


静态资源：菜单 url
        功能 class
岗位角色，数据敏感度

业务架构：
    后台管理：
    内容cms

    用户网关

    总线bus 本地事件表 事件表轮询处理

    站点site
    会员user
    财务money
    风控redis
    促销cost
    订单order
    统计report
    权限auth







