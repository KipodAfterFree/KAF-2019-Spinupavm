package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Quteshell;
import spinupavm.ProgramManager;

public class add implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        ProgramManager.Program program = ProgramManager.get(shell.getIdentifier());
        program.add("add");
    }
}
