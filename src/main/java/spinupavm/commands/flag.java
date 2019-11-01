package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;

@Elevation(1)
public class flag implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        if (shell.getElevation() < 2){
            shell.writeln("KAF{damn_1_0nly_had_t0_writ3_flag}");
        }else{
            shell.writeln("KAF{well_it_was_just_a_little_bit_more_complex_then_that}");
        }
    }
}
