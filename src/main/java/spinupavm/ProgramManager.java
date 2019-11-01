package spinupavm;

import org.quteshell.Quteshell;

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

        private class Runtime {
            private Stack SA = new Stack(), SB = new Stack();
            private Register RA = new Register((short) 0), RB = new Register((short) 0), RC = new Register((short) 1);

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
                            RC.set((short) (RA.get() + RB.get()));
                            break;
                        }
                        case "cmp": {
                            if (RA.get() == RB.get()) {
                                RC.set((short) 0);
                            } else {
                                if (RA.get() > RB.get()) {
                                    RC.set((short) 1);
                                } else {
                                    RC.set((short) 2);
                                }
                            }
                            break;
                        }
                        case "mov": {
                            if (split.length < 3) throw new Exception("Missing argument");
                            Register from = findRegister(split[1]), to = findRegister(split[2]);
                            to.set(from.get());
                            break;
                        }
                        case "pop": {
                            Stack from = findStack(split[1]);
                            RC.set(from.pop());
                        }
                        case "prt":{

                        }
                    }
                } catch (StackNotFoundException | RegisterNotFoundException e) {
                    error = e.getMessage();
                } catch (Stack.ValueStackFloodException e) {
                    error = e.getMessage();
                    quteshell.setElevation(e.getValue());
                } catch (Exception e) {
                    error = e.getMessage();
                }
                // Print output
                quteshell.write(" < ", Quteshell.Color.LightBlue);
                quteshell.write(error == null ? "OK" : error, error == null ? Quteshell.Color.LightGreen : Quteshell.Color.LightRed);
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
                    super("A register not found exception has occurred - the register \'" + name.toUpperCase() + "\' was not found.");
                }
            }

            private class StackNotFoundException extends Exception {
                private StackNotFoundException(String name) {
                    super("A stack not found exception has occurred - the stack \'" + name.toUpperCase() + "\' was not found.");
                }
            }

            private class Register {
                private short value;

                private Register(short value) {
                    set(value);
                }

                private void set(short value) {
                    this.value = value;
                }

                private short get() {
                    return this.value;
                }
            }

            private class Stack {

                private short amount = 0;
                private short[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


                private void push(short value) throws ValueStackFloodException {
                    short flood = values[0];
                    for (int i = 0; i < amount - 1; i++) {
                        values[i] = values[i + 1];
                    }
                    values[amount - 1] = value;
                    if (amount > values.length) {
                        throw new ValueStackFloodException(flood);
                    } else {
                        amount++;
                    }
                }

                public short pop() {
                    if (amount > 0) {
                        amount--;
                        return values[amount];
                    } else {
                        return 0;
                    }
                }

                private class ValueStackFloodException extends Exception {

                    private int value = 0;

                    private ValueStackFloodException(int value) {
                        super("A value stack flood exception has occurred - the stack was flooded, pushed out value is " + value + ", sending to handler.");
                        this.value = value;
                    }

                    public int getValue() {
                        return value;
                    }
                }
            }
        }


    }
}
