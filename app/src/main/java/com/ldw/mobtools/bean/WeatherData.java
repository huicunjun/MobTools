package com.ldw.mobtools.bean;

import java.util.List;

public class WeatherData {

    /**
     * msg : success
     * result : [{"airCondition":"良","city":"北京","coldIndex":"低发期","updateTime":"20150908153820","date":"2015-09-08","distrct":"门头沟","dressingIndex":"短袖类","exerciseIndex":"适宜","future":[{"date":"2015-09-09","dayTime":"阵雨","night":"阴","temperature":"24°C/18°C","week":"星期三","wind":"无持续风向小于3级"},{"date":"2015-09-10","dayTime":"阵雨","night":"阵雨","temperature":"22°C/15°C","week":"星期四","wind":"无持续风向小于3级"},{"date":"2015-09-11","dayTime":"阴","night":"晴","temperature":"23°C/15°C","week":"星期五","wind":"北风3～4级无持续风向小于3级"},{"date":"2015-09-12","dayTime":"晴","night":"晴","temperature":"26°C/13°C","week":"星期六","wind":"北风3～4级无持续风向小于3级"},{"date":"2015-09-13","dayTime":"晴","night":"晴","temperature":"27°C/16°C","week":"星期日","wind":"无持续风向小于3级"},{"date":"2015-09-14","dayTime":"晴","night":"多云","temperature":"27°C/16°C","week":"星期一","wind":"无持续风向小于3级"},{"date":"2015-09-15","dayTime":"少云","night":"晴","temperature":"26°C/14°C","week":"星期二","wind":"南风3级南风2级"},{"date":"2015-09-16","dayTime":"局部多云","night":"少云","temperature":"26°C/15°C","week":"星期三","wind":"南风3级南风2级"},{"date":"2015-09-17","dayTime":"阴天","night":"局部多云","temperature":"26°C/15°C","week":"星期四","wind":"东南风2级"}],"humidity":"湿度：46%","province":"北京","sunset":"18:37","sunrise":"05:49","temperature":"25℃","time":"14:35","washIndex":"不适宜","weather":"多云","week":"周二","wind":"南风2级"}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * airCondition : 良
         * city : 北京
         * coldIndex : 低发期
         * updateTime : 20150908153820
         * date : 2015-09-08
         * distrct : 门头沟
         * dressingIndex : 短袖类
         * exerciseIndex : 适宜
         * future : [{"date":"2015-09-09","dayTime":"阵雨","night":"阴","temperature":"24°C/18°C","week":"星期三","wind":"无持续风向小于3级"},{"date":"2015-09-10","dayTime":"阵雨","night":"阵雨","temperature":"22°C/15°C","week":"星期四","wind":"无持续风向小于3级"},{"date":"2015-09-11","dayTime":"阴","night":"晴","temperature":"23°C/15°C","week":"星期五","wind":"北风3～4级无持续风向小于3级"},{"date":"2015-09-12","dayTime":"晴","night":"晴","temperature":"26°C/13°C","week":"星期六","wind":"北风3～4级无持续风向小于3级"},{"date":"2015-09-13","dayTime":"晴","night":"晴","temperature":"27°C/16°C","week":"星期日","wind":"无持续风向小于3级"},{"date":"2015-09-14","dayTime":"晴","night":"多云","temperature":"27°C/16°C","week":"星期一","wind":"无持续风向小于3级"},{"date":"2015-09-15","dayTime":"少云","night":"晴","temperature":"26°C/14°C","week":"星期二","wind":"南风3级南风2级"},{"date":"2015-09-16","dayTime":"局部多云","night":"少云","temperature":"26°C/15°C","week":"星期三","wind":"南风3级南风2级"},{"date":"2015-09-17","dayTime":"阴天","night":"局部多云","temperature":"26°C/15°C","week":"星期四","wind":"东南风2级"}]
         * humidity : 湿度：46%
         * province : 北京
         * sunset : 18:37
         * sunrise : 05:49
         * temperature : 25℃
         * time : 14:35
         * washIndex : 不适宜
         * weather : 多云
         * week : 周二
         * wind : 南风2级
         */

