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

        public void reset() {
            lines.clear();
        }

        private class Runtime {
            private Stack SA = new Stack(20), SB = new Stack(10);
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
                            break;
                        }
                        case "psh": {
                            if (split.length < 2) throw new Exception("Missing argument");
                            Stack to = findStack(split[1]);
                            to.push(RC.get());
                            break;
                        }
                        case "sub": {
                            RC.set((short) (RA.get() - RB.get()));
                            break;
                        }
                    }
                } catch (Stack.ValueStackFullException e) {
                    error = e.getMessage();
                    quteshell.setElevation(e.value);
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
                    super("Register not found - the register \'" + name.toUpperCase() + "\' was not found.");
                }
            }

            private class StackNotFoundException extends Exception {
                private StackNotFoundException(String name) {
                    super("Stack not found - the stack \'" + name.toUpperCase() + "\' was not found.");
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

                private int capacity = 10;

                private ArrayList<Short> array = new ArrayList<>();

                private Stack(int capacity) {
                    this.capacity = capacity;
                }

                private void push(short value) throws ValueStackFullException {
                    array.add(value);
                    if (array.size() > capacity) {
                        throw new ValueStackFullException(array.remove(array.size() - 1));
                    }
                }

                public short pop() throws ValueStackEmptyException {
                    if (array.size() > 0)
                        return array.remove(0);
                    throw new ValueStackEmptyException();
                }

                private class ValueStackFullException extends Exception {

                    private int value = 0;

                    private ValueStackFullException(int value) {
                        super("Value stack full - the stack overflowed, pushed out value is " + value + ", sending to handler.");
                        this.value = value;
                    }
                }

                private class ValueStackEmptyException extends Exception {
                    private ValueStackEmptyException() {
                        super("Value stack empty - the stack underflowed, pulled in value is 0, sending to handler.");
                    }
                }
            }
        }


    }
}
