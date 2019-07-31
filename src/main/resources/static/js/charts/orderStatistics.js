
load();
function load() {
    $.ajax({
        type: 'post',
        url: '/charts/order/statistics',
        data: {
            type: $('#type').val(),
            startTime: $('#startTime').val(),
            endTime: $('#endTime').val(),
            countType: $('input[name="countType"]:checked').val()
        },
        async: false,
        success: function (data) {
            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: data.legend
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: data.xAxis
                },
                yAxis: {
                    type: 'value'
                },
                series: data.series
            };

            var orderChart = echarts.init(document.getElementById('orderId'));

            // 使用刚指定的配置项和数据显示图表。
            orderChart.setOption(option);


        }
    });

}

