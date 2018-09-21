<%--
  Created by IntelliJ IDEA.
  User: Da
  Date: 2018/9/19
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>回款情况图表</title>
    <!-- Bootstrap3.3.7 核心 CSS 文件 -->
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="/static/js/html5shiv.min.js"/>
    <script type="text/javascript" src="/static/js/respond.min.js"/>
    <![endif]-->
    <!-- jquery 核心 JS 文件 -->
    <script type="text/javascript" src="/static/jquery/jquery-1.12.4.min.js"></script>
    <!-- ECharts 核心 JS 文件 -->
    <script type="text/javascript" src="/static/echarts/echarts.js"></script>
</head>
<body>

<ul class="nav nav-tabs">
    <li><a href="/excel/imExcel">上传</a></li>
    <li><a href="/excel/List">合同列表</a></li>
    <li><a href="/excel/checkCA">合同总金额</a></li>
    <li class="active"><a href="/excel/checkRS">回款详情</a></li>
</ul>


<!-- 显示Echarts图表 -->
<div style="height:400px;min-height:100px;margin:0 auto;" id="RS"></div>

<script type="text/javascript">
    $(function() {
        //初始化echarts实例
        var RSChart = echarts.init(document.getElementById('RS'));

        // 指定图表的配置项和数据
        var option = {

            color: [	//颜色
                '#3398DB',
                '#003366',
                '#FFDC35',
                '#53FF53',
                '#e5323e'
            ],

            tooltip: {
                trigger: 'axis',   //坐标轴触发提示框，多用于柱状、折线图中
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },

            legend: {
                data: ['2015年回款', '2016年回款', '2017年回款', '2018年回款', '合计回款']
            },

            toolbox: {      //工具栏
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },

            calculable: true,   //图表计算

            // 指定图表的配置项和数据
            title: {
                text: '回款情况'     //图表标题
            },

            dataZoom: [
                {
                    type: 'slider',	    //支持鼠标滚轮缩放
                    start: 0,			//默认数据初始缩放范围为10%到90%
                    end: 100,
                    handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                    handleSize: '80%',
                    show:true
                },
                {
                    type: 'inside',	    //支持单独的滑动条缩放
                    start: 0,			//默认数据初始缩放范围为10%到90%
                    end: 100,
                }
            ],

            xAxis: [   //X轴
                {
                    type: 'category',
                    data: [],  //先设置数据值为空，后面用Ajax获取动态数据填入
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [   //Y轴
                {
                    type: 'value',
                    name: '回款金额',
                    axisLabel: {
                        formatter: '{value} ¥'	//控制输出格式
                    }
                }
            ],
            series: [  //系列（内容）列表
                {
                    name: '2015年回款',
                    type: 'bar',     //柱状图表示
                    barGap: 0,
                    barWidth: '10%',
                    data: [] ,    //数据值通过Ajax动态获取
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    }
                },
                {
                    name: '2016年回款',
                    type: 'bar',     //柱状图表示
                    barGap: 0,
                    barWidth: '10%',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    data: []     //数据值通过Ajax动态获取
                },
                {
                    name: '2017年回款',
                    type: 'bar',     //柱状图表示
                    barGap: 0,
                    barWidth: '10%',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    data: []     //数据值通过Ajax动态获取
                },
                {
                    name: '2018年回款',
                    type: 'bar',     //柱状图表示
                    barGap: 0,
                    barWidth: '10%',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    data: []     //数据值通过Ajax动态获取
                },
                {
                    name: '合计回款',
                    type: 'bar',     //柱状图表示
                    barGap: 0,
                    barWidth: '10%',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    data: []     //数据值通过Ajax动态获取
                },
            ]
        };
        RSChart.showLoading();  //数据加载完之前先显示一段简单的loading动画


        var recovered_amount2015 = [];    //（存放服务器返回的数据）
        var recovered_amount2016 = [];
        var recovered_amount2017 = [];
        var recovered_amount2018 = [];
        var receivable_total = [];
        var sale_person = [];

        $.ajax({	//使用JQuery内置的Ajax方法
            type: "post",		//post请求方式
            async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "/excel/recordRS",	//请求发送到ShowInfoIndexServlet处
            dataType: "json",		//返回数据形式为json

            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (result != null && result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        //挨个取出回款等值并填入前面声明的回款数组
                        sale_person.push(result[i].salePerson);
                        recovered_amount2015.push(result[i].recoveredAmount2015);
                        recovered_amount2016.push(result[i].recoveredAmount2016);
                        recovered_amount2017.push(result[i].recoveredAmount2017);
                        recovered_amount2018.push(result[i].recoveredAmount2018);
                        receivable_total.push(result[i].receivableTotal);
                    }
                    RSChart.hideLoading();	//隐藏加载动画

                    RSChart.setOption({		//载入数据
                        xAxis: {
                            data: sale_person	//填入X轴数据
                        },
                        series: [	//填入系列（内容）数据
                            {
                                // 根据名字对应到相应的系列
                                name: '2015年回款',
                                data: recovered_amount2015
                            },
                            {
                                // 根据名字对应到相应的系列
                                name: '2016年回款',
                                data: recovered_amount2016
                            },
                            {
                                // 根据名字对应到相应的系列
                                name: '2017年回款',
                                data: recovered_amount2017
                            },
                            {
                                // 根据名字对应到相应的系列
                                name: '2018年回款',
                                data: recovered_amount2018
                            },
                            {
                                // 根据名字对应到相应的系列
                                name: '合计回款',
                                data: receivable_total
                            },
                        ]
                    });

                }
                else {
                    //返回的数据为空时显示提示信息
                    alert("图表请求数据为空，可能服务器暂未录入合同，您可以稍后再试！");
                    RSChart.hideLoading();
                }

            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败，可能是服务器开小差了");
                RSChart.hideLoading();
            }
        })

        RSChart.setOption(option);//载入图表
    });
</script>

<!-- Bootstrap3.3.7 核心 JavaScript 文件 -->
<script type="text/javascript" src="/static/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
