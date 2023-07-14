package Exceptions.HW3;

public class Main {
    public static void main(String[] args) {
        PersonalData pd = new PersonalData();
        String[] test ={
                "too little data",
                "to o mu ch da t a 1 2",
                "mozart m wolfgang amadeus 27.01.1756 12345678",
                "mozart m wo1fgang 12345678 amadeus 27.01.1756",
                "Slayers Lina Inverse x 85663040 01.01.1200",
                "Slayers Lina f m 85663040 01.01.1200",
                "Slayers Lina Inverse f 85663040 01-01-1200",
                "Slayers Lina Inverse f b5663o40 01.01.1200",
                "Slayers Lina Inverse f 856630 01.01.1200",
                "01.01.1200 Slayers f Lina Inverse 85663040"
        };
        for (int i = 0; i < test.length; i++){
            System.out.println("---Test"+i);
            pd.readDataString(test[i]);
        }


       // pd.inputData();
    }
}
