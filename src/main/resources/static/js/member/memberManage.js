//'formSelects.render('permissions');
var pageCurr;
var form;

$(function () {

    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#uploadMember' //绑定元素
            ,url: '/member/upload' //上传接口
            ,accept: 'file'
            ,done: function(res){
                load({});
            }
            ,error: function(){
                load({});
            }
        });
    });

    layui.use('table', function () {
        var table = layui.table;
        form = layui.form;

        tableIns = table.render({
            elem: '#memberList',
            url: '/member/getMemberList',
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
                , {field: 'name', title: '名称', align: 'center', width: 100}
                , {field: 'label', title: '用户标签', align: 'center', width: 200}
                , {field: 'phone', title: '手机号码', align: 'center', width: 150}
                , {field: 'email', title: '电子邮箱', align: 'center', width: 200}
                , {field: 'ordersCount', title: '订单量', align: 'center', width: 100}
                , {field: 'ordersTotalPrice', title: '订单总价', align: 'center', width: 100}
                , {field: 'remark', title: '备注', align: 'center', width: 200}
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
                pageCurr = curr;
            }
        });


        //监听工具条
        table.on('tool(memberTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //删除
                delMember(data, data.id);
            } else if (obj.event === 'edit') {
                //编辑
                edit(data);
            }

        });

        //监听提交
        form.on('submit(memberSubmit)', function (data) {
            formSubmit(data);
            return false;
        });

    });

});

//提交表单
function formSubmit(obj) {
    $.ajax({
        type: "post",
        data: $("#memberForm").serialize(),
        url: "/member/setMember",
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
        $("#name").val(data.name);
        $("#label").val(data.label);
        $("#phone").val(data.phone);
        $("#email").val(data.email);
        $("#ordersCount").val(data.ordersCount);
        $("#ordersTotalPrice").val(data.ordersTotalPrice);
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
        content: $('#setMember'),
        end: function () {
            cleanMember();
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
            name: $("#name2").val(),
            phone: $("#phone2").val(),
            email: $("#email2").val(),
            label: $("#label2").val()
        }
        , page: {
            curr: 1 //从当前页码开始
        }
    });
}

function reset() {
    $("#name2").val("");
    $("#phone2").val("");
    $("#email2").val("");
    $("#label2").val("");
}

//删除
function delMember(obj, id) {
    if (null != id) {
        layer.confirm('您确定要删除吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/member/delete", {"id": id}, function (data) {
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


function cleanMember() {
}

