function getPatternsChartData() {
    var data = {
        actina: 'getPatternsChartData',
        pageNo: 1,
        pageSize: 45
    };
    var succCallBackFunc = function (res) {
        if (res.erorcd == "000000") {
            var index = 0;
            res.record1.forEach(function (value, key, map) {
                // alert(value.award_num);
                var $tr = $('<tr class="">');
                fileIssue3(value.issue_no, $tr);
                fillAwardNum3(value.award_num, $tr);
                fillSpan3(value.span_value, $tr);
                fillSum3(value.sum_value, $tr, index);
                fillM3(value.sum_m3_value, $tr, index, ',"sum_m3_"');
                fillSumBigOrSmall(value.sum_big_small, $tr, index);
                fillSingleDouble(value.sum_single_double, $tr, index);
                fillNumsForm(value.num_form, $tr, index);
                index = index + 1;
            });

            var $tr1 = $('<tr class="omission">');
            $("#chart3").append($tr1);
            //
            // res.record2.forEach(function (value, key, map) {
            //     initLost(value);
            // });
            //
            // fillCurrentLost($tr1);
            //
            // var $tr2 = $('<tr class="omission">');
            // $("#chart").append($tr2);
            // fillHotCold($tr2, res.record3);
            drawPage4();
            $("#chart3").append('<tr class="">');
        } else {
            requestError(res);
        }
    }

    // ajax提交数据
    ajax_commit_commonData(data, succCallBackFunc, ajaxError);

}

// 填充期号
function fileIssue3(issue, tr) {
    $("#chart3").append(tr);
    tr.append('<td class="chart-bg-qh">' + issue.substring(issue.length - 2) + '</td>');
}

// 填充开奖号码、与开奖号码分布
function fillAwardNum3(awardNum, tr) {
    //20180218 增加数组排序；修复因未排序前一位比后一位号码大的情况下，部分开奖号码不显示的问题
    var awardNums = awardNum.split('/').sort();
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
function fillSpan3(span, tr) {
    for (var i = 0; i <= 5; i++) {
        if (i == span) {
            tr.append('<td class="chart-bg-c10 blank-c10" id="pattern_e2_' + i + '">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-c6 blank-c6 k3even"></td>');
        }
    }
}


// 填充和值
function fillSum3(sum, tr, index) {
    tr.append('<td class="chart-bg-c10 blank-c10" id="sum' + index + '">' + sum + '</td>');
}

// 填充012路数据
function fillM3(value, tr, index, descr) {
    for (var i = 0; i <= 2; i++) {
        if (i == value) {
            tr.append('<td class="chart-bg-012" id="m3_' + descr + index + '">' + i + '</td>');
        } else {
            tr.append('<td class="chart-bg-012"></td>');
        }
    }
}

// 填充和值 大小
function fillSumBigOrSmall(value, tr, index) {
    if (value == "小") {
        tr.append('<td class="chart-bg-sbs blank-sbs"></td>');
    }
    tr.append('<td class="chart-bg-sbs blank-sbs" id="bs' + index + '">' + value + '</td>');
    if (value == "大") {
        tr.append('<td class="chart-bg-sbs blank-sbs"></td>');
    }
}

// 填充和值 单双
function fillSingleDouble(value, tr, index) {
    if (value == "单") {
        tr.append('<td class="chart-bg-ssd blank-ssd"></td>');
    }
    tr.append('<td class="chart-bg-ssd blank-ssd" id="sd' + index + '">' + value + '</td>');
    if (value == "双") {
        tr.append('<td class="chart-bg-ssd blank-ssd"></td>');
    }
}

// 填充号码形态
function fillNumsForm(value, tr, index) {
    var three_same = '三同', three_diff = '三不同', two_diff = '二不同', three_line = '三连', two_same = '二同';
    if (value == three_same) {//三同，满足两同，其他置空
        three_diff = '', two_diff = '', three_line = '';
        two_same = '';// 20190221 三同时不再显示两同
    } else if (value == three_diff) {//三不同，满足两不同，其他置空
        three_same = '', three_line = '', two_same = '';
    } else if (value == two_diff || value == two_same) {//两不同，满足两同，其他置空
        three_same = '', three_diff = '', three_line = '';
    } else if (value == three_line) {//三连，满足三不同、两不同，其他置空
        three_same = '', two_same = '';
    }
    tr.append('<td class="chart-bg-ssd blank-ssd" id="thsa' + index + '">' + three_same + '</td>');
    tr.append('<td class="chart-bg-ssd blank-ssd" id="thdi' + index + '">' + three_diff + '</td>');
    // tr.append('<td class="chart-bg-ssd blank-ssd" id="twdi' + index + '">' + two_diff + '</td>');
    tr.append('<td class="chart-bg-ssd blank-ssd" id="thli' + index + '">' + three_line + '</td>');
    tr.append('<td class="chart-bg-ssd blank-ssd" id="twsa' + index + '">' + two_same + '</td>');
}
