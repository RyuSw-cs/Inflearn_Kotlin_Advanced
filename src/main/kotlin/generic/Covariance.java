package generic;

/**
 * Java에서의 공변
 * */
public class Covariance {
    String[] strArr = new String[3];
    Object[] objArr = new Object[3];

    private void casting(){
        objArr = strArr;
    }
}
