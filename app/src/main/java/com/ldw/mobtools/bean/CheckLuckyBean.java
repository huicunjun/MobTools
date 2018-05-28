package com.ldw.mobtools.bean;

public class CheckLuckyBean {

    /**
     * msg : success
     * result : {"conclusion":"时来运转，事事如意，功成名就，富贵自来吉"}
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
         * conclusion : 时来运转，事事如意，功成名就，富贵自来吉
         */

        private String conclusion;

        public String getConclusion() {
            return conclusion;
        }

        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
        }
    }
}
