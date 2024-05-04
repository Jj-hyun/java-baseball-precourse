package model;

import java.util.ArrayList;
import java.util.Scanner;

public class GameControlService {
    private Scanner scanner;
    private ArrayList<Integer> targetNumbers;
    private ArrayList<Integer> inputNumbers;
    public GameControlService(){
        scanner = new Scanner(System.in);
    }

    public void start(){
        BaseballNumberGenerator baseballNumberGenerator = new BaseballNumberGenerator();
        JudgmentService judgmentService = new JudgmentService();
        targetNumbers = baseballNumberGenerator.generate();
        while(true){
            System.out.print("숫자를 입력해 주세요 : ");
            scanInput();
            if(judgmentService.compare(targetNumbers, inputNumbers)) break;
        }
    }

    public void scanInput(){
        String input = scanner.nextLine();
        try {
            checkInputValid(input);
        }catch(Exception e){
           System.exit(-1);
        }
        inputNumbers = new ArrayList<Integer>();
        for(int i = 0; i < input.length(); i++){
            inputNumbers.add(Integer.parseInt(String.valueOf(input.charAt(i))));
        }
    }

    public void checkInputValid(String input) throws IllegalArgumentException{
        if(input.length() != BaseballNumberGenerator.making_number_count){
            throw new IllegalArgumentException("Invalid Input : " + input);
        }
    }

    public GameControlService scanRestart(){
        int checkRestart = Integer.parseInt(scanner.nextLine());
        if(checkRestart == 1){
            GameControlService gameControlService = new GameControlService();
            return gameControlService;
        }
        else if(checkRestart == 2) {
            return null;
        }
        else
            throw new IllegalArgumentException("Invalid Input : " + checkRestart);
    }
}
