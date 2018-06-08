package com.blade.demo.route.api;

public class Rest {


        private String suffix;
        private String method;
        private String description;
        private String param;

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
