package Exceptions.HW3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PersonalData {

    DataField[] data = new DataField[4];
    public static final String ANSI_RESET = "\033[0m" ;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\033[0;32m";

    /**
     *  прием данных пользователя с консоли
     */
    public void inputData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ваши данные в произвольном порядке через пробел, форматы: 'Фамилия Имя Отчество(первое слово будет названием файла), датарождения(dd.mm.yyyy) , номертелефона(просто 8 цифр), пол(m/f)':");
        String userString = in.nextLine();
        processData(userString);
    }

    /**
     *  метод-заглушка для чтения готовых данных
     * @param userString
     */
    public void readDataString(String userString){
        processData(userString);
    }

    /**
     * основной метод, выступает как контроллер для других методов.
     * @param userString
     */
    protected void processData(String userString){
        List<String> userData = new ArrayList<>( Arrays.asList(userString.split(" ")) )  ;
        System.out.println(userData);
        int check = checkData(userData);
        if (check == 1){
            System.out.println(ANSI_RED+ "Вы ввели больше данных, чем требуется, строчка не была записана"+ANSI_RESET);
            return;
        }
        if (check == -1){
            System.out.println(ANSI_RED+"Вы ввели меньше данных, чем требуется, строчка не была записана"+ANSI_RESET);
            return;
        }
        data[3] = new Gender();
        data[2] = new PhoneNum();
        data[1] = new BirthDate();
        data[0] = new FIO();

        try {
            data[3]. parseField(userData);
            data[2]. parseField(userData);
            data[1]. parseField(userData);
            data[0]. parseField(userData);
            saveToFile(data);
        } catch(BadParseException e){
            System.out.println(ANSI_RED+ e.getMessage()+ANSI_RESET);
            //e.printStackTrace();
        } catch(IOException e) {
            System.out.println(ANSI_RED+"Не удалось записать данные в файл"+ANSI_RESET);
            e.printStackTrace();
        }

    }


    /**
     * Приложение должно проверить введенные данные по количеству.
     * Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
     */
    private int checkData(List<String> data){
        int size =  data.size();
            if (size == 6){return 0;}
            else { return size >6 ? 1 : -1;}
    }

    private void saveToFile(DataField[] data) throws IOException {
        File f = new File(data[0].
                                        fieldValue.
                                            split(" ",2)
                                                [0]+".txt");
        try(FileWriter fw = new FileWriter( f ,true)){
            for (DataField field : data){
                fw.write( field.fieldValue + " ");
            }
            fw.write("\n");
        }
        System.out.println(ANSI_GREEN+"Запись успешна"+ANSI_RESET);
    }
}
