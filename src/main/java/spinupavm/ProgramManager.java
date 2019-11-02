package spinupavm;

import org.quteshell.Quteshell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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

        public void reset() {
            lines.clear();
        }

        private class Runtime {
            private Stack SA = new Stack(20), SB = new Stack(10);
            private Register RA = new Register(BigInteger.valueOf(0)), RB = new Register(BigInteger.valueOf(0)), RC = new Register(BigInteger.valueOf(1));

            private ArrayList<String> lines = new ArrayList<>();

            private Runtime(ArrayList<String> lines) {
                this.lines.addAll(lines);
            }

            private void run(Quteshell quteshell) {
                while (!lines.isEmpty()) {
                    evaluate(lines.remove(0), quteshell);
                }
            }

            private void evaluate(String line, Quteshell quteshell) {
                // Print input
                quteshell.write("> ", Quteshell.Color.LightBlue);
                quteshell.write(line);
                // Evaluate
                String error = null;
                String[] split = line.split(" ");
                try {
                    switch (split[0]) {
                        case "add": {
                            RC.set(RA.get().add(RB.get()));
                            break;
                        }
                        case "cmp": {
                            RC.set(BigInteger.valueOf(RA.get().compareTo(RB.get())));
                            break;
                        }
                        case "mov": {
                            if (split.length < 3) throw new Exception("Missing argument");
                            Register to = findRegister(split[1]), from = findRegister(split[2]);
                            to.set(from.get());
                            break;
                        }
                        case "pop": {
                            if (split.length < 2) throw new Exception("Missing argument");
                            Stack from = findStack(split[1]);
                            RC.set(from.pop());
                            break;
                        }
                        case "prt": {
                            if (split.length < 2) throw new Exception("Missing argument");
                            Register from = findRegister(split[1]);
                            quteshell.write(" > ", Quteshell.Color.LightBlue);
                            quteshell.write(String.valueOf(from.get()));
                            RC.set(new BigInteger("872529637301286644995771451296691547261925492"));
                            break;
                        }
                        case "psh": {
                            if (split.length < 2) throw new Exception("Missing argument");
                            Stack to = findStack(split[1]);
                            to.push(RC.get());
                            break;
                        }
                        case "sub": {
                            RC.set(RA.get().subtract(RB.get()));
                            break;
                        }
                    }
                } catch (Exception e) {
                    error = e.getMessage();
                }
                // Print output
                quteshell.write(" < ", Quteshell.Color.LightBlue);
                quteshell.write(error == null ? "OK" : error, error == null ? Quteshell.Color.LightGreen : Quteshell.Color.LightRed);
                if (error != null) {
                    try {
                        StringBuilder result = new StringBuilder();
                        Process process = new ProcessBuilder().command("bash", "-c", "/errorlog '" + error + "'").start();
                        process.waitFor();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String rl;
                        while ((rl = bufferedReader.readLine()) != null) {
                            result.append(rl);
                        }
                        bufferedReader.close();
                        quteshell.write(" > ", Quteshell.Color.LightBlue);
                        quteshell.write("Log " + result.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                quteshell.writeln();
            }

            private Register findRegister(String name) throws RegisterNotFoundException {
                if (name.toLowerCase().equals("ra")) return RA;
                if (name.toLowerCase().equals("rb")) return RB;
                if (name.toLowerCase().equals("rc")) return RC;
                throw new RegisterNotFoundException(name);
            }

            private Stack findStack(String name) throws StackNotFoundException {
                if (name.toLowerCase().equals("sa")) return SA;
                if (name.toLowerCase().equals("sb")) return SB;
                throw new StackNotFoundException(name);
            }

            private class RegisterNotFoundException extends Exception {
                private RegisterNotFoundException(String name) {
                    super("The register \'" + name.toUpperCase() + "\' was not found.");
                }
            }

            private class StackNotFoundException extends Exception {
                private StackNotFoundException(String name) {
                    super("The stack \'" + name.toUpperCase() + "\' was not found.");
                }
            }

            private class Register {
                private BigInteger value;

                private Register(BigInteger value) {
                    set(value);
                }

                private void set(BigInteger value) {
                    this.value = value;
                }

                private BigInteger get() {
                    return this.value;
                }
            }

            private class Stack {

                private int capacity = 10;

                private ArrayList<BigInteger> array = new ArrayList<>();

                private Stack(int capacity) {
                    this.capacity = capacity;
                }

                private void push(BigInteger value) throws ValueStackFullException {
                    array.add(value);
                    if (array.size() > capacity) {
                        throw new ValueStackFullException(array.remove(array.size() - 1));
                    }
                }

                public BigInteger pop() throws ValueStackEmptyException {
                    if (array.size() > 0)
                        return array.remove(0);
                    throw new ValueStackEmptyException();
                }

                private class ValueStackFullException extends Exception {
                    private ValueStackFullException(BigInteger value) {
                        super("The stack overflowed, pushed out value is " + new String(value.toByteArray(), StandardCharsets.UTF_8));
                    }
                }

                private class ValueStackEmptyException extends Exception {
                    private ValueStackEmptyException() {
                        super("The stack underflowed");
                    }
                }
            }
        }


    }
}
