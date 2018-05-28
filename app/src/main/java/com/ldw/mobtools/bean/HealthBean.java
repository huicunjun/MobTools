package com.ldw.mobtools.bean;

import java.util.List;

public class HealthBean {

    /**
     * msg : success
     * result : {"list":[{"content":"　　原料：瘦肉400g，雪梨1个，蜜枣6颗，百合、胡萝卜适量，姜3片。   　　制作： 　　1、瘦肉切粒，雪梨去心切块，胡萝卡切片。雪梨和胡萝卜不用去皮，因为果皮营养很丰富。 　　2、鲜百合洗净并择成一瓣一瓣。挑选新鲜百合时应选用表皮洁白光鲜的。 　　3、在锅中加入5碗水，放入瘦肉、雪梨、蜜枣、胡萝卜、姜片煮开后，再用中小火炖40分钟。 　　4、在起锅前10分钟放入百合。 　　5、加适量盐，即可上桌。","id":19288,"title":"雪梨百合瘦肉汤"},{"content":"　　女性的皮肤娇嫩，对气候很敏感。冬季气候干燥，对女性皮肤损伤较大，为护肤美颜，应多吃些滋补之物。下面介绍几种食疗润颜法。吃滋补品养颜雪梨、芝麻、百合、雪耳、川贝、杏仁、荸荠、栗子等都是最佳的润肺健脾食物，喜欢吃甜品的人，在冬季可以适量吃一些此类食品。 　　例如雪梨炖蜜糖。把雪梨去皮去核与蜜糖共炖1小时，吃梨肉喝糖水，每日1次，效果立见。不加糖也可以，雪梨和百合等本身也有天然甜味，用来煲汤配搭适合材料，老少皆宜。 　　再如用雪梨、雪耳、无花果煨瘦猪肉，或以雪梨百合煨瘦肉，或以荸荠、栗子、无花果等煨鸡肉或瘦肉皆可。现在市场上有真空煲出售，使用起来非常方便。可将材料洗净，在前一晚或早上上班之前，放入煲内，与清水猛火炖30分钟，然后转放在真空煲内焖煮，无需看火，安全又节省能源。数小时以后，再将整煲汤置明火炖30分钟，闻到汤水香味即可食用。 　　吃芝麻生津干燥须生津，缺少津液除了易致咳嗽或便秘外，还会使人皮肤干涩，皱纹增多。所以，在补充水分的基础上，还应多吃植物性脂肪食物，如芝麻、杏仁、芝麻糊等，如果您不能吃甜或不爱吃甜，则可买回瓶装的炒芝麻，煮面或熬粥时撒一些。 　　白菜越吃越白把看似普通的大白菜稍加调配，就是一道驻颜佳肴\u2015\u2015将栗子去壳切成两半，用鸭汤将栗子煨熟后，放入大白菜小火炖熟即可。鸭汤滋阴补肾，栗子健脾肾，白菜补阴润燥，经常食用可以改善阴虚所致的面色黑黄，并可消除皮肤黑斑和黑眼圈，提高美白指数，真正做到越吃越白。 　　吃醋豆延缓衰老新鲜黄豆250克，用醋泡15日后，每日取10粒左右嚼食，可以使皮肤柔嫩，色素变淡。醋豆含有磷脂及多种氨基酸，能促进皮肤细胞的新陈代谢，并有降低胆固醇，改善肝功能及延缓衰老的功效。 　　食疗需要定期及持续进行，它虽不及药物收效快，但效果长久，对人体起着调整和平衡机能的作用。针对季节气候变化，选择适当的食物作为日常饮食，是最根本的美容方法。","id":20428,"title":"雪梨炖蜜糖食疗 润颜美肤"}],"page":1,"total":2}
     * retCode : 200
     */

