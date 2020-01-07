package br.com.module.exchange.model;

public class History {

    private int id;

    private String from;

    private String to;

    private String result;

    private String date;

    private String hourMinuteAndSeconds;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }

    public String getHourMinuteAndSeconds() {
        return hourMinuteAndSeconds;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHourMinuteAndSeconds(String hourMinuteAndSeconds) {
        this.hourMinuteAndSeconds = hourMinuteAndSeconds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
