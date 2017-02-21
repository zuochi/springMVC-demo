/**
 * Copyright (c) 2016-2017 Tommy Lau
 */
var DataUtils = {
	/**
	 * 取本月第一天的日期
	 */
	getThisMonth1stDateStr : function() {
		var dateStr;
		var myDate = new Date();
		var year = myDate.getFullYear(); // 获取完整的年份(4位,1970-????)
		var month = myDate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
		var day = "01";
	
		dateStr = year + "-";
		if (month < 10) {
			dateStr = dateStr + "0" + month + "-";
		} else {
			dateStr = dateStr + month + "-";
		}
		return dateStr + day;
	},
	
	/**
	 * 上月第一天
	 */
    getLastMonthFirstDateStr :function(){
        var oDate = new Date();
            oDate.setDate(1);
            oDate.setMonth(oDate.getMonth()-1);
        return oDate.getFullYear()+"-"+doubleNumber(oDate.getMonth()+1)+"-"+doubleNumber(oDate.getDate());
    },

    /**
     * 上月最后一天
     */
    getLastMonthLastDate :function(){
        var oDate = new Date();
            oDate.setDate(0);
        return oDate.getFullYear()+"-"+doubleNumber(oDate.getMonth()+1)+"-"+doubleNumber(oDate.getDate());
    },
	
	/**
	 * 取昨天的日期
	 */
	getYesterdayDateStr : function() {
		var dateStr;
		var myDate = new Date();
		var year = myDate.getFullYear(); // 获取完整的年份(4位,1970-????)
		var month = myDate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
		var day = myDate.getDate() - 1; // 获取当前日(1-31)
	
		dateStr = year + "-";
		if (month < 10) {
			dateStr = dateStr + "0" + month + "-";
		} else {
			dateStr = dateStr + month + "-";
		}
		if (day < 10) {
			dateStr = dateStr + "0" + day;
		} else {
			dateStr = dateStr + day;
		}
		return dateStr;
	},
	
	/**
	 * 取今天的日期
	 */
	getTodayDateStr : function() {
		var dateStr;
		var myDate = new Date();
		var year = myDate.getFullYear(); // 获取完整的年份(4位,1970-????)
		var month = myDate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
		var day = myDate.getDate(); // 获取当前日(1-31)
	
		dateStr = year + "-";
		if (month < 10) {
			dateStr = dateStr + "0" + month + "-";
		} else {
			dateStr = dateStr + month + "-";
		}
		if (day < 10) {
			dateStr = dateStr + "0" + day;
		} else {
			dateStr = dateStr + day;
		}
		return dateStr;
	},
	
	/**
	 * 取下一天的日期
	 */
	getNextDateStr : function() {
		var dateStr;
		var myDate = new Date();
		var year = myDate.getFullYear(); // 获取完整的年份(4位,1970-????)
		var month = myDate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
		var day = myDate.getDate() + 1; // 获取当前日(1-31)
	
		dateStr = year + "-";
		if (month < 10) {
			dateStr = dateStr + "0" + month + "-";
		} else {
			dateStr = dateStr + month + "-";
		}
		if (day < 10) {
			dateStr = dateStr + "0" + day;
		} else {
			dateStr = dateStr + day;
		}
		return dateStr;
	},
	
	/**
	 * 本周第一天
	 */
    getThisWeekFirstDateStr : function(){
        var oDate = new Date(),
            day = oDate.getDay();
            switch(day){
                case 0 : 
                    oDate.setDate(oDate.getDate()-6);
                    break;
                default :
                    oDate.setDate(oDate.getDate()-day+1);
                    break;
            }
        return oDate.getFullYear()+"-"+doubleNumber(oDate.getMonth()+1)+"-"+doubleNumber(oDate.getDate());
    },

    /**
     * 本周最后一天
     */
    getThisWeekLastDateStr : function (){
        var oDate = new Date(),
            day = oDate.getDay();
            if(day!=0){
                oDate.setDate(oDate.getDate()-day+7+1);
            }
        return oDate.getFullYear()+"-"+doubleNumber(oDate.getMonth()+1)+"-"+doubleNumber(oDate.getDate());
    }
}

//一位数转两位数
function doubleNumber(num){
    var num = parseInt(num);
    return num > 9 ? num : "0"+num;
};