
package ro.uvt.execution;

import org.springframework.stereotype.Component;
import ro.uvt.commands.Command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CommandExecutor {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(4);

    public <T> T executeSync(Command<T> command) {
        return command.execute();
    }

    public void executeAsync(Runnable task) {
        executor.submit(task);
    }
}
