/*
used for calculate page height
 */
function refresh() {
    if (window.innerHeight != null) {
        windowHeight = window.innerHeight - 70;
        $(".leftnav").css("height", windowHeight);
        $(".leftnav ul").css("height", windowHeight - 80);
        $(".main-content").css("height", windowHeight);
        $("#page-content").css("height", windowHeight - 42);
    } else {
        windowHeight = document.documentElement.clientHeight - 70;
        $(".leftnav").css("min-height", windowHeight);
        $(".leftnav ul").css("min-height", windowHeight - 80);
        $(".main-content").css("height", windowHeight);
        $("#page-content").css("height", windowHeight - 42);
    }
}

function GgControl(value, row, gglx) {
    if (value == '未发布') {
        return '<button class="button" data-id="' + row.ah + '" ' +
            'value="' + value + '" ' +
            'data-url="' + gglx + '" ' +
            'style="' + ggcolor(value) + '" ' +
            'onmouseover="mouse_over(this)" ' +
            'onmouseout="mouse_out(this)" ' +
            'onclick="addgg(this)">未发布</button>';
    } else if (value == '无') {
        return "<span>无</span>"
    } else {
        return '<button class="button" data-id="' + row.ah + '"' +
            ' value="' + value + '" ' +
            'data-url="' + gglx + '" ' +
            'style="' + ggcolor(value) + '" ' +
            'onmouseover="mouse_over(this)" ' +
            'onmouseout="mouse_out(this)" ' +
            'onclick="readmore(this)">' + value + '</button>'
    }
}

function mouse_over(obj) {
    var value = $(obj).html();
    if (value == '未发布') {
        $(obj).html('发布')
    } else if (value == '未预约') {
        $(obj).html('预约')
    } else {
        $(obj).html('查看')
    }
}

function mouse_out(obj) {
    var value = $(obj).attr('value');
    $(obj).html(value)
}

function addgg(obj) {
    var ah = $(obj).data("id");
    var gglx = $(obj).data("url");
    /**
     * 新增一个tab
     */
    addElement($('#curNav'), encodeURI('/ggmodel?ah=' + ah + '&gglx=' + gglx), '', Gglx(parseInt(gglx)));
}

function addYy(str) {
    var list = str.split(';');
    var ah = list[0];
    var ajmc = list[1];

    $('#glr').empty();
    /**
     * 填入相关值
     */
    $.ajax({
        url: 'getAjDsr',
        method: 'post',
        data: {
            ah: ah
        }, success: function (data) {
            var yglist = data["yg"];
            var bglist = data["bg"];
            $('#yyDialog').find("input").each(function (k, v) {
                var name = $(v).attr('name');
                if (name == 'ah') $(v).val(ah);
                if (name == 'ajmc') $(v).val(ajmc);
            });
            $('#dsr').empty();
            $('#glrDialog').resetForm();
            $('#gldsr').empty();
            for (var i = 0; i < yglist.length; i++) {
                var str = '<div><input type="checkbox" name="lfr" value="' + yglist[i] + '" ' +
                    'style="width: 15px;height:15px;float:left;"/>' +
                    '<label >原告：' + yglist[i] + '</label></div>';
                $('#dsr').append(str);
                $('#gldsr').append('<option value="' + yglist[i] + '">原告：' + yglist[i] + '</option>');
            }
            for (var i = 0; i < bglist.length; i++) {
                var str = '<div><input type="checkbox" name="lfr" value="' + bglist[i] + '" ' +
                    'style="width: 15px;height:15px;float:left;"/>' +
                    '<label >被告：' + bglist[i] + '</label></div>';
                $('#dsr').append(str);
                $('#gldsr').append('<option value="' + bglist[i] + '">被告：' + bglist[i] + '</option>');
            }
            $('#yyDialog').dialog("open");
        }
    })
}

function moreInfo(str) {
    var arr = str.split(',');
    var bh = arr[0];
    var lx = arr[1];
    if (lx == 1) {
        addElement($('#curNav'), '/yyxq?bh=' + bh, '', '预约详情');
    } else {
        addElement($('#curNav'), '/ggxq?bh=' + bh, '', '公告详情');
    }

}

function readmore(obj) {
    var ah = $(obj).data("id");
    var gglx = $(obj).data("url");
    /**
     * 新增一个tab
     */
    addElement($('#curNav'), '/ggxq?ah=' + encodeURI(ah) + '&gglx=' + gglx, '', '公告详情');
}

