/* */

public class Prueba {
    public static void main(String[] args) {
        final char[] CHARSET = " abcdefABCDEF1234567890!".toCharArray();
        char[] password = new char[6];

        for(int i0 = 0; i0<CHARSET.length; i0++) {
            for(int i1 = 0; i1<CHARSET.length; i1++) {
                for(int i2 = 0; i2<CHARSET.length; i2++) {
                    for(int i3 = 0; i3<CHARSET.length; i3++) {
                        for(int i4 = 0; i4<CHARSET.length; i4++) {
                            for(int i5 = 0; i5<CHARSET.length; i5++) {
                                password[0]=CHARSET[i5];
                                password[1]=CHARSET[i4];
                                password[2]=CHARSET[i3];
                                password[3]=CHARSET[i2];
                                password[4]=CHARSET[i1];
                                password[5]=CHARSET[i0];
                            }
                        }
                    }
                }
            }   
        }
    }
}