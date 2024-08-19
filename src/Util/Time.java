package Util;


//creez o clasa Time care ma ajuta sa manageriez frame urile la fiecare update
public class Time {
    public static double start = System.nanoTime();
    //returneaza timpul de cand jocul "functioneaza" de cand aceasta a inceput
    public static double getTime()
    {
        return(System.nanoTime()-start)*1E-9;//convertirea in secunde
    }
}