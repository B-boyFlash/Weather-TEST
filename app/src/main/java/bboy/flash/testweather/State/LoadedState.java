package bboy.flash.testweather.State;

public class LoadedState<T> extends State {

    private T data;

    public LoadedState(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

