package spinupavm;

import org.quteshell.Quteshell;

import java.util.ArrayList;
import java.util.Stack;

public class ProgramManager {

    private static ArrayList<Program> programs = new ArrayList<>();

    public static Program get(Quteshell quteshell) {
        for (Program program : programs)
            if (program.id.equals(quteshell.getIdentifier()))
                return program;

        return null;
    }

    public static void start(Quteshell quteshell) {
        programs.add(new Program(quteshell.getIdentifier()));
    }

    public static void run(Quteshell quteshell) {
        Program program = get(quteshell);
        if (program != null)
            program.run(quteshell);
    }

    public static class Program {

        private String id;

        private ArrayList<String> lines = new ArrayList<>();

        private Program(String id) {
            this.id = id;
        }

        public void add(String s) {
            lines.add(s);
        }

        public void run(Quteshell shell) {
            new Runtime(lines).run(shell);
        }

        private class Runtime {
            private Stack<Short> SA = new Stack<>(), SB = new Stack<>();
            private Register<Short> RA = new Register<>((short) 0), RB = new Register<>((short) 0), RC = new Register<>((short) 0);

            private ArrayList<String> lines = new ArrayList<>();

            private Runtime(ArrayList<String> lines) {
                this.lines.addAll(lines);
            }

            private void run(Quteshell quteshell) {
                for (String line : lines) {
                    evaluate(line, quteshell);
                }
            }

            private void evaluate(String line, Quteshell quteshell) {
                // Print input
                quteshell.write("> ", Quteshell.Color.LightBlue);
                quteshell.writeln(line);
                // Evaluate
                boolean result = true;

                // Print output
                quteshell.write("< ", Quteshell.Color.LightBlue);
                quteshell.writeln(result ? "V" : "X", result ? Quteshell.Color.LightGreen : Quteshell.Color.LightRed);
            }

            private class Register<T> {
                private T value;

                private Register(T value) {
                    set(value);
                }

                private void set(T value) {
                    this.value = value;
                }

                private T get() {
                    return this.value;
                }
            }
        }


    }
}
