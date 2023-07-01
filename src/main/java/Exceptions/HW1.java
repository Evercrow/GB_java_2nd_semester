package Exceptions;

import java.util.Arrays;

public class HW1 {
    public static void main(String[] args) {

        int[] a1 = {1,2,3,4,5};
        int[] a2 = {3,2,1};

        try {
            System.out.println(Arrays.toString(arraySubstract(a1, a1)));
            System.out.println(Arrays.toString(arraySubstract(a1, a2)));
        }catch(RuntimeException e){
            System.out.println(e);
        }

        try {
            stackExc(1000000);
        }catch(StackOverflowError e){
            System.out.println("\nслишком много рекурсий, Stack overflow");
            System.out.println(e);
        }

        try {
            indexExc();
        }catch(IndexOutOfBoundsException e){
            System.out.println("\nвызываем неправильный индекс, IndexOutOfBoundsException");
            System.out.println(e);
        }

        try {
            classEx();
        }catch(ClassCastException e){
            System.out.println("\nПреобразования между детьми-подклассами нет, ClassCastException");
            System.out.println(e);
        }

    }


    public static Integer[] arraySubstract( int[] arr1, int[] arr2){
        if(arr1.length != arr2.length){
            throw new RuntimeException("Массивы разной длины, не могу посчитать разницу");
        }
        Integer[] result = new Integer[arr1.length];
        for (int i = 0; i < result.length; i++){
            result[i] = arr1[i]-arr2[i];
        }
        return result;
    }

    public static int stackExc( int num){
        return  stackExc(num);
    }

    public static void indexExc(){
        int[] array = new int[]{1,2,3};
        System.out.println(array[5]);
    }

    public static void classEx(){
        Number num = 5;
        Number next = (Double)num;
    }
}
