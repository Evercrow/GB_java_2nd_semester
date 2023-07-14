package Exceptions.HW3;

public class Main {
    public static void main(String[] args) {
        PersonalData pd = new PersonalData();
        String test1 = "mozart wolfgang amadeus m 27.01.1756 12345678";
        String test2 = "mo7art m wolfgang 12345678 amadeus 27.01.1756";
        // pd.inputData();
        pd.readDataString(test1);
        pd.readDataString(test2);
    }
}
