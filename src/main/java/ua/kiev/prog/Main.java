package ua.kiev.prog;

import java.io.File;

public class Main {

    public static void main(String[] args) {

//  *********  Reading trains timetable from existing XML file *********

//        File file = new File("E:\\trains.xml"); //for PC
        File file = new File("D:\\trains.xml"); //for notebook
        Timetable timetable = new Timetable();
        TrainParser.fromXML(file, timetable);

        System.out.println("List of trains before modifying:");
        timetable.printTrains();

//  *********  Adding new train to timetable and then parsing timetable to XML file *********

        Train train = new Train(8, "Kyiv", "Budapest", "31.10.2018", "17:18");
        timetable.addTrain(train);

        TrainParser.toXML(file, timetable);

        System.out.println("\nList of trains in XML file after modifying:");
        timetable.printTrains();

//  *********  Adding new train directly in XML file, without timetable *********
        timetable.deleteAllTrains();
        Train train2 = new Train(9, "Kyiv", "Krakow", "31.10.2018", "16:24");
        TrainParser.addTrainAsXML(file, train2);

//  *********  Reading trains timetable from modified XML file *********
        System.out.println("\nTrains list after adding new train to XML file:");
        TrainParser.fromXML(file, timetable);
        timetable.printTrains();

//  *********  Showing all trains with departure time between 15:00 and 19:00 *********
        System.out.println("\nTrains departing between 15:00 and 19:00:");
        for (Train currentTrain : timetable.getTrains()) {
            String[] departureAsArray = currentTrain.getDeparture().split(":");
            if ((Integer.parseInt(departureAsArray[0]) > 14 ) & (Integer.parseInt(departureAsArray[0]) < 19))
                System.out.print(currentTrain);
        }
    }
}
