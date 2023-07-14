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

    /**
     *  прием данных пользователя с консоли
     */
    public void inputData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ваши данные в строке через пробел формата: 'Фамилия Имя Отчество, датарождения(dd.mm.yyyy) , номертелефона(просто 8 цифр), пол(m/f)':");
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
            System.out.println("Вы ввели больше данных, чем требуется, работа завершена досрочно");
            return;
        }
        if (check == -1){
            System.out.println("Вы ввели меньше данных, чем требуется, работа завершена досрочно");
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
            e.printStackTrace();
        } catch(IOException e) {
            System.out.println("Не удалось записать данные в файл");
            e.printStackTrace();
        }

        if(userData.size()>0){
            System.err.println("Не удалось распарсить следующие поля");
            System.err.println(userData);
        }

        for (DataField d:data
             ){
            System.out.println(d);
        }
    }

    /**
     * Приложение должно проверить введенные данные по количеству.
     * Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
     */
    private int checkData(List<String> data){
        int size =  data.size();
            if (size == 6){return 0;}
            else { return size >7 ? 1 : -1;}
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

    }
}
