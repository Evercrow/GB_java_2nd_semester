package Exceptions.HW3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class DataField {
    String fieldName;
    String fieldValue ="";

    public abstract List<String> parseField(List<String> values) throws BadParseException;

    @Override
    public String toString() {
        return String.format("%s : %s",fieldName,fieldValue);
    }
}

class FIO extends DataField{

    public FIO() {
        this.fieldName = "ФИО";
    }

    @Override
    public List<String> parseField(List<String> values) throws BadParseException{
        boolean isWord = true;
        ArrayList<String> parsedWords = new ArrayList<>();
        for (String val:values){
            for (int i = 0; i < val.length(); i++){
                if(Character.isDigit(val.charAt(i)) ){
                    isWord = false;
                }
            }
            if(isWord){
                this.fieldValue = this.fieldValue.concat(val+" ");
                parsedWords.add(val);
            }
            isWord = true;
        }
        if(fieldValue.isBlank()){
            throw new BadParseException(this.fieldName);
        }
        values.removeAll(parsedWords);
        return values;
    }

}
class Gender extends DataField{

    public Gender() {
        this.fieldName = "Пол";
    }

    @Override
    public List<String> parseField(List<String> values) throws BadParseException{
        if(values.contains("f")){
            this.fieldValue =this.fieldValue.concat("f");
            values.remove("f");
        }
        if(values.contains("m")){
            this.fieldValue =this.fieldValue.concat("m");
            values.remove("m");
        }
        if(fieldValue.length() != 1) {
            throw new BadParseException(fieldName);
        }
        return values;
    }
}

class PhoneNum extends DataField{

    public PhoneNum() {
        this.fieldName = "Номер телефона";
    }

    @Override
    public List<String> parseField(List<String> values) {
        ListIterator<String> iter = values.listIterator();
        String currentStr;
        while(iter.hasNext()){
            currentStr = iter.next();
            if(isNumeric(currentStr) ){
                this.fieldValue = currentStr;
                iter.remove();
            }
        }

        if(this.fieldValue.isBlank()){
            throw new BadParseException(this.fieldName);
        }
        if(this.fieldValue.length() != 8){
            throw new BadParseException("Телефонный номер длинной не в 8 цифр", this.fieldName);
        }
        return values;
    }

    private boolean isNumeric(String str){
        try {
            Integer d = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

class BirthDate extends DataField {
    private  final DateTimeFormatter dformat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public BirthDate() {
        this.fieldName = "Дата рождения";
    }

    @Override
    public List<String> parseField(List<String> values) throws BadParseException{

        ListIterator<String> iter = values.listIterator();
        String currentStr;
        while(iter.hasNext()){
            currentStr = iter.next();
            if(isValidDate(currentStr) ){
                this.fieldValue = currentStr;
                iter.remove();
            }
        }

        if(fieldValue.isBlank()){
            throw new BadParseException(this.fieldName);
        }

        return values;
    }

    private boolean isValidDate(String str){
        try {
            LocalDate.parse(str,dformat);
        } catch(DateTimeException e){
            return false;
        }
        return true;
    }
}

class BadParseException extends RuntimeException{

    public BadParseException(String cause) {
        super("Не удалось распарсить "+cause);
    }

    public BadParseException(String message, String cause) {
        super(String.format("Проблема с полем %s, %s",cause,message));
    }
}
