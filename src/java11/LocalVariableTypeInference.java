package java11;

import java.util.Arrays;

public class LocalVariableTypeInference {

    public static void main(String[] args) {

        var arrInteger = new Integer[]{5, 9, 3, 6, 2, 4, 8, 7, 1};
        var cnt = Arrays.stream(arrInteger).filter(x -> (x != null && x > 5)).count();
        System.out.println(cnt);


        cnt = Arrays.stream(arrInteger).filter((var a) -> (a != null && a > 5)).count();
        System.out.println(cnt);
    }
}
