package Exceptions;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) {

        System.out.println("main received number "+task1());
        System.out.println("------------------");
        task2(2);
        task2(0);
        System.out.println("------------------");
        Task3.task3();
        System.out.println("------------------");
        task4();
    }

    public static float task1(){
        Scanner input = new Scanner(System.in);
        Float userFloat = null;
        try {
            System.out.println("Введите ваше число типа float");
            userFloat = input.nextFloat();
        } catch(InputMismatchException e){
            System.out.println("Вы ввели не десятичное число с точкой");
            userFloat = task1();
        } catch(Exception e){
            System.out.println("Что-то не так со сканером консоли");
        }

        return userFloat;
    }

    /**
     *  в изначальном коде массив intArray не определен.
     * @param divisor - на что делим эемент массива
     */
    public static void task2(int divisor){

//        List<Integer> intArray = new ArrayList<>();
//        for (int i = 0; i < 10; i++){
//            intArray.add(i);
//        }
        int[] intArray = {0,1,2,3,4,5,6,7,8,9};
        try {
            int d = divisor;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }

    }
    /**
     * Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
     * Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
     */
    public static void task4() throws RuntimeException{
        Scanner input = new Scanner(System.in);
        String userStr = null;
        try {
            System.out.println("Введите строку");
            userStr = input.nextLine();
        } catch(Exception e){
            System.out.println("Что-то не так со сканером консоли");
        }
        if(userStr.equals("")){
            throw new RuntimeException("Вы ввели пустую строку");
        }else {
            System.out.printf("Вы ввели %s", userStr);
        }

    }





}

/**
 * Нужно спустить catch более общего Exception,
 * и убрать лишние throws из сигнатур - print не работает с файлами,
 * а Exception мы уже проверяем внутри основного метода
 */
class Task3{
    public static void task3(){
        try {
            int a = 90;
            int b = 3;
            // Integer a =null;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = { 1, 2 };
            abc[3] = 9;

        } catch (NullPointerException ex) {
            System.out.println("Указатель не может указывать на null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Throwable ex) {
            System.out.println("Что-то пошло не так...");
        }
    }

    public static void printSum(Integer a, Integer b){
        System.out.println(a + b);
    }
}
