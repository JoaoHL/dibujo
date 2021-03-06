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
            // ideia: usar um enum para pegar o comando e executar ele automaticamente, pra tirar esses if-else
            if (line.startsWith("C")) {
                criaCanvas(line);
            } else if (line.startsWith("L")) {
                criaLinha(line, canvas);
            } else if (line.startsWith("R")) {
                criaRetangulo(line, canvas);
            } else if (line.startsWith("B")) {
                preencheEspaco(line, canvas);
            } else if (line.startsWith("Q")) {
                terminaPrograma(out);
            } else {
                err.println("Invalid command: " + line + "\n");
            }
            //Comando comando = Comando.achaComando(line, err);

            canvas.print(out);

            out.print("\nenter command: ");

        } catch (Exception ex) {
            err.println(ex.getMessage()+"\n");
            out.print("\nenter command: ");
        }
    }

    private void terminaPrograma(PrintStream out) {
        out.println("Bye bye!");
        System.exit(0);
    }

    private void preencheEspaco(String line, Canvas canvas) {
        Preenchedor.preencheEspacoComCor(line, canvas);
    }

    private void criaRetangulo(String line, Canvas canvas) {
        Retangulo.adicionaNoCanvas(line, canvas);
    }

    private void criaLinha(String line, Canvas canvas) {
        Linha.adicionaNoCanvas(line, canvas);
    }

    private void criaCanvas(String line, Canvas canvas) {
        this.canvas = CanvasFactory.criaCanvas(line);
    }

    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

