var menus = [
    {
        id: "9000",
        text: "主菜单",
        icon: "",
        isHeader: true
    },
    {
        id: "9001",
        text: "项目管理",
        icon: "fa fa-laptop",
        children: [
            {
                id: "90011",
                text: "新建接口",
                icon: "fa fa-circle-o",
                url: "forms/interface_iframe.html",
                targetType: "iframe-tab"
            }
        ]
    },
    {
        id: "9002",
        text: "项目文档",
        icon: "fa fa-edit",
        children: [
            {
                id: "90021",
                text: "体育盘",
                url: "forms/advanced_iframe.html",
                targetType: "iframe-tab",
                icon: "fa fa-circle-o"
            },
            {
                id: "90022",
                text: "综合盘",
                url: "forms/general_iframe.html",
                targetType: "iframe-tab",
                icon: "fa fa-circle-o"
            },
            {
                id: "90023",
                text: "彩票盘",
                url: "forms/editors_iframe.html",
                targetType: "iframe-tab",
                icon: "fa fa-circle-o",
                children: [{
                    id: "90023",
                    text: "1.0",
                    url: "forms/editors_iframe.html",
                    targetType: "iframe-tab",
                    icon: "fa fa-circle-o"
                }]
            }
        ]
    }
];