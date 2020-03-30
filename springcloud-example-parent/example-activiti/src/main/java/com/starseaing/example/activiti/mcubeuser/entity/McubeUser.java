package com.starseaing.example.activiti.mcubeuser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 通过插件自动生成
 *
 * @author chentc
 * @since 2020/3/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class McubeUser {

    /**
     * __typename : Account
     * id : aqpJRGfSEzXD09D2vEw
     * creatorId : syssa
     * createDate : 1582880341936
     * modifierId : syssa
     * modifyDate : 1582881576290
     * username : zhangs
     * name : 张三
     * type : 1
     * idCardNumber : null
     * sex : 1
     * email : null
     * mobile : null
     * sortNo : 0
     * enabled : 1
     * delFlag : 0
     * admin : null
     * modifier : {"__typename":"Account","id":"syssa","creatorId":null,"createDate":null,"modifierId":null,"modifyDate":null,"username":null,"name":"超级管理员","type":null,"idCardNumber":null,"sex":null,"email":null,"mobile":null,"sortNo":null,"enabled":null,"delFlag":null,"admin":null}
     * info : {"__typename":"AccountInfo","id":"aqpJRGfSEzXD09D2vEw","creatorId":"syssa","createDate":1582880341969,"modifierId":"syssa","modifyDate":1582880341969,"cardType":null,"cardNumber":null,"birthday":null,"address":null,"telephone":null,"politics":null,"nation":null,"education":null,"avatar":null,"employDate":null,"employStatus":null,"jobNumber":null}
     * units : [{"__typename":"AccountUnit","id":"AIis5zhkiBbzH76tIHf","creatorId":"syssa","createDate":1582880342026,"accountId":"aqpJRGfSEzXD09D2vEw","unitId":"f0rWaW5qY2ST2CMiArx","unit":{"__typename":"Dept","id":"f0rWaW5qY2ST2CMiArx","creatorId":"syssa","createDate":1582702347604,"modifierId":"syssa","modifyDate":1582702347604,"name":"南宁市政府","shortName":"政府","type":"1","areaId":"Fk1QwWLzuEeloJar7VY","unitId":"f0rWaW5qY2ST2CMiArx","parentId":"root","directParentId":"root","creditNumber":null,"leader":null,"telephone":null,"address":null,"fax":null,"sortNo":0,"delFlag":"0"},"rankId":null,"defaultFlag":"1","sortNo":1,"depts":[{"__typename":"AccountDept","id":"4gNwZ3rh3I9OasL55mE","creatorId":"syssa","createDate":1582880342003,"accountId":"aqpJRGfSEzXD09D2vEw","unitId":"f0rWaW5qY2ST2CMiArx","unit":{"__typename":"Dept","id":"f0rWaW5qY2ST2CMiArx","creatorId":"syssa","createDate":1582702347604,"modifierId":"syssa","modifyDate":1582702347604,"name":"南宁市政府","shortName":"政府","type":"1","areaId":"Fk1QwWLzuEeloJar7VY","unitId":"f0rWaW5qY2ST2CMiArx","parentId":"root","directParentId":"root","creditNumber":null,"leader":null,"telephone":null,"address":null,"fax":null,"sortNo":0,"delFlag":"0"},"deptId":"f0rWaW5qY2ST2CMiArx","dept":{"__typename":"Dept","id":"f0rWaW5qY2ST2CMiArx","creatorId":"syssa","createDate":1582702347604,"modifierId":"syssa","modifyDate":1582702347604,"name":"南宁市政府","shortName":"政府","type":"1","areaId":"Fk1QwWLzuEeloJar7VY","unitId":"f0rWaW5qY2ST2CMiArx","parentId":"root","directParentId":"root","creditNumber":null,"leader":null,"telephone":null,"address":null,"fax":null,"sortNo":0,"delFlag":"0"},"postId":"CUEaKbLqQEa8SFhurio","post":{"__typename":"Post","id":"CUEaKbLqQEa8SFhurio","creatorId":"syssa","createDate":1582702393676,"modifierId":"syssa","modifyDate":1582702393676,"name":"市长","code":"shizhang","unitId":"f0rWaW5qY2ST2CMiArx","sortNo":1,"description":null},"defaultFlag":"1","sortNo":1}]}]
     * roles : []
     */

    private String __typename;
    private String id;
    private String creatorId;
    private long createDate;
    private String modifierId;
    private long modifyDate;
    private String username;
    private String name;
    private String type;
    private Object idCardNumber;
    private String sex;
    private Object email;
    private Object mobile;
    private int sortNo;
    private String enabled;
    private String delFlag;
    private Object admin;
    private ModifierBean modifier;
    private InfoBean info;
    private List<UnitsBean> units;
    private List<McubeRole> roles;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifierBean {
        /**
         * __typename : Account
         * id : syssa
         * creatorId : null
         * createDate : null
         * modifierId : null
         * modifyDate : null
         * username : null
         * name : 超级管理员
         * type : null
         * idCardNumber : null
         * sex : null
         * email : null
         * mobile : null
         * sortNo : null
         * enabled : null
         * delFlag : null
         * admin : null
         */

        private Object delFlag;
        private Object admin;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InfoBean {
        /**
         * __typename : AccountInfo
         * id : aqpJRGfSEzXD09D2vEw
         * creatorId : syssa
         * createDate : 1582880341969
         * modifierId : syssa
         * modifyDate : 1582880341969
         * cardType : null
         * cardNumber : null
         * birthday : null
         * address : null
         * telephone : null
         * politics : null
         * nation : null
         * education : null
         * avatar : null
         * employDate : null
         * employStatus : null
         * jobNumber : null
         */

        private String __typename;
        private String id;
        private String creatorId;
        private long createDate;
        private String modifierId;
        private long modifyDate;
        private String cardType;
        private String cardNumber;
        private String birthday;
        private String address;
        private String telephone;
        private String politics;
        private String nation;
        private String education;
        private String avatar;
        private String employDate;
        private String employStatus;
        private String jobNumber;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitsBean {
        /**
         * __typename : AccountUnit
         * id : AIis5zhkiBbzH76tIHf
         * creatorId : syssa
         * createDate : 1582880342026
         * accountId : aqpJRGfSEzXD09D2vEw
         * unitId : f0rWaW5qY2ST2CMiArx
         * unit : {"__typename":"Dept","id":"f0rWaW5qY2ST2CMiArx","creatorId":"syssa","createDate":1582702347604,"modifierId":"syssa","modifyDate":1582702347604,"name":"南宁市政府","shortName":"政府","type":"1","areaId":"Fk1QwWLzuEeloJar7VY","unitId":"f0rWaW5qY2ST2CMiArx","parentId":"root","directParentId":"root","creditNumber":null,"leader":null,"telephone":null,"address":null,"fax":null,"sortNo":0,"delFlag":"0"}
         * rankId : null
         * defaultFlag : 1
         * sortNo : 1
         * depts : [{"__typename":"AccountDept","id":"4gNwZ3rh3I9OasL55mE","creatorId":"syssa","createDate":1582880342003,"accountId":"aqpJRGfSEzXD09D2vEw","unitId":"f0rWaW5qY2ST2CMiArx","unit":{"__typename":"Dept","id":"f0rWaW5qY2ST2CMiArx","creatorId":"syssa","createDate":1582702347604,"modifierId":"syssa","modifyDate":1582702347604,"name":"南宁市政府","shortName":"政府","type":"1","areaId":"Fk1QwWLzuEeloJar7VY","unitId":"f0rWaW5qY2ST2CMiArx","parentId":"root","directParentId":"root","creditNumber":null,"leader":null,"telephone":null,"address":null,"fax":null,"sortNo":0,"delFlag":"0"},"deptId":"f0rWaW5qY2ST2CMiArx","dept":{"__typename":"Dept","id":"f0rWaW5qY2ST2CMiArx","creatorId":"syssa","createDate":1582702347604,"modifierId":"syssa","modifyDate":1582702347604,"name":"南宁市政府","shortName":"政府","type":"1","areaId":"Fk1QwWLzuEeloJar7VY","unitId":"f0rWaW5qY2ST2CMiArx","parentId":"root","directParentId":"root","creditNumber":null,"leader":null,"telephone":null,"address":null,"fax":null,"sortNo":0,"delFlag":"0"},"postId":"CUEaKbLqQEa8SFhurio","post":{"__typename":"Post","id":"CUEaKbLqQEa8SFhurio","creatorId":"syssa","createDate":1582702393676,"modifierId":"syssa","modifyDate":1582702393676,"name":"市长","code":"shizhang","unitId":"f0rWaW5qY2ST2CMiArx","sortNo":1,"description":null},"defaultFlag":"1","sortNo":1}]
         */

        private List<DeptsBean> depts;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class UnitBean {
            /**
             * __typename : Dept
             * id : f0rWaW5qY2ST2CMiArx
             * creatorId : syssa
             * createDate : 1582702347604
             * modifierId : syssa
             * modifyDate : 1582702347604
             * name : 南宁市政府
             * shortName : 政府
             * type : 1
             * areaId : Fk1QwWLzuEeloJar7VY
             * unitId : f0rWaW5qY2ST2CMiArx
             * parentId : root
             * directParentId : root
             * creditNumber : null
             * leader : null
             * telephone : null
             * address : null
             * fax : null
             * sortNo : 0
             * delFlag : 0
             */

            private String __typename;
            private String id;
            private String creatorId;
            private long createDate;
            private String modifierId;
            private long modifyDate;
            private String name;
            private String shortName;
            private String type;
            private String areaId;
            private String unitId;
            private String parentId;
            private String directParentId;
            private Object creditNumber;
            private Object leader;
            private Object telephone;
            private Object address;
            private Object fax;
            private int sortNo;
            private String delFlag;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class DeptsBean {
            /**
             * __typename : AccountDept
             * id : 4gNwZ3rh3I9OasL55mE
             * creatorId : syssa
             * createDate : 1582880342003
             * accountId : aqpJRGfSEzXD09D2vEw
             * unitId : f0rWaW5qY2ST2CMiArx
             * unit : {"__typename":"Dept","id":"f0rWaW5qY2ST2CMiArx","creatorId":"syssa","createDate":1582702347604,"modifierId":"syssa","modifyDate":1582702347604,"name":"南宁市政府","shortName":"政府","type":"1","areaId":"Fk1QwWLzuEeloJar7VY","unitId":"f0rWaW5qY2ST2CMiArx","parentId":"root","directParentId":"root","creditNumber":null,"leader":null,"telephone":null,"address":null,"fax":null,"sortNo":0,"delFlag":"0"}
             * deptId : f0rWaW5qY2ST2CMiArx
             * dept : {"__typename":"Dept","id":"f0rWaW5qY2ST2CMiArx","creatorId":"syssa","createDate":1582702347604,"modifierId":"syssa","modifyDate":1582702347604,"name":"南宁市政府","shortName":"政府","type":"1","areaId":"Fk1QwWLzuEeloJar7VY","unitId":"f0rWaW5qY2ST2CMiArx","parentId":"root","directParentId":"root","creditNumber":null,"leader":null,"telephone":null,"address":null,"fax":null,"sortNo":0,"delFlag":"0"}
             * postId : CUEaKbLqQEa8SFhurio
             * post : {"__typename":"Post","id":"CUEaKbLqQEa8SFhurio","creatorId":"syssa","createDate":1582702393676,"modifierId":"syssa","modifyDate":1582702393676,"name":"市长","code":"shizhang","unitId":"f0rWaW5qY2ST2CMiArx","sortNo":1,"description":null}
             * defaultFlag : 1
             * sortNo : 1
             */

            private String __typename;
            private String id;
            private String creatorId;
            private long createDate;
            private String accountId;
            private String unitId;
            private UnitBeanX unit;
            private String deptId;
            private DeptBean dept;
            private String postId;
            private PostBean post;
            private String defaultFlag;
            private int sortNo;



            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class UnitBeanX {
                /**
                 * __typename : Dept
                 * id : f0rWaW5qY2ST2CMiArx
                 * creatorId : syssa
                 * createDate : 1582702347604
                 * modifierId : syssa
                 * modifyDate : 1582702347604
                 * name : 南宁市政府
                 * shortName : 政府
                 * type : 1
                 * areaId : Fk1QwWLzuEeloJar7VY
                 * unitId : f0rWaW5qY2ST2CMiArx
                 * parentId : root
                 * directParentId : root
                 * creditNumber : null
                 * leader : null
                 * telephone : null
                 * address : null
                 * fax : null
                 * sortNo : 0
                 * delFlag : 0
                 */

                private String __typename;
                private String id;
                private String creatorId;
                private long createDate;
                private String modifierId;
                private long modifyDate;
                private String name;
                private String shortName;
                private String type;
                private String areaId;
                private String unitId;
                private String parentId;
                private String directParentId;
                private Object creditNumber;
                private Object leader;
                private Object telephone;
                private Object address;
                private Object fax;
                private int sortNo;
                private String delFlag;


            }
            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class DeptBean {
                /**
                 * __typename : Dept
                 * id : f0rWaW5qY2ST2CMiArx
                 * creatorId : syssa
                 * createDate : 1582702347604
                 * modifierId : syssa
                 * modifyDate : 1582702347604
                 * name : 南宁市政府
                 * shortName : 政府
                 * type : 1
                 * areaId : Fk1QwWLzuEeloJar7VY
                 * unitId : f0rWaW5qY2ST2CMiArx
                 * parentId : root
                 * directParentId : root
                 * creditNumber : null
                 * leader : null
                 * telephone : null
                 * address : null
                 * fax : null
                 * sortNo : 0
                 * delFlag : 0
                 */

                private String __typename;
                private String id;
                private String creatorId;
                private long createDate;
                private String modifierId;
                private long modifyDate;
                private String name;
                private String shortName;
                private String type;
                private String areaId;
                private String unitId;
                private String parentId;
                private String directParentId;
                private Object creditNumber;
                private Object leader;
                private Object telephone;
                private Object address;
                private Object fax;
                private int sortNo;
                private String delFlag;


            }
            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class PostBean {
                /**
                 * __typename : Post
                 * id : CUEaKbLqQEa8SFhurio
                 * creatorId : syssa
                 * createDate : 1582702393676
                 * modifierId : syssa
                 * modifyDate : 1582702393676
                 * name : 市长
                 * code : shizhang
                 * unitId : f0rWaW5qY2ST2CMiArx
                 * sortNo : 1
                 * description : null
                 */

                private String __typename;
                private String id;
                private String creatorId;
                private long createDate;
                private String modifierId;
                private long modifyDate;
                private String name;
                private String code;
                private String unitId;
                private int sortNo;
                private Object description;


            }
        }
    }
}