//js方法：序列化表单
function serializeForm(form) {
    var obj = {};
    $.each(form.serializeArray(), function (index) {
        if (obj[this['name']]) {
            obj[this['name']] = obj[this['name']] + ',' + this['value'];
        } else {
            obj[this['name']] = this['value'];
        }
    });
    return obj;
}

//js方法：序列化表单
function serialize(form) {
    var obj = serializeForm(form);
    var checkbox = $('input[name="lfr"]');
    obj['lfr'] = new Array();
    obj['gldsr'] = new Array();
    obj['lfrlxfs'] = new Array();
    obj['ybagx'] = new Array();
    obj['lfrsfzh'] = new Array();

    checkbox.each(function (k, v) {
        var type = $(v).attr('type');
        if (type == 'checkbox' && $(v).context.checked) {
            obj['lfr'].push($(v).val());
            var gldsr = "";
            var lfrlxfs = "";
            var ybagx = "";
            var lfrsfzh = "";
            var str = $(v).data('target');
            if (str != null && str.length != 0) {
                var arr = str.split(';');
                gldsr = arr[0];
                lfrlxfs = arr[1];
                ybagx = arr[2];
                lfrsfzh = arr[3];
            }
            obj['gldsr'].push(gldsr);
            obj['lfrlxfs'].push(lfrlxfs);
            obj['ybagx'].push(ybagx);
            obj['lfrsfzh'].push(lfrsfzh);
        }
    });
    return obj;
}

/**
 * 1 -- 送达起诉状副本及开庭传票公告
 * 2 -- 开庭排期公告
 * 3 -- 送达判决书公告
 * 4 -- 执行公告
 * 5 -- 失信被执行人公告
 * 6 -- 送达裁定书公告
 * 7 -- 自定义公告
 */
function Gglx(value) {
    var color;
    switch (value) {
        case 1:
            color = '送达起诉状副本及开庭传票公告';
            break;
        case 2:
            color = '开庭公告';
            break;
        case 3:
            color = '送达判决书公告';
            break;
        case 4:
            color = '执行公告';
            break;
        case 5:
            color = '失信被执行人公告';
            break;
        case 6:
            color = '送达裁定书公告';
            break;
        case 7:
            color = '自定义公告';
            break;
        case 8:
            color = '公告';
            break;
    }
    return color;
}

/**
 * 预约状态按钮的显示颜色
 * @param value
 * @returns {*}
 */
function yyztcolor(value) {
    var color;
    switch (value) {
        case '未预约':
            color = 'background-color:#b28750';
            break;
        case '预约中':
            color = 'background-color:#009a44';
            break;
        case '已完成':
            color = 'background-color:#01adff';
            break;
        case '已过期':
            color = 'background-color:#fe0000';
            break;
        case '已作废':
            color = 'background-color:#000000';
            break;
        case '审核成功':
            color = 'background-color:#eb6100';
            break;
        case '审核中':
            color = 'background-color:#0000fe';
            break;
        case '审核失败':
            color = 'background-color:#ff00ff';
            break;
    }
    return color;
}

/**
 * 公告状态按钮的显示颜色
 * @param value
 * @returns {*}
 */
function ggcolor(value) {
    var color;
    switch (value) {
        case '未发布':
            color = 'background-color:#b28750';
            break;
        case '正在发布':
            color = 'background-color:#029745';
            break;
        case '已发布':
            color = 'background-color:#01adff';
            break;
        case '已过期':
            color = 'background-color:#fe0000';
            break;
        case '已作废':
            color = 'background-color:#000000';
            break;
        case '审核成功':
            color = 'background-color:#eb6100';
            break;
        case '审核中':
            color = 'background-color:#0000fe';
            break;
        case '审核失败':
            color = 'background-color:#ff00ff';
            break;
    }
    return color;
}

/***
 * 获得公告内容的模板
 * @returns {string}
 */
function ggnr(obj) {
    var ggnr = '';
    obj.find('div').each(function (k, v) {
        var id = $(v).attr('id');
        if (id != 'title' && id != 'footer' && id != 'tips') {
            $(this).children().each(function (k, v) {
                var tag = $(v)[0].tagName;
                if (tag == 'SPAN') {
                    ggnr += $(v).text();
                } else {
                    ggnr += $(v).val();
                }
            })
            ggnr += '</br>';
        }
    });
    return ggnr;
}

