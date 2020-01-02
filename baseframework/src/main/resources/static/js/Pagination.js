/**
 * 用于表格分页
 * @param pageSize
 * @param currentPage
 * @param id
 */
function pagination(pageSize,currentPage,total,table) {
    var trlist = table.parent().find('tr');

    //所有行不可见-除表头以外
    for (var i = 1; i <trlist.length ; i++) {
        trlist[i].style.display = "none";
    }

    var fromIndex = (currentPage-1)*pageSize+1;
    var toIndex = currentPage*pageSize<total?currentPage*pageSize:total;
    for (var i = fromIndex; i <=toIndex ; i++) {
        trlist[i].children[2].innerHTML = normalize(trlist[i].children[2].innerHTML);
        trlist[i].children[3].innerHTML = normalize(trlist[i].children[3].innerHTML)
        //console.log(table.parent.find('tr')[i])
        trlist[i].style.display="";
    }
}

/**
 * 对于过长的信息进行统一化处理...
 * @param str
 * @returns {*}
 */
function normalize(str) {
    if(str.length>10){
        return str.substr(0,10)+'...';
    }else{
        return str;
    }
}