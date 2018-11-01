package ua.kiev.prog;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

//    @XmlRootElement (name = "trains")
public class Timetable {

    public Timetable() {}

//    @XmlElement (name = "train")
    private List<Train> trains = new ArrayList<Train>();

    public void addTrain (Train train) {
        trains.add(train);
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void deleteAllTrains() {
        trains.removeAll(trains);
    }

    public void printTrains() {
        for (Train currentTrain : trains) {
            System.out.print(currentTrain);
        }
    }
}
