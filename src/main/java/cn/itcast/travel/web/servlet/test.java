package cn.itcast.travel.web.servlet;

/**
 * @ClassName: test
 * @Description:
 * @Author: niunan
 * @Create: 2019/8/19 10:07
 **/
public class test {
    public static void main(String[] args) {
        String str = "um_p_022626_ctwf,um_p_022626_ctwf";
        String res = str.replaceAll("(um)", "PAICOM\\\\\\\\"+"$1");
        System.out.println(res);
    }
}
