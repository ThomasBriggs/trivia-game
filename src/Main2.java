import java.io.IOException;
import java.util.Scanner;

/**
 * Main2
 */
public class Main2 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        TriviaGame game = new TriviaGame(10, 9, TriviaGame.QUESTION_DIFFICULTY_MEDIUM,
                TriviaGame.QUESTION_TYPE_MULTIPLE);
        while (game.hasNextQuestion()) {
            System.out.println(game.getQuestionTitle());
            System.out.println(game.getQuestion().formatAnswers(game.getQuestion().getAnswersSorted()));
            System.out.println();
            game.increaseQuestionNumber();
        }

    }
}