package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;
import spinupavm.ProgramManager;

@Elevation(1)
public class add implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        ProgramManager.Program program = ProgramManager.get(shell);
        if (program != null)
            program.add("add");
    }
}
