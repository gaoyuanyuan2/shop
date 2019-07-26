//'formSelects.render('permissions');
var pageCurr;
var form;

$(function () {

    layui.use('table', function () {
        var table = layui.table;
        form = layui.form;

        tableIns = table.render({
            elem: '#orderList',
            url: '/order/getOrderList',
            method: 'get', //默认：get请求
            cellMinWidth: 80
            , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                , groups: 5 //只显示 1 个连续页码
                , first: '首页'//不显示首页
                , last: '尾页' //不显示尾页

            },
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[

                {fixed: 'left', title: '操作', align: 'center', toolbar: '#optBar', width: 200},
                {type: 'numbers'}
                , {field: 'startName', title: '明星名称', align: 'center', width: 100}
                , {field: 'type', title: '类型', align: 'center', width: 75}
                , {field: 'costPrice', title: '成本价', align: 'center', width: 75}
                , {field: 'salePrice', title: '销售价', align: 'center', width: 75}
                , {field: 'giveGood', title: '是否好评', align: 'center', width: 80}
                , {field: 'havePicture', title: '是否带图', align: 'center', width: 80}
                , {field: 'phone', title: '手机号', align: 'center', width: 120}
                , {field: 'createTime', title: '创建时间', align: 'center', width: 180}
                , {field: 'updateTime', title: '修改时间', align: 'center', width: 180}

            ]],
            id: 'reload'
            ,
            done: function (res, curr, count) {

                $("[data-field='createTime']").children().each(function () {
                    var str = this.innerText;
                    if ($(this).text() == null) {
                        $(this).text("")
                    } else {

                        var newStr = str.replace("T", " ").replace("+0000", "").replace(".000", "");
                        $(this).text(newStr)

                    }
                });
                $("[data-field='updateTime']").children().each(function () {
                    var str = this.innerText;
                    if ($(this).text() == null) {
                        $(this).text("")
                    } else {
                        var newStr = str.replace("T", " ").replace("+0000", "").replace(".000", "");
                        $(this).text(newStr)

                    }
                });
                $("[data-field='giveGood']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("是")
                    } else if ($(this).text() == '2') {
                        $(this).text("否")
                    }
                });
                $("[data-field='havePicture']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("是")
                    } else if ($(this).text() == '2') {
                        $(this).text("否")
                    }
                });
                pageCurr = curr;
            }
        });


        //监听工具条
        table.on('tool(orderTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //删除
                delOrder(data, data.id);
            } else if (obj.event === 'edit') {
                //编辑
                edit(data);
            }

        });

        //监听提交
        form.on('submit(orderSubmit)', function (data) {
            formSubmit(data);
            return false;
        });

    });

});

//提交表单
function formSubmit(obj) {
    $.ajax({
        type: "post",
        data: $("#orderForm").serialize(),
        url: "/order/setOrder",
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.msg, function () {
                    layer.closeAll();
                    load(obj);
                });
            } else {
                if (data.msg) {
                    layer.alert(data.msg)
                } else {
                    layer.alert("重复了", function () {
                        layer.closeAll()
                        load(obj);
                    });
                }
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试", function () {
                layer.closeAll();
                load(obj);
            });
        }
    });
}

//新增
function add() {
    edit(null, "新增");
}

//打开编辑框
function edit(data, title) {
    var pid = null;
    if (data == null) {
        $("#id").val("");
    } else {
        //回显数据
        $("#id").val(data.id);
        $("#startName").val(data.startName);
        $("#type2").val(data.type);
        $("#costPrice").val(data.costPrice);
        $("#salePrice").val(data.salePrice);
        $("#giveGood2").val(data.giveGood);
        $("#havePicture2").val(data.havePicture);
        $("#phone").val(data.phone);
        $("#remark").val(data.remark);
        pid = data.permissionIds;
    }

    formSelects.data('permissions', 'server', {
        url: '/permission/parentPermissionList',
        keyName: 'name',
        keyVal: 'id',
        success: function (id, url, searchVal, result) {      //使用远程方式的success回调

            console.log(pid)
            if (pid != null) {
                var assistAuditArry = pid.split(",");
                formSelects.value('permissions', assistAuditArry);
            }

            console.log(result);    //返回的结果
        },
        error: function (id, url, searchVal, err) {           //使用远程方式的error回调
            //同上
            console.log(err);   //err对象
        },
    });


    layer.open({
        type: 1,
        title: title,
        fixed: false,
        resize: false,
        shadeClose: true,
        area: ['1000px', '800px'],
        content: $('#setOrder'),
        end: function () {
            cleanOrder();
        }
    });

    form.render();
}

//重新加载table
function load(obj) {
    tableIns.reload({
        where: obj.f
        , page: {
            curr: 1 //从当前页码开始
        }
    });
}

function search() {
    tableIns.reload({
        where: {
            startName: $("#searchStartName").val(),
            type: $("#type").val(),
            phone: $("#phone").val(),
            giveGood: $("#giveGood").val(),
            havePicture: $("#havePicture").val()
        }
        , page: {
            curr: 1 //从当前页码开始
        }
    });
}

function reset() {
    $("#startName").val("");
    $("#type").val("");
    $("#phone").val("");
    $("#giveGood").val("");
    $("#havePicture").val("");
}

//删除
function delOrder(obj, id) {
    if (null != id) {
        layer.confirm('您确定要删除吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/order/delete", {"id": id}, function (data) {
                if (data.code == 1) {
                    layer.alert(data.msg, function () {
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function () {
            layer.closeAll();
        });
    }
}


function cleanOrder() {
}

