package ShiLiHua;

public interface Furry {
    /**
     * 接口可以有成员变量
     * 接口可以有方法实现，但仅限于static default private(java 9+支持)
     */
    String tixing = "small";
    String tizhong = "200kg";

    void getColor();

    default String getTixing(){
        return tixing;
    }

}
