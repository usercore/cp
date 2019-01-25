function getBasicData() {
    var data = {
        actina: 'getBasicPlotData',
        pageNo: 1,
        pageSize: 30
    };
    var succCallBackFunc = function (res) {
        if (res.erorcd == "000000") {
            var index = 0;
            res.record1.forEach(function (value, key, map) {
                // alert(value.award_num);
                var $tr = $('<tr class="">');
                fileIssue(value.issue_no, $tr);
                fillAwardNum(value.award_num, $tr);
                fillSum(value.sum_value, $tr, index);
                fillSpan(value.span_value, $tr);
                index = index + 1;
            });

            var $tr1 = $('<tr class="omission">');
            $("#chart").append($tr1);

            res.record2.forEach(function (value, key, map) {
                initLost(value);
            });

            fillCurrentLost($tr1);

            var $tr2 = $('<tr class="omission">');
            $("#chart").append($tr2);
            fillHotCold($tr2, res.record3);
            drawPage1();
            $("#chart").append('<tr class="">');
        } else {
            requestError(res);
        }
    }

    // ajax提交数据
    ajax_commit_commonData(data, succCallBackFunc, ajaxError);

}

// 填充期号
function fileIssue(issue, tr) {
    $("#chart").append(tr);
    tr.append('<td class="chart-bg-qh">' + issue.substring(issue.length-2) + '</td>');
}

// 填充开奖号码、与开奖号码分布
function fillAwardNum(awardNum, tr) {
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

// 填充和值
function fillSum(sum, tr, index) {
    for (var i = 3; i <= 18; i++) {
        if (i == sum) {
            tr.append('<td class="chart-bg-c6 blank-c6" id="a' + index + '">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6"></td>');
        }
    }
}

// 填充跨度
function fillSpan(span, tr) {
    for (var i = 0; i <= 5; i++) {
        if (i == span) {
            tr.append('<td class="chart-bg-c10 blank-c10" id="basic_e2_'+i+'">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6 blank-c6 k3even"></td>');
        }
    }
}

// 填充遗漏
function fillCurrentLost(tr) {
    numLost.sort(sortLostNum);
    sumLost.sort(sortLostNum);
    spanLost.sort(sortLostNum);
    tr.append('<td class="chart-bg-qh" colspan="4">当前遗漏</td>');
    numLost.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-hmfb" id=num' + value.lost_num + '>' + value.lost_value + '</td>');
    });
    sumLost.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6" id=sum' + value.lost_num + '>' + value.lost_value + '</td>');
    });
    spanLost.forEach(function (value, key, map) {
        tr.append('<td class="chart-bg-c6 k3even" id=span' + value.lost_num + '>' + value.lost_value + '</td>');
    });
}

// 填充冷热号
function fillHotCold(tr, value) {
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

var numLost = eval('([])');
var sumLost = eval('([])');
var spanLost = eval('([])');

function initLost(value) {
    var temp = {
        "lost_num": value.lost_num,
        "lost_value": value.lost_value
    };
    if (value.lost_type == '0') {
        sumLost.push(temp);
    } else if (value.lost_type == '2') {
        spanLost.push(temp);
    } else if (value.lost_type == '6') {
        numLost.push(temp);
    }
}

function sortLostValue(a, b) {
    return b.lost_value - a.lost_value;
}

function sortLostNum(a, b) {
    return a.lost_num - b.lost_num;
}