/**
 * 用于获取是否可以点击发布
 */
function isReady(obj) {
    var flag = true;
    obj.find('.require').each(function (k, v) {
        var str = $(v).val();
        var name = $(v).attr('placeholder')
        if (str == '' || str == 'undefined') {
            flag = false;
            alert(name + "不能为空！");
            return false;
        }
    });
    return flag;
}

/**
 *
 * @param table
 */
function getColumnField(table) {
    var datagridTitle = new Array();
    var fields = table.datagrid('getColumnFields');
    for (var i = 0; i < fields.length; i++) {
        var option = table.datagrid('getColumnOption', fields[i]);
        if (option.field != "ck" && option.field != 'id'
            && option.field != 'bh' && option.hidden != true) {
            //过滤勾选框和隐藏列
            datagridTitle.push(option.title);
        }
    }
    return datagridTitle;
}

/**
 * 返回table里面选中行的信息
 * @param table
 * @returns {any[]}
 */
function getSelectedRows(table) {
    var content = new Array();
    var rows = table.datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        var temp = new Array();
        var row = rows[i];
        for (var obj in row) {
            temp.push(row[obj])
        }
        content.push(temp);
    }
    return content;
}

/**
 * 检测是否需要增加
 * @param object
 * @param href
 * @returns {number}
 */
function checkAdd(object, href) {
    var flag = 1;
    object.find('li').each(function (k, v) {
        var url = $(v).find('a').data('url');
        if (url == href) {
            flag = $(v).find('a');
            return false;
        }
    });
    return flag;
}

function hidediv(href) {
    $('.page').each(function (k, v) {
        var url = $(v).data('url');
        if (url == href) {
            $(v).css('display', 'block');
        } else {
            $(v).css('display', 'none');
        }
    });
}

function removeDiv(href) {
    $('.page').each(function (k, v) {
        var url = $(v).data('url');
        if (url == href) {
            $(v).remove();
            return false;
        }
    });
}

function NavModel(href, iconurl, name) {
    var str = "<li class='nav-item curItem'>" +
        "<a class='nav-link active'  data-toggle='tab' data-url=" + href +
        "><span onclick='Navigator(this)' class=\"" + iconurl + "\" data-url=" + href + ">" +
        "<span class='navPadding'>" + name + "</span></span>" +
        "<span class='icon-remove' style='color:grey;' " +
        " onclick='PageRemove(\"" + href + "\")' " +
        "data-url=" + href + "></span></a></li>";
    return str;
}

/**
 * 点击侧边栏在tab栏添加一个标签
 * @param obj
 * @param href
 * @param iconurl
 * @param name
 */
function addElement(obj, href, iconurl, name) {
    //将每个ul标签下的li标签class设置为nav-item
    var flag = checkAdd(obj, href);
    if (flag == 1) {
        obj.find('li').each(function (k, v) {
            var item = $(v).find('a');
            item.removeClass("active");
        });
        obj.append(NavModel(href, iconurl, name));
        $('#loading').css('display', 'block');
        $('#mask').css('display', 'block');
        $.ajax({
            url: href,
            success: function (html) {
                hidediv('');
                $('#page-content').append('<div class="page" style="display: block;" ' +
                    'data-url="' + href + '">' + html + '</div>');
                $('#loading').css('display', 'none');
                $('#mask').css('display', 'none');
            }
        });
    } else {
        //唤醒对应的标签状态为active
        obj.find('li').each(function (k, v) {
            var item = $(v).find('a');
            item.removeClass("active");
        });
        flag.addClass("active");
        hidediv(href);
    }

    //如果tab栏仅有一个标签，则去掉curItem
    var len = obj.find('li').length;
    if (len == 1) {
        $(obj.find('li')[0]).removeClass('curItem');
    }
}

/**
 * 点击tab栏的标签显示相关页面
 * @param obj
 * @constructor
 */
function Navigator(obj) {
    var ul = $('#curNav');
    ul.find('li').each(function (k, v) {
        var item = $(v).find('a');
        item.removeClass("active");
    });
    var href = $(obj).data("url");
    if (href != 'home') {
        $(obj).parent().addClass("active");
    } else {
        $(obj).addClass("active");
        if (href == 'ajlb') {
            $('#ajlb').datagrid('reload')
        } else if (href == 'yycx') {
            $('#t_yycx').datagrid('reload');
        } else if (href == 'ggcx') {
            $("#t_ggcx").datagrid('reload');
        }
    }
    hidediv(href);
}

