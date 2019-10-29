package spinupavm;

import java.util.ArrayList;
import java.util.Stack;

public class ProgramManager {

    private static ArrayList<Program> programs = new ArrayList<>();

    public static Program get(String id) {
        for (Program program : programs)
            if (program.id.equals(id))
                return program;

        return null;
    }

    public static void start(String id) {
        programs.add(new Program(id));
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

        private class Runtime{
            private Stack<Short> SA, SB;

        }

    }
}
