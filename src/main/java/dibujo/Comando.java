package dibujo;

import javax.swing.text.html.Option;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Optional;

public enum Comando {
    CRIA_CANVAS("C", new CanvasFactory()) {
        @Override
        public void executa(String line, Canvas canvas) {
            executa(line);
        }
    },
    TERMINA_PROGRAMA("Q", null) {
        @Override
        public void executa(String line, Canvas canvas) {
            System.out.println("Bye bye!");
            System.exit(0);
        }
    },
    CRIA_LINHA("L", new Linha()),
    CRIA_RETANGULO("R", new Retangulo()),
    PREENCHE_ESPACO("B", new Preenchedor()),
    ;

    public String getLetraComando() {
        return letraComando;
    }

    private String letraComando;
    private ExecutorDeComando executorDeComando;

    Comando(String letraComando, ExecutorDeComando executorDeComando) {
        this.letraComando = letraComando;
        this.executorDeComando = executorDeComando;
    }

    public static Comando achaComando(String line, PrintStream err) {
        Optional<Comando> possivelComando = Arrays.stream(values())
                .filter(cmd -> line.startsWith(cmd.getLetraComando()))
                .findAny();
        if (possivelComando.isEmpty()) {
            //err.println("Invalid command: " + line + "\n");
            throw new RuntimeException("Invalid command: " + line + "\n");
        }
        return possivelComando.get();
    }

    public void executa(String line, Canvas canvas) {
        executorDeComando.executa(line, canvas);
    }
}
