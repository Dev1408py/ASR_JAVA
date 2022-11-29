package com.ASR_JAVA;

import java.util.Scanner;

import static com.ASR_JAVA.helper.choose_lang;

class helper{
    public static String choose_lang(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1 --> For English");
        System.out.println("2 --> For Chinese");
        System.out.println("3 --> For Japanese");
        System.out.println("4 --> For German");
        System.out.println("Enter Your Choice of Language");
        int hold = sc.nextInt();
        switch (hold){
            case 1:
                return "en-US";
            case 2:
                return "zh-CN";
            case 3:
                return "ja-JP";
            case 4:
                return "de-DE";
            default:
                return "en-US";
        }
    }
}
public class speech_rec {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String Api_Key = "0bdea89b2b9848bdb304e246f88ce8c9"; // For response purposes from the server, won't work without this.
        api app = new api();
        jdbc db = new jdbc();
        boolean flag = true;
        int id = 1;
        System.out.println("*******************************************************************************");
        System.out.println("Welcome to the Automatic Speech Recognition Software");
        System.out.println("Enter your Name");
        String name = sc.next();
        while(flag){
            System.out.println("1 --> To Run The Speech Recognizer and save the data to the Database.");
            System.out.println("2--> To view records from the Database.");
            System.out.println("3--> To delete the record from the Database.");
            System.out.println("4--> To quit the System.");
            System.out.println("Enter the choice you want to Execute.");
            int take = sc.nextInt();
            switch (take){
                case 1:
                    String lang = choose_lang();
                    String ans = app.get_transcript(Api_Key,lang);
                    if(ans == "f"){
                        System.out.println("Please Try Again.Something went Wrong");
                    }
                    else {
                        System.out.println(ans);
                        jdbc.insert_data(id,ans,name);
                        id+=1;
                    }
                    break;

                case 2:
                    System.out.println("Enter the ID of the data you want to view.");
                    int nm = sc.nextInt();
                    db.get_data(nm);
                    break;

                case 3:
                    System.out.println("Enter the ID of the data that you want to delete.");
                    int del = sc.nextInt();
                    db.delete_data(del);
                    break;
                case 4:
                    System.out.println("Thanks for using the System.");
                    flag = false;
                    break;
            }
        }
    }
}