/**
 * 点击tab标签关闭当前页面
 * @param obj
 * @constructor
 */
function PageRemove(href) {
    removeDiv(href);
    var prev = new Object();
    var tab = new Object();
    $('#curNav').find('li').each(function (k, v) {
        var curr = $(v).find('a');
        curr.removeClass("active");
        if (curr.data('url') == href) {
            tab = $(v);
            return false;
        }
        prev = curr;
    });
    tab.remove();
    if (JSON.stringify(prev) != '{}') {
        prev.addClass("active");
        hidediv(prev.data("url"));
    }
}

//用于检验上传文件类型是否正确以及文件大小是否超出规定范围
function checkFile(formId,options) {
    var file = $(formId).find("input[name='file']");
    var filename = file.val();
    if ("" != filename) {
        var fileType = getFileType(filename);
        var option = options.split("或");
        var num = 0;
        if (option.length > 0) {
            for (var i =0;i<option.length;i++){
                if(fileType==option[i]){
                    continue;
                }else {
                    num++;
                }
            }
            if(num == option.length){
                alert("请上传"+option+"格式文件");
                return false;
            }
        } else {
            var fileSize = file[0].files[0].size / 1024 / 1024;
            if (fileSize > 1000) {
                alert("可上传文件最大为1000MB")
                return false;
            }
        }
        // 阻止表单提交
        $(formId).ajaxSubmit(function (data) {
            $('#message').html(data);
        });
        //表单重置
        $(formId).resetForm();
        //重新刷新
        $('#spgl').datagrid('reload');
        return false;
    } else {
        alert("请选择文件");
        return false;
    }
}

/***
 * 用于判断文件类型
 * @param filePath
 * @returns {string}
 */
function getFileType(filePath) {
    var startIndex = filePath.lastIndexOf(".");
    if (startIndex != -1) {
        return filePath.substr(startIndex + 1, filePath.length);
    } else return "";
}

function readYy(ah) {
    addElement($('#curNav'), '/yyxq?ah=' + encodeURI(ah), '', '预约详情')
}

function findPage() {
    var obj = null;
    $('.page').each(function (k, v) {
        var display = $(v).css('display');
        if (display != 'none') {
            obj = $(v);
            return false;
        }
    });
    return obj;
}

function yy_sh(bh, yyzt) {
    console.log(bh + "," + yyzt)
    $.ajax({
        url: 'updateYy',
        method: 'post',
        data: {
            "bh": bh,
            "zt": yyzt
        },
        success: function () {
            $.messager.show({
                title: "提示信息",
                msg: "该条预约记录已成功审核！"
            });
            $('#yysh').datagrid('reload');
        }
    })
}

function gg_sh(bh, ggzt) {
    $.ajax({
        url: 'updategg',
        data: {
            "id": parseInt(bh),
            "ggzt": parseInt(ggzt)
        },
        method: 'post',
        success: function () {
            $.messager.show({
                title: "提示信息",
                msg: "该条公告记录已成功审核！"
            })
            $('#ggsh').datagrid('reload')
        }
    })
}

function browserIsIe() {
    if (!!window.ActiveXObject || "ActiveXObject" in window) {
        return true;
    } else {
        return false;
    }
}

/**
 * 判断联系方式是否合法
 * @param lxfs
 * @returns {boolean}
 */
function checkLxfs(lxfs) {
    if (lxfs.length == 11) {
        var reg = /^[0-9]+.?[0-9]*$/;
        if (reg.test(lxfs)) {
            return true;
        } else {
            return false;
        }
    }
    return false;
}

/**
 * 校验身份证号是否合法
 * @param sfzh
 * @returns {boolean}
 */
function checkSfzh(sfzh) {
    var arrExp = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 8, 10, 58, 4, 2]; //加权和
    var arrValid = [1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2];  //校验码
    var reg = /^\d{17}\d|x$/i;
    if (reg.test(sfzh)) {
        var sum = 0, idx;
        for (var i = 0; i < sfzh.length - 1; i++) {
            sum += parseInt(sfzh.substr(i, 1), 10) * arrExp[i];
        }
        idx = sum % 11;
        //检测第18位是否与校验码相等
        return arrValid[idx] == sfzh.substr(17, 1).toUpperCase();
    } else {
        return false;
    }
}