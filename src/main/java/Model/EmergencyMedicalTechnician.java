package Model;

public class EmergencyMedicalTechnician extends User {
    public EmergencyMedicalTechnician(String userName, String password, String name,String rank, String status,String type){
        super(userName,password,name,rank,status,"Emergency medical technician");
    }
}
