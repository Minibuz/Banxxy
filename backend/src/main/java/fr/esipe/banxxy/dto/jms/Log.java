package fr.esipe.banxxy.dto.jms;

import java.util.Objects;

public class Log {

    private String message;

    public Log() {
    }

    public Log(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(message, log.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "Log{" +
                "message='" + message + '\'' +
                '}';
    }
}
