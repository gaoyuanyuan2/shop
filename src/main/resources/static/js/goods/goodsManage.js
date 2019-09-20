//'formSelects.render('permissions');
var pageCurr;
var form;

$(function () {

    layui.use('table', function () {
        var table = layui.table;
        form = layui.form;

        tableIns = table.render({
            elem: '#goodsList',
            url: '/goods/getGoodsList',
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

                {fixed: 'left', title: '操作', align: 'center', toolbar: '#optBar', width: 300},
                {type: 'numbers'}
                , {field: 'startName', title: '明星名称', align: 'center', width: 100}
                , {field: 'type', title: '类型', align: 'center', width: 75}
                , {field: 'searchTimes', title: '搜索次数', align: 'center', width: 100}
                , {field: 'keyWord', title: '搜索关键字', align: 'center', width: 120}
                , {field: 'haveBrushSingle', title: '刷单', align: 'center', width: 80}
                , {field: 'evaluationTimes', title: '评价次数', align: 'center', width: 100}
                , {field: 'havePictrueEvaluationTimes', title: '有图评价次数', align: 'center', width: 120}
                , {field: 'salesCount', title: '销量', align: 'center', width: 75}
                , {field: 'brushSingleTimes', title: '刷单数量', align: 'center', width: 100}
                , {field: 'brushSingleCost', title: '刷单成本', align: 'center', width: 100}
                , {field: 'detailsOptimization', title: '详情优化', align: 'center', width: 80}
                , {field: 'haveTogether', title: '聚合', align: 'center', width: 80}
                , {field: 'haveProduct', title: '产品是否制作', align: 'center', width: 80}
                , {field: 'createTime', title: '创建时间', align: 'center', width: 180}
                , {field: 'updateTime', title: '修改时间', align: 'center', width: 180}

            ]],
            id: 'reload'
            ,
            done: function (res, curr, count) {
                $("[data-field='haveBrushSingle']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("是")
                    } else if ($(this).text() == '2') {
                        $(this).text("否")
                    }
                });
                $("[data-field='detailsOptimization']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("是")
                    } else if ($(this).text() == '2') {
                        $(this).text("否")
                    }
                });
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
                $("[data-field='haveTogether']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("是")
                    } else if ($(this).text() == '2') {
                        $(this).text("否")
                    }
                });
                $("[data-field='type']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("手机壳")
                    } else if ($(this).text() == '2') {
                        $(this).text("台历")
                    } else if ($(this).text() == '3') {
                        $(this).text("相册")
                    } else if ($(this).text() == '4') {
                        $(this).text("装饰画")
                    } else if ($(this).text() == '5') {
                        $(this).text("抱枕")
                    } else if ($(this).text() == '6') {
                        $(this).text("海报")
                    } else if ($(this).text() == '7') {
                        $(this).text("相框")
                    } else if ($(this).text() == '8') {
                        $(this).text("T恤")
                    } else if ($(this).text() == '9') {
                        $(this).text("卫衣")
                    } else if ($(this).text() == '10') {
                        $(this).text("帆布袋")
                    }
                });
                $("[data-field='haveProduct']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("是")
                    } else if ($(this).text() == '0') {
                        $(this).text("否")
                    }
                });
                pageCurr = curr;
            }
        });


        //监听工具条
        table.on('tool(goodsTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //删除
                delGoods(data, data.id);
            } else if (obj.event === 'edit') {
                //编辑
                edit(data);
            }else if (obj.event === 'addSearch') {
                addSearch(obj,data.id);
            }else if (obj.event === 'addSale') {
                addSale(obj,data.id);
            }

        });

        //监听提交
        form.on('submit(goodsSubmit)', function (data) {
            formSubmit(data);
            return false;
        });

    });

});

//提交表单
function formSubmit(obj) {
    $.ajax({
        type: "post",
        data: $("#goodsForm").serialize(),
        url: "/goods/setGoods",
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
        $("#searchTimes").val(data.searchTimes);
        $("#keyWord").val(data.keyWord);
        $("#haveBrushSingle2").val(data.haveBrushSingle);
        $("#haveProduct2").val(data.haveProduct);
        $("#evaluationTimes").val(data.evaluationTimes);
        $("#havePictrueEvaluationTimes").val(data.havePictrueEvaluationTimes);
        $("#salesCount").val(data.salesCount);
        $("#brushSingleTimes").val(data.brushSingleTimes);
        $("#brushSingleCost").val(data.brushSingleCost);
        $("#detailsOptimization2").val(data.detailsOptimization);
        $("#haveTogether2").val(data.haveTogether);
        $("#remark").val(data.remark);
        $("#analyze").val(data.analyze);
        $("#haveProduct").val(data.haveProduct);

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
        content: $('#setGoods'),
        end: function () {
            cleanGoods();
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
            haveBrushSingle: $("#haveBrushSingle").val(),
            detailsOptimization: $("#detailsOptimization").val(),
            sort: $('input[name="sort"]:checked').val(),
            haveProduct: $("#haveProduct").val(),
            remark: $("#remark2").val(),
            keyWord: $("#keyWord2").val()
        }
        , page: {
            curr: 1 //从当前页码开始
        }
    });
}

function reset() {
    $("#searchStartName").val("");
    $("#type").val("");
    $("#haveBrushSingle").val("");
    $("#detailsOptimization").val("");
    $("#remark2").val("");
    $("#keyWord2").val("");
    $("#haveProduct").val("");
    $("input[name='sort'][value='0']").attr("checked", true);
}

//删除
function delGoods(obj, id) {
    if (null != id) {
        layer.confirm('您确定要删除吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/goods/delete", {"id": id}, function (data) {
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

function addSearch(obj,id) {
    if (null != id) {
        $.post("/goods/addSearch", {"id": id}, function (data) {
            if (data.code == 1) {
                layer.alert(data.msg, function () {
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
        });
    }else{
        layer.alert("id is null！");
    }
}
function addSale(obj,id) {
    if (null != id) {
        $.post("/goods/addSale", {"id": id}, function (data) {
            if (data.code == 1) {
                layer.alert(data.msg, function () {
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
        });
    }else{
        layer.alert("id is null！");
    }
}

function cleanGoods() {

}

