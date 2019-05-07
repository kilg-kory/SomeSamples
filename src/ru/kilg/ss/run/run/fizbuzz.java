package ru.kilg.ss.run.run;

/**
 * SomeSamples
 * fizbuzz - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 07.05.19
 */
public class fizbuzz {
    public static void main(String[] args) {
        for(int i = 1; i<= 100; i++){
            if(i % 15 == 0){
                System.out.println("Fifteen ");continue;
            }
            if(i % 5 == 0){
                System.out.print("Five ");continue;
            }
            if(i % 3 == 0){
                System.out.print("Three "); continue;
            }
            System.out.print(i + " ");
        }
    }
}
