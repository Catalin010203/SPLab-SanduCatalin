
package ro.uvt.execution;

public class AsyncRequestStatus {

    public enum State {
        PENDING,
        RUNNING,
        COMPLETED,
        FAILED
    }

    private final String requestId;
    private volatile State state;
    private volatile Object result;
    private volatile String error;

    public AsyncRequestStatus(String requestId) {
        this.requestId = requestId;
        this.state = State.PENDING;
    }

    public String getRequestId() {
        return requestId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