    private String msg;
    private ResultBean result;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public static class ResultBean {
        /**
         * list : [{"content":"　　原料：瘦肉400g，雪梨1个，蜜枣6颗，百合、胡萝卜适量，姜3片。   　　制作： 　　1、瘦肉切粒，雪梨去心切块，胡萝卡切片。雪梨和胡萝卜不用去皮，因为果皮营养很丰富。 　　2、鲜百合洗净并择成一瓣一瓣。挑选新鲜百合时应选用表皮洁白光鲜的。 　　3、在锅中加入5碗水，放入瘦肉、雪梨、蜜枣、胡萝卜、姜片煮开后，再用中小火炖40分钟。 　　4、在起锅前10分钟放入百合。 　　5、加适量盐，即可上桌。","id":19288,"title":"雪梨百合瘦肉汤"},{"content":"　　女性的皮肤娇嫩，对气候很敏感。冬季气候干燥，对女性皮肤损伤较大，为护肤美颜，应多吃些滋补之物。下面介绍几种食疗润颜法。吃滋补品养颜雪梨、芝麻、百合、雪耳、川贝、杏仁、荸荠、栗子等都是最佳的润肺健脾食物，喜欢吃甜品的人，在冬季可以适量吃一些此类食品。 　　例如雪梨炖蜜糖。把雪梨去皮去核与蜜糖共炖1小时，吃梨肉喝糖水，每日1次，效果立见。不加糖也可以，雪梨和百合等本身也有天然甜味，用来煲汤配搭适合材料，老少皆宜。 　　再如用雪梨、雪耳、无花果煨瘦猪肉，或以雪梨百合煨瘦肉，或以荸荠、栗子、无花果等煨鸡肉或瘦肉皆可。现在市场上有真空煲出售，使用起来非常方便。可将材料洗净，在前一晚或早上上班之前，放入煲内，与清水猛火炖30分钟，然后转放在真空煲内焖煮，无需看火，安全又节省能源。数小时以后，再将整煲汤置明火炖30分钟，闻到汤水香味即可食用。 　　吃芝麻生津干燥须生津，缺少津液除了易致咳嗽或便秘外，还会使人皮肤干涩，皱纹增多。所以，在补充水分的基础上，还应多吃植物性脂肪食物，如芝麻、杏仁、芝麻糊等，如果您不能吃甜或不爱吃甜，则可买回瓶装的炒芝麻，煮面或熬粥时撒一些。 　　白菜越吃越白把看似普通的大白菜稍加调配，就是一道驻颜佳肴\u2015\u2015将栗子去壳切成两半，用鸭汤将栗子煨熟后，放入大白菜小火炖熟即可。鸭汤滋阴补肾，栗子健脾肾，白菜补阴润燥，经常食用可以改善阴虚所致的面色黑黄，并可消除皮肤黑斑和黑眼圈，提高美白指数，真正做到越吃越白。 　　吃醋豆延缓衰老新鲜黄豆250克，用醋泡15日后，每日取10粒左右嚼食，可以使皮肤柔嫩，色素变淡。醋豆含有磷脂及多种氨基酸，能促进皮肤细胞的新陈代谢，并有降低胆固醇，改善肝功能及延缓衰老的功效。 　　食疗需要定期及持续进行，它虽不及药物收效快，但效果长久，对人体起着调整和平衡机能的作用。针对季节气候变化，选择适当的食物作为日常饮食，是最根本的美容方法。","id":20428,"title":"雪梨炖蜜糖食疗 润颜美肤"}]
         * page : 1
         * total : 2
         */

        private int page;
        private int total;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content : 　　原料：瘦肉400g，雪梨1个，蜜枣6颗，百合、胡萝卜适量，姜3片。   　　制作： 　　1、瘦肉切粒，雪梨去心切块，胡萝卡切片。雪梨和胡萝卜不用去皮，因为果皮营养很丰富。 　　2、鲜百合洗净并择成一瓣一瓣。挑选新鲜百合时应选用表皮洁白光鲜的。 　　3、在锅中加入5碗水，放入瘦肉、雪梨、蜜枣、胡萝卜、姜片煮开后，再用中小火炖40分钟。 　　4、在起锅前10分钟放入百合。 　　5、加适量盐，即可上桌。
             * id : 19288
             * title : 雪梨百合瘦肉汤
             */

            private String content;
            private int id;
            private String title;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
