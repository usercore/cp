function getM3PlotData() {
    var data = {
        actina: 'getM3PlotData',
        pageNo: 1,
        pageSize: 45
    };
    var succCallBackFunc = function (res) {
        if (res.erorcd == "000000") {
            var index = 0;
            res.record1.forEach(function (value, key, map) {
                // alert(value.award_num);
                var $tr = $('<tr class="">');
                fileIssue2(value.issue_no, $tr);
                fillAwardNum2(value.award_num, $tr);
                fillSpan2(value.span_value, $tr);
                fillM3(value.span_m3_value, $tr, index, "span_m3_");
                fillSum2(value.sum_value, $tr, index);
                fillM3(value.span_m3_value, $tr, index, ',"sum_m3_"');
                fillSumTail2(value.sum_tail_value, $tr, index);
                fillM3(value.sum_tail_m3_value, $tr, index, "sum_tail_m3_");
                index = index + 1;
            });

            var $tr1 = $('<tr class="omission">');
            $("#chart2").append($tr1);

            res.record2.forEach(function (value, key, map) {
                initLost2(value);
            });

            fillCurrentLost2($tr1);

            var $tr2 = $('<tr class="omission">');
            $("#chart2").append($tr2);
            fillHotCold2($tr2, res.record3);
            drawPage3();
            $("#chart2").append('<tr class="">');
        } else {
            requestError(res);
        }
    }

    // ajax提交数据
    ajax_commit_commonData(data, succCallBackFunc, ajaxError);
}

// 填充期号
function fileIssue2(issue, tr) {
    $("#chart2").append(tr);
    tr.append('<td class="chart-bg-qh">' + issue.substring(issue.length-2) + '</td>');
}

// 填充开奖号码、与开奖号码分布
function fillAwardNum2(awardNum, tr) {
    var awardNums = awardNum.split('/');
    tr.append('<td class="chart-bg-kjhm" id="2_0">' + awardNums[0] + '</td>');
    tr.append('<td class="chart-bg-kjhm" id="2_1">' + awardNums[1] + '</td>');
    tr.append('<td class="chart-bg-kjhm" id="2_2">' + awardNums[2] + '</td>');
    var index = 0;
    for (var i = 1; i <= 6; i++) {
        if (i == awardNums[index]) {
            if (awardNums[index] == awardNums[index + 1]) {// 重号
                tr.append('<td class="chart-bg-hmfb q2 dqhm"><span class="bigred_q">' + i + '</span></td>');
                index = index + 1;
            } else {
                tr.append('<td class="chart-bg-hmfb q2 dqhm"><span class="bulebg">' + i + '</span></td>');
            }
            index = index + 1;
        } else {
            tr.append('<td class="chart-bg-hmfb"></td>');
        }
    }
}

// 填充跨度
function fillSpan2(span, tr) {
    for (var i = 0; i <= 5; i++) {
        if (i == span) {
            tr.append('<td class="chart-bg-c10 blank-c10" id="e2">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6 blank-c6 k3even" id="e2"></td>');
        }
    }
}

// 填充和值
function fillSum2(sum, tr, index) {
    for (var i = 3; i <= 18; i++) {
        if (i == sum) {
            tr.append('<td class="chart-bg-c6 blank-c6" id="a' + index + '">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6"></td>');
        }
    }
}


// 填充和尾
function fillSumTail2(sum_tail, tr, index) {
    for (var i = 0; i <= 10; i++) {
        if (i == sum_tail) {
            tr.append('<td class="chart-bg-c6 blank-c6" id="a' + index + '">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6"></td>');
        }
    }
}

// 填充012路数据
function fillM3(value, tr, index, descr) {
    for (var i = 0; i <= 2; i++) {
        if (i == value) {
            tr.append('<td class="chart-bg-012" id="' + descr + index + '">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-012"></td>');
        }
    }
}

// 填充遗漏
function fillCurrentLost2(tr) {
    numLost2.sort(sortLostNum2);
    sumLost2.sort(sortLostNum2);
    sumTailLost2.sort(sortLostNum2);
    spanLost2.sort(sortLostNum2);
    sumM3Lost2.sort(sortLostNum2);
    sumTailM3Lost2.sort(sortLostNum2);
    spanM3Lost2.sort(sortLostNum2);
    tr.append('<td class="chart-bg-qh" colspan="4">当前遗漏</td>');
    numLost2.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-hmfb" id=num' + value.lost_num + '>' + value.lost_value + '</td>');
    });
    spanLost2.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6 k3even" id=span' + value.lost_num + '>' + value.lost_value + '</td>');
    });
    spanM3Lost2.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6 k3even" id=spanM3' + value.lost_num + '>' + value.lost_value + '</td>');
    });
    sumLost2.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6" id=sum' + value.lost_num + '>' + value.lost_value + '</td>');
    });
    sumM3Lost2.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6" id=sumM3' + value.lost_num + '>' + value.lost_value + '</td>');
    });
    sumTailLost2.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6" id=sumTail' + value.lost_num + '>' + value.lost_value + '</td>');
    });
    sumTailM3Lost2.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6" id=sumTailM3' + value.lost_num + '>' + value.lost_value + '</td>');
    });
}

// 填充冷热号
function fillHotCold2(tr, value) {
    tr.append('<td class="chart-bg-qh" colspan="4">冷热号码</td>');
    for (var i = 0; i < 43; i++) {
        tr.append('<td class="chart-bg-c6" id=hot_2_' + i + '></td>');
    }
    // {"num":"10","lost_type":"0","show_type":"1"}
    value.forEach(function (value, key, map) {
        var startPos = 0;
        if (value.lost_type == '0') {
            startPos = parseInt(startPos) + 15 + parseInt(value.num) - 3;
        } else if (value.lost_type == '1') {
            startPos = parseInt(startPos) + 34 + parseInt(value.num);
        } else if (value.lost_type == '2') {
            startPos = parseInt(startPos) + 6 + parseInt(value.num);
        } else {
            startPos = parseInt(startPos) + parseInt(value.num) - 1;
        }
        // show_type:0冷 1热
        $("#hot_2_" + startPos).html(value.num);
    });

}

var numLost2 = eval('([])');
var sumLost2 = eval('([])');
var sumTailLost2 = eval('([])');
var spanLost2 = eval('([])');
var sumM3Lost2 = eval('([])');
var sumTailM3Lost2 = eval('([])');
var spanM3Lost2 = eval('([])');

function initLost2(value) {
    var temp = {
        "lost_num": value.lost_num,
        "lost_value": value.lost_value
    };
    // console.log("遗漏类型："+value.lost_type+",遗漏值："+temp)
    if (value.lost_type == '0') {
        sumLost2.push(temp);
    } else if (value.lost_type == '1') {
        sumTailLost2.push(temp);
    } else if (value.lost_type == '2') {
        spanLost2.push(temp);
    } else if (value.lost_type == '3') {
        sumM3Lost2.push(temp);
    } else if (value.lost_type == '4') {
        sumTailM3Lost2.push(temp);
    } else if (value.lost_type == '5') {
        spanM3Lost2.push(temp);
    } else if (value.lost_type == '6') {
        numLost2.push(temp);
    }
}

function sortLostValue2(a, b) {
    return b.lost_value - a.lost_value;
}

function sortLostNum2(a, b) {
    return a.lost_num - b.lost_num;
}