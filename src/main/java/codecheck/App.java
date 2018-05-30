package codecheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        int target_number = 0;
        int args_number[] = new int[args.length-1];
        String str_number[] = new String[args.length-1];
        int max_number = 0;
        String max_str = null;

        // 正規表現のパターンを作成
        Pattern p = Pattern.compile("^[1-9][0-9]*:.+$");

        for (int i = 0, l = args.length; i < l; i++) {
            String output = String.format("argv[%s]: %s", i, args[i]);

            if (i == (args.length - 1)) {
                try {
                    target_number = Integer.parseInt(args[i]);
                } catch (NumberFormatException e) {
                    System.out.println("invalid input");
                    return;
                }
            } else {
                Matcher m = p.matcher(args[i]);
                if (m.find() == true) {
                    String[] output_array = args[i].split(":", 0);
                    args_number[i] = Integer.parseInt(output_array[0]);
                    str_number[i] = output_array[1];
                } else {
                    System.out.println("invalid input");
                    return;
                }
            }
        }

        //System.out.println(target_number);
        for (int i = 0; i < args_number.length; i++) {
            //System.out.println(args_number[i]);
            if ((target_number % args_number[i]) == 0) {
                if (max_number < args_number[i]) {
                        max_number = args_number[i];
                        max_str = str_number[i];
                }
            }
        }

        if (max_number == 0) {
            System.out.println(target_number);
        } else {
            System.out.println(max_str);
        }
    }
}
