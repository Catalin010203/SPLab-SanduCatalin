
package ro.uvt.execution;

import org.springframework.stereotype.Service;
import ro.uvt.commands.Command;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AsyncRequestService {

    private final CommandExecutor commandExecutor;

    private final Map<String, AsyncRequestStatus> requests =
            new ConcurrentHashMap<>();

    public AsyncRequestService(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public String submit(Command<?> command) {
        String requestId = UUID.randomUUID().toString();

        AsyncRequestStatus status = new AsyncRequestStatus(requestId);
        requests.put(requestId, status);

        commandExecutor.executeAsync(() -> {
            status.setState(AsyncRequestStatus.State.RUNNING);

            try {
                Object result = command.execute();
                status.setResult(result);
                status.setState(AsyncRequestStatus.State.COMPLETED);
            } catch (Exception e) {
                status.setError(e.getMessage() == null
                        ? "Request processing failed"
                        : e.getMessage());
                status.setState(AsyncRequestStatus.State.FAILED);
            }
        });

        return requestId;
    }

    public AsyncRequestStatus getStatus(String requestId) {
        return requests.get(requestId);
    }
}
