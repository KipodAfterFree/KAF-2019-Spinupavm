package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;
import org.quteshell.commands.Help;
import spinupavm.ProgramManager;

@Elevation(1)
@Help.Description("pop - pops value from stack arg1 to register RC")
public class pop implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        ProgramManager.Program program = ProgramManager.get(shell);
        if (program != null)
            program.add("pop " + arguments);
    }
}
