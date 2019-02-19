function getComprehensiveData() {
    var data = {
        actina: 'getCompositeChartData',
        pageNo: 1,
        pageSize:45
    };
    var succCallBackFunc = function (res) {
        if (res.erorcd == "000000") {
            var index = 0;
            res.record1.forEach(function (value, key, map) {
                // alert(value.award_num);
                var $tr = $('<tr class="">');
                fileIssue1(value.issue_no, $tr);
                fillAwardNum1(value.award_num, $tr);

                /*  <Field name="unit_value" title="个位" type="String" default=""/>
                        <Field name="ten_value" title="十位" type="String" default=""/>
                        <Field name="hundreds_value" title="百位" type="String" default=""/>*/
                fillHundreds(value.hundreds_value, $tr);
                fillHundreds(value.ten_value, $tr);
                fillHundreds(value.unit_value, $tr);


                //fillSum1(value.sum_value, $tr, index);
                fillSpan1(value.span_value, $tr);
                index = index + 1;
            });

            var $tr1 = $('<tr class="omission">');
            $("#chart1").append($tr1);
            /*
                res.record2.forEach(function(value, key, map) {
                    initLost1(value);
                });

                fillCurrentLost1($tr1);

                var $tr3 = $('<tr class="omission">');

                $("#chart1").append($tr3);

                fillHotCold1($tr3, res.record3);*/
            //drawPage2();
            $("#chart1").append('<tr class="">');
        } else {
            requestError(res);
        }
    }

    // ajax提交数据
    ajax_commit_commonData(data, succCallBackFunc, ajaxError);

}

// 填充期号
function fileIssue1(issue, tr) {
    $("#chart1").append(tr);
    tr.append('<td class="chart-bg-qh">' + issue.substring(issue.length-2) + '</td>');
}

// 填充开奖号码、与开奖号码分布
function fillAwardNum1(awardNum, tr) {
    //20180218 增加数组排序；修复因未排序前一位比后一位号码大的情况下，部分开奖号码不显示的问题
    var awardNums = awardNum.split('/').sort();
    tr.append('<td class="chart-bg-kjhm" id="2_0">' + awardNums[0] + '</td>');
    tr.append('<td class="chart-bg-kjhm" id="2_1">' + awardNums[1] + '</td>');
    tr.append('<td class="chart-bg-kjhm" id="2_2">' + awardNums[2] + '</td>');
    var index = 0;
    for (var i = 1; i <= 6; i++) {
        if (i == awardNums[index]) {
            if (awardNums[index] == awardNums[index + 1]) {// 重号
                tr.append('<td class="chart-bg-hmfb q2 dqhm"><span class="bigred_q">'
                    + i + '</span></td>');
                index = index + 1;
            } else {
                tr.append('<td class="chart-bg-hmfb q2 dqhm"><span class="bulebg">'
                    + i + '</span></td>');
            }
            index = index + 1;
        } else {
            tr.append('<td class="chart-bg-hmfb"></td>');
        }
    }
}

//填充百位、十位、个位
function fillHundreds(hundreds, tr) {
    for (var i = 1; i <= 6; i++) {
        if (i == hundreds) {
            tr.append('<td class="chart-bg-c10" id="e2' + i + '">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6 blank-c6 k3even"></td>');
        }
    }
}

// 填充和值
function fillSum1(sum, tr, index) {
    for (var i = 3; i <= 18; i++) {
        if (i == sum) {
            tr.append('<td class="chart-bg-c6 blank-c6" id="a' + index + '">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6"></td>');
        }
    }
}

// 填充跨度
function fillSpan1(span, tr) {
    for (var i = 0; i <= 5; i++) {
        if (i == span) {
            tr.append('<td class="chart-bg-c10 blank-c10" id="com_span_e2_">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6 blank-c6 k3even"></td>');
        }
    }
}

// 填充遗漏
function fillCurrentLost1(tr) {
    numLost1.sort(sortLostNum);
    sumLost1.sort(sortLostNum);
    spanLost1.sort(sortLostNum);
    tr.append('<td class="chart-bg-qh" colspan="4">当前遗漏</td>');
    numLost1.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-hmfb" id=num' + value.lost_num + '>'
            + value.lost_value + '</td>');
    });
    sumLost1.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6" id=sum' + value.lost_num + '>'
            + value.lost_value + '</td>');
    });
    spanLost1.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6 k3even" id=span' + value.lost_num
            + '>' + value.lost_value + '</td>');
    });
}

// 填充冷热号
function fillHotCold1(tr, value) {
    tr.append('<td class="chart-bg-qh" colspan="4">冷热号码</td>');
    for (var i = 0; i < 28; i++) {
        tr.append('<td class="chart-bg-c6" id=hot' + i + '></td>');
    }
    // {"num":"10","lost_type":"0","show_type":"1"}
    value.forEach(function (value, key, map) {
        var startPos = 0;
        if (value.lost_type == '0') {
            startPos = parseInt(startPos) + 6 + parseInt(value.num) - 3;
        } else if (value.lost_type == '2') {
            startPos = parseInt(startPos) + 22 + parseInt(value.num);
        } else {
            startPos = parseInt(startPos) + parseInt(value.num) - 1;
        }
        // show_type:0冷 1热
        $("#hot" + startPos).html(value.num);
    });

}

var numLost1 = eval('([])');
var sumLost1 = eval('([])');
var spanLost1 = eval('([])');

function initLost1(value) {
    var temp = {
        "lost_num": value.lost_num,
        "lost_value": value.lost_value
    };
    if (value.lost_type == '0') {
        sumLost1.push(temp);
    } else if (value.lost_type == '2') {
        spanLost1.push(temp);
    } else if (value.lost_type == '6') {
        numLost1.push(temp);
    }
}

function sortLostValue1(a, b) {
    return b.lost_value - a.lost_value;
}

function sortLostNum1(a, b) {
    return a.lost_num - b.lost_num;
}