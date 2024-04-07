package com.ssm.constant;

public class UserInfoConstant {
    /**
     * 星座类型
     */
    public enum ConstellationType{
        Pisces("双鱼座"),
        Scorpio("天蝎座");
        private final String name;

        ConstellationType(String name) {
            this.name = name;
        }
    }
}
