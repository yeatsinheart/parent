业务涉及逻辑
    1。涉及的对象自身的增删查改
    2。关联关系通过事件的方式进行增删查改
开站：随意服务器
租户：独有服务器/公用系统运行逻辑，使用域名-租户id区分。。
    dubbo分组/version隔离，数据库分配



业务架构：灰度发布
界面：维护中
界面：无权限
用户 - 界面 - 网关 - 应用 - 数据库

           Nacos

    全局配置（认证服务）： 独立部署
        站点状态 site
        站点白名单 
        接口管理 api 是否需要权限
        资源管理  限制参数「yyy:in:zzz,yyy:=:zzz]
        安全认证（验签/解密/JWT）
        鉴权


    后台管理：独立网关 鉴权 白名单（nacos:site_id=1.1.1.1,2.2.2.2）
        menu
        menu_function class api 页面中会触发的所有api都需要添加为功能，比如报表页面，那么一打开调用的数据查询功能就是一个功能点
        两个都会被放入resource中通过type区分
        开通游戏 设定游戏配置
        开通金流 设定金流配置
        管理游戏（默认配置）
        管理金流（默认配置）
    内容cms

    用户网关 api 定义接口信息
           request_op  操作记录

    总线bus 本地事件表bus_event 事件表轮询处理====》别的写入/修改业务，尽量只操作单表，不负责其他表
                凡事多流程，不能本地一次性完成的，或者关联业务复杂的，都放到这里来异步完成
                ：注册送彩金，被邀请注册，充值递进流程，游戏的转进转出

    站点site site_info 统计脚本
            site_language
            site_currency
            site_resource 菜单 功能
            site_domain
            site_role 数据敏感度 xxx字段（加密 可见）
            site_role_resource
            site_user
            site_user_role
            site_free_user
            site_app_info
            提供role拥有的所有资源，前端自己整理分类，每块区域通过固定class表示是否显示：getResourceClass(resourceId),无则返回null，表示不显示咯。
                比如下拉列表中，option :class="getResourceClass(1)"===》反向要求后端不能提供通用接口，查询所有/查询指定条件 都要进行拆分出不同的接口。
                组合查询的情况,限定接口的参数xxx取值（in则判断是否符合，=复写）
                比如input中，input :class="getResourceClass(1)"
                比如button中，button :class="getResourceClass(1)"

    会员user user salt
            user_level
            user_wallet
            user_agent 代理
            user_group 邀请群（建站拥有顶级群，群主是0） 是否代理群  用户  群组ID===》（ID转成字符）邀请码/推广码 群备注名称  需要注意全民推广群与代理推广群的差异。
            user_group_member 群成员 直连关系族谱

    财务money
    风控risk
    促销cost
    订单order
    统计report
    权限auth
    接入third third-site
             third_categra
             third_type
             third_detail
             third_detail_type
    游戏game game_site
    金流pay pay_site