        private String airCondition;
        private String city;
        private String coldIndex;
        private String updateTime;
        private String date;
        private String distrct;
        private String dressingIndex;
        private String exerciseIndex;
        private String humidity;
        private String province;
        private String sunset;
        private String sunrise;
        private String temperature;
        private String time;
        private String washIndex;
        private String weather;
        private String week;
        private String wind;
        private List<FutureBean> future;

        public String getAirCondition() {
            return airCondition;
        }

        public void setAirCondition(String airCondition) {
            this.airCondition = airCondition;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getColdIndex() {
            return coldIndex;
        }

        public void setColdIndex(String coldIndex) {
            this.coldIndex = coldIndex;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDistrct() {
            return distrct;
        }

        public void setDistrct(String distrct) {
            this.distrct = distrct;
        }

        public String getDressingIndex() {
            return dressingIndex;
        }

        public void setDressingIndex(String dressingIndex) {
            this.dressingIndex = dressingIndex;
        }

        public String getExerciseIndex() {
            return exerciseIndex;
        }

        public void setExerciseIndex(String exerciseIndex) {
            this.exerciseIndex = exerciseIndex;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWashIndex() {
            return washIndex;
        }

        public void setWashIndex(String washIndex) {
            this.washIndex = washIndex;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class FutureBean {
            /**
             * date : 2015-09-09
             * dayTime : 阵雨
             * night : 阴
             * temperature : 24°C/18°C
             * week : 星期三
             * wind : 无持续风向小于3级
             */

            private String date;
            private String dayTime;
            private String night;
            private String temperature;
            private String week;
            private String wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayTime() {
                return dayTime;
            }

            public void setDayTime(String dayTime) {
                this.dayTime = dayTime;
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }
        }
    }
}

    //获取未来天气JSON数组
/*
    private void initFutureDate(JSONObject result) throws JSONException {

        JSONArray future_arry = result.getJSONArray("future");
        //遍历取得JSONArray各个JSon
        JSONObject future = new JSONObject();

*/
/*
        //第n次
        JSONArray future
        for (int i = 0; i < future_arry.length(); i++) {
            map = new HashMap<>();
            map.put("date",future.getString("data"));
            future_date.add(map);
        }
*//*


    }
*/

    //获取当天天气
/*
    private JSONObject initTodayata(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        String msg = (String) jsonObject.get("msg");      //获得请求结果
        String retCode = (String) jsonObject.get("retCode");    //返回码
        JSONArray result_arry = jsonObject.getJSONArray("result");//第一次解析结果的json数组
        JSONObject result = new JSONObject();

        //遍历取得JSONArray各个Json
        for (int i =0 ; i<result_arry.length();i++){
            result = result_arry.getJSONObject(i);
        }
        //当天天气赋值
        this.airCondition = result.getString("airCondition");
        this.exerciseIndex = result.getString("exerciseIndex");
        this.date = result.getString("date");
        this.dayTime = result.getString("dayTime");
        this.night = result.getString("night");
        this.temperature = result.getString("temperature");

        //返回第一次解析的JSON对象
        return result;
    }
*/



/**
 * {"msg":"success","result":[{"airCondition":"优","city":"珠海","coldIndex":"易发期","date":"2018-05-21","distrct":"珠海","dressingIndex":"薄短袖类","exerciseIndex":"不适宜","future":[{"date":"2018-05-21","dayTime":"多云","night":"多云","temperature":"32°C / 26°C","week":"今天","wind":"微风 小于3级"},{"date":"2018-05-22","dayTime":"多云","night":"多云","temperature":"32°C / 26°C","week":"星期二","wind":"微风 小于3级"}],"humidity":"湿度：73%","pollutionIndex":"18","province":"广东","sunrise":"05:52","sunset":"18:54","temperature":"30℃","time":"18:53","updateTime":"20180521190724","washIndex":"","weather":"晴","week":"周一","wind":"东南风3级"}],"retCode":"200"}

 */
    //
    /**
     * 名称	类型	说明
     retCode	string	返回码
     msg	string	返回说明
     result	string	返回结果集
     airCondition	string	空气质量
     city	string	城市
     coldIndex	string	感冒指数
     updateTime	string	更新时间
     date	string	日期
     distrct	string	区县
     dressingIndex	string	穿衣指数
     humidity	string	湿度
     pollutionIndex	string	空气质量指数
     province	string	省份
     sunset	string	日落时间
     sunrise	string	日出时间
     temperature	string	温度
     time	string	时间
     washIndex	string	洗车指数
     weather	string	天气
     week	string	星期
     wind	string	风向
     exerciseIndex	string	运动指数
     dayTime	string	白天天气
     night	string	晚上天气
     */
    /**
     *                           {
     "msg": "success",
     "result": [
     {
     "airCondition": "良",
     "city": "北京",
     "coldIndex": "低发期",
     "updateTime": "20150908153820",
     "date": "2015-09-08",
     "distrct": "门头沟",
     "dressingIndex": "短袖类",
     "exerciseIndex": "适宜",
     "future": [
     {
     "date": "2015-09-09",
     "dayTime": "阵雨",
     "night": "阴",
     "temperature": "24°C/18°C",
     "week": "星期三",
     "wind": "无持续风向小于3级"
     },
     {
     "date": "2015-09-10",
     "dayTime": "阵雨",
     "night": "阵雨",
     "temperature": "22°C/15°C",
     "week": "星期四",
     "wind": "无持续风向小于3级"
     },
     {
     "date": "2015-09-11",
     "dayTime": "阴",
     "night": "晴",
     "temperature": "23°C/15°C",
     "week": "星期五",
     "wind": "北风3～4级无持续风向小于3级"
     },
     {
     "date": "2015-09-12",
     "dayTime": "晴",
     "night": "晴",
     "temperature": "26°C/13°C",
     "week": "星期六",
     "wind": "北风3～4级无持续风向小于3级"
     },
     {
     "date": "2015-09-13",
     "dayTime": "晴",
     "night": "晴",
     "temperature": "27°C/16°C",
     "week": "星期日",
     "wind": "无持续风向小于3级"
     },
     {
     "date": "2015-09-14",
     "dayTime": "晴",
     "night": "多云",
     "temperature": "27°C/16°C",
     "week": "星期一",
     "wind": "无持续风向小于3级"
     },
     {
     "date": "2015-09-15",
     "dayTime": "少云",
     "night": "晴",
     "temperature": "26°C/14°C",
     "week": "星期二",
     "wind": "南风3级南风2级"
     },
     {
     "date": "2015-09-16",
     "dayTime": "局部多云",
     "night": "少云",
     "temperature": "26°C/15°C",
     "week": "星期三",
     "wind": "南风3级南风2级"
     },
     {
     "date": "2015-09-17",
     "dayTime": "阴天",
     "night": "局部多云",
     "temperature": "26°C/15°C",
     "week": "星期四",
     "wind": "东南风2级"
     }
     ],
     "humidity": "湿度：46%",
     "province": "北京",
     "sunset": "18:37",
     "sunrise": "05:49",
     "temperature": "25℃",
     "time": "14:35",
     "washIndex": "不适宜",
     "weather": "多云",
     "week": "周二",
     "wind": "南风2级"
     }
     ],
     "retCode": "200"
     }

     */




/**
 *   "date": "2015-09-09",
 "dayTime": "阵雨",
 "night": "阴",
 "temperature": "24°C/18°C",
 "week": "星期三",
 "wind": "无持续风向小于3级"
 */