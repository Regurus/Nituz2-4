package Model;

import Controller.UsersController;

public class EmergencyMedicalTechnician extends User {
    public EmergencyMedicalTechnician(String userName, String password, String name, String rank, String status, String type, UsersController uc){
        super(userName,password,name,rank,status,"EmergencyMedicalTechnician", uc);
    }
}
