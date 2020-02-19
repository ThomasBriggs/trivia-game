import java.io.IOException;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        TriviaGame game = new TriviaGame(10, 9, TriviaGame.QUESTION_DIFFICULTY_HARD, TriviaGame.QUESTION_TYPE_MULTIPLE);
        int answer;
        while (game.hasNextQuestion()) {
            System.out.println(game.getQuestionTitle());
            System.out.println(TriviaGame.escape(game.getQuestion().toString()));
            System.out.println(game.printAnswers());
            System.out.println();
            System.out.print("Please enter a (1-4): ");
            answer = in.nextInt();
            if (game.checkAnswer(answer)) {
                game.correct();
                System.out.println("Correct");
            } else {
                game.incorrect();
                System.out.println("Incorrect");
                System.out.print("Correct answer was: ");
                System.out.println(TriviaGame.escape(game.getCorrectAnswer()));
            }
            System.out.println();
            game.next();
        }
        System.out.printf("Correct Questions: %s\nIncorrect Questions: %s\n", game.getScore()[0], game.getScore()[1]);
        in.close();
    }
}