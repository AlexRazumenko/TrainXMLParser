package ua.kiev.prog;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement (name = "train")
public class Train {
    private int id;
    private String from;
    private String to;
    private String date;
    private String departure;

    public Train() {
    }

    public Train(int id, String from, String to, String date, String departure) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Train №" + id + " from " + from + " to " + to + " departs at " + departure + " in day " + date + "\n";
    }

    public Integer getId() {
        return id;
    }

    //   @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    //  @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    //   @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    public String getDeparture() {
        return departure;
    }

    //  @XmlElement
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDate() {
        return date;
    }

    //  @XmlElement
    public void setDate(String date) {
        this.date = date;
    }
}