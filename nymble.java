import java.util.* ;

class Activity{
    private String activityName ;
    private String activityDescription ;
    private double cost ;
    private int capacity ;
    private List<Passenger> passengers ;

    public Activity(String activityName , String description , double cost , int capacity){
        this.activityName = activityName ;
        this.activityDescription = description ;
        this.cost = cost ;
        this.capacity = capacity ;
        this.passengers = new ArrayList<>() ;
    }

    public boolean addPassenger(Passenger passenger){
        if(this.capacity > this.passengers.size()){
            passengers.add(passenger) ;
            // System.out.println("Passenger added successfully.") ;
            return true ;
        }else{
            // System.out.println("Passenger capacity is full.") ;
            return false ;
        }
    }

    public String getActivityName(){
        return this.activityName ;
    }

    public String getActivityDescription(){
        return this.activityDescription ;
    }

    public double getActivityCost(){
        return this.cost ;
    }
    
    public int getCapacity(){
        return this.capacity ;
    }

    public List<Passenger> getPassengersList(){
        return this.passengers ;
    }
}

class Destination{
    private String destinationName ;
    private List<Activity> activities ;

    public Destination(String name){
        this.destinationName = name ;
        this.activities = new ArrayList<>() ;
    }

    public void addActivity(Activity activity){
        this.activities.add(activity) ;
    }

    public String getDestinationName(){
        return this.destinationName ;
    }

    public List<Activity> getActivities(){
        return this.activities ;
    } 
}

class Passenger{
    private String passengerName ;
    private String passengerNumber ;
    // 1 = standard passenger , 2 = gold passenger , 3 = premium passenger
    private int passengerType ;
    private double balance ;
    private List<Activity> activities ;

    public Passenger(String name , String number , int passengerType , double balance){
        this.passengerName = name ;
        this.passengerNumber = number ;
        this.passengerType = passengerType ;
        this.balance = balance ;
        this.activities = new ArrayList<>() ;
    }

    public boolean addActivity(Activity activity){
        if(this.passengerType == 1){
            double activityCost = activity.getActivityCost() ;
            if(activityCost <= this.balance){
                this.activities.add(activity) ;
                activity.addPassenger(this) ;
                this.balance = this.balance - activityCost ;
                // System.out.println("Activity added successfully.") ;
                return true ;
            }else {
                // System.out.println("Insufficient balance.") ;
                return false ;
            }
        }else if(this.passengerType == 2){
            double activityCost = activity.getActivityCost() * 0.9 ;
            if(activityCost <= this.balance){
                this.activities.add(activity) ;
                activity.addPassenger(this) ;
                this.balance = this.balance - activityCost ;
                // System.out.println("Activity added successfully.") ;
                return true ;
            }
            else {
                // System.out.println("Insufficient balance.") ;
                return false ;
            }
        }else{
            this.activities.add(activity) ;
            activity.addPassenger(this) ;
            // System.out.println("Activity added successfully.") ;
            return true ;
        }
    }

    public String getPassengerName(){
        return this.passengerName ;
    }
    
    public String getPassengerNumber(){
        return this.passengerNumber ;
    }

    public int getPassengerType(){
        return this.passengerType ;
    }

    public double getBalance(){
        return this.balance ;
    }

    public List<Activity> getActivities(){
        return this.activities ;
    }
}

class TravelPackage{
    private String name ;
    private int passengerCapacity ;
    private List<Destination> itenary ;
    private List<Passenger> passengers ;

    public TravelPackage(String name , int passengerCapacity){
        this.name = name ;
        this.passengerCapacity = passengerCapacity ;
        this.itenary = new ArrayList<>() ;
        this.passengers = new ArrayList<>() ;
    }

    public void addDestination(Destination destination){
        this.itenary.add(destination) ;
    }

    public boolean addPassenger(Passenger passenger){
        if(this.passengerCapacity > this.passengers.size()){
            this.passengers.add(passenger) ;
            // System.out.println("Passenger added successfully.") ;
            return true ;
        }else{
            // System.out.println("Passenger capacity is full.") ;
            return false ;
        }
    }

    public String getName(){
        return this.name ;
    }
    
    public int getPassengerCapacity(){
        return this.passengerCapacity ;
    }

