package dibujo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processaComando(out, err, line);
            }
        }
    }

    private void processaComando(PrintStream out, PrintStream err, String line) {
        try {
            if (line.startsWith("C")) {
                criaCanvas(line);
            } else if (line.startsWith("L")) {
                criaLinha(line);
            } else if (line.startsWith("R")) {
                criaRetangulo(line);
            } else if (line.startsWith("B")) {
                preencheEspaco(line);
            } else if (line.startsWith("Q")) {
                terminaPrograma(out);
            } else {
                err.println("Invalid command: " + line + "\n");
            }

            printaCanvas(out);

            out.print("\nenter command: ");

        } catch (Exception ex) {
            err.println(ex.getMessage()+"\n");
            out.print("\nenter command: ");
        }
    }

    private void printaCanvas(PrintStream out) {
        out.println();

        for (int i = 0; i <= canvas.getWidth(); i++) {
            out.print("-");
        }
        out.print("-");

        out.println();

        int x = 1, y = 1;
        while (x >= 1 && x <= canvas.getWidth() && y >= 1 && y <= canvas.getHeight()) {
            Position position = canvas.getPosition(x, y);
            if (position.getX() == 1) {
                if (position.getY() > 1) {
                    out.println("|");
                }
                out.print("|");
            }

            if (position.getColor() != null) {
                out.print(position.getColor());
            } else if (position.isFilled()) {
                out.print("x");
            } else {
                out.print(" ");
            }
            x++;
            if (x >= canvas.getWidth() + 1) {
                x = 1;
                y++;
            }
        }

        out.println("|");
        out.print("-");

        for (int i = 0; i < canvas.getWidth(); i++) {
            out.print("-");
        }
        out.print("-");

        out.println();
    }

    private void terminaPrograma(PrintStream out) {
        out.println("Bye bye!");
        System.exit(0);
    }

    private void preencheEspaco(String line) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before filling it.");
        }

        Matcher matcher = Pattern.compile("^B (\\d+) (\\d+) (\\w+)$").matcher(line);
        if (matcher.find()) {
            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            String colorCharacter = matcher.group(3);

            Position startingPositionToFill = new Position(startingX, startingY);
            canvas.fill(startingPositionToFill, colorCharacter);
        } else {
            throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
        }
    }

    private void criaRetangulo(String line) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
        }

        Matcher matcher = Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int upperLeftCornerX = Integer.parseInt(matcher.group(1));
            int upperLeftCornerY = Integer.parseInt(matcher.group(2));
            int lowerRightCornerX = Integer.parseInt(matcher.group(3));
            int lowerRightCornerY = Integer.parseInt(matcher.group(4));

            canvas.createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
        } else {
            throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
        }
    }

    private void criaLinha(String line) {
        Linha.criaLinha(line, canvas);
    }

    private void criaCanvas(String line) {
        canvas = CanvasFactory.criaCanvas(line);
    }

    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