    public List<Destination> getItenary(){
        return this.itenary ;
    }


    public List<Passenger> getPassengersList(){
        return this.passengers ;
    }
}

public class nymble{
    public static void main(String[] args){
        // Create activities
        Activity activity1 = new Activity("Paragliding" , "Jumping from the cliff of a mointain with a pilot." , 4000 , 10) ;
        Activity activity2 = new Activity("Jungle Hiking" , "Explore the jungle and the huge biodiversity." , 1000 , 20) ;
        Activity activity3 = new Activity("Rafting" , "Rafting allows you to appreciate nature on a new level." , 500 , 2) ;
        Activity activity4 = new Activity("Camping" , "Staying the night/more than one night in a protective shelter out in nature." , 500 , 4) ;

        // Create destinations
        Destination destination1 = new Destination("Bir Billing") ;
        destination1.addActivity(activity1) ;
        destination1.addActivity(activity2) ;

        Destination destination2 = new Destination("Rishikesh") ;
        destination2.addActivity(activity3) ;
        destination2.addActivity(activity4) ;

        // Create travel package
        TravelPackage travelPackage = new TravelPackage("Backpackers Package" , 5) ;
        travelPackage.addDestination(destination1) ;
        travelPackage.addDestination(destination2) ;

        // Create passengers
        Passenger passenger1 = new Passenger("Mohit" , "9999888898" , 1 , 10000) ;
        Passenger passenger2 = new Passenger("Nitish" , "9999111181" , 2 , 5000) ;
        Passenger passenger3 = new Passenger("Saurabh" , "9876543210" , 3 , 0) ;

        // Add passengers for activities
        passenger2.addActivity(activity1);
        passenger3.addActivity(activity1);
        passenger3.addActivity(activity3);

        System.out.println("1. Travel Package Itenary:");
        System.out.println("Package Name: " + travelPackage.getName());
        System.out.println("- Activity Details:");
        for(Destination destination : travelPackage.getItenary()){
            for(Activity activity : destination.getActivities()){
                System.out.println("  -> " + activity.getActivityName() + " : Rs" + activity.getActivityCost() + " (" + activity.getActivityDescription() + ")") ;
            }
        }

        // Printing package details with passengers details.
        // printPassengersDetails(travelPackage) ;
        travelPackage.addPassenger(passenger2) ;
        travelPackage.addPassenger(passenger3) ;
        printPassengersDetails(travelPackage) ;
    }

    public static void printPassengersDetails(TravelPackage travelPackage){
        System.out.println();
        System.out.println("2. Passengers list:");
        System.out.println("Package Name: " + travelPackage.getName());
        System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());
        System.out.println("Number of Passengers: " + travelPackage.getPassengersList().size());
        for (Passenger passenger : travelPackage.getPassengersList()) {
            System.out.println("- Name: " + passenger.getPassengerName() + " (Number: " + passenger.getPassengerNumber() + ")");
        }

        // Printing individual passenger details
        System.out.println();
        System.out.println("3. Passenger Details:");
        for (Passenger passenger : travelPackage.getPassengersList()) {
            System.out.println("Passenger Name: " + passenger.getPassengerName());
            System.out.println("Passenger Number: " + passenger.getPassengerNumber());
            System.out.println("Passenger Balance: " + passenger.getBalance());
            for(Activity activity : passenger.getActivities()){
                for(Destination destination : travelPackage.getItenary()){
                    for(Activity activity2 : destination.getActivities()){
                        if(activity.getActivityName().equals(activity2.getActivityName())){
                            System.out.println("Activity Name: " + activity.getActivityName()) ;
                            System.out.println("Destination Name: " + destination.getDestinationName()) ;
                            System.out.println("Activity Cost: " + activity.getActivityCost()) ;
                        }
                    }
                }
            }
        }
        
        // Printing available activities
        System.out.println();
        System.out.println("4. Available Activities:");
        for (Destination destination : travelPackage.getItenary()) {
            for (Activity activity : destination.getActivities()) {
                int spacesAvailable = activity.getCapacity() - activity.getPassengersList().size();
                System.out.println("- " + activity.getActivityName() + " at " + destination.getDestinationName() + ": " + spacesAvailable + " spaces available");
            }
        }
    }
}