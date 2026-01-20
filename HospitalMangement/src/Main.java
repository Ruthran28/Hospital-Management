import java.sql.Connection;
import java.util.Scanner;

public class Main {
     public static void main(String[] args) throws Exception {

        Scanner scan=new Scanner(System.in);
        Connection Instance=DBConnection.getConnection();
        Patient pt=new Patient(Instance,scan);
        Doctor dr=new Doctor(Instance, scan);
        HospitalManageMent hm=new HospitalManageMent(Instance, scan);
       while (true) {
        System.out.println("-----------------------------------------");
        System.out.println("1.add Paitent\n2.Book Appoiment\n3.add Doctor \n4.View Patient Details\n5.View Doctor Details\n6.Exit");
        System.out.println("-----------------------------------------");
        int choice=scan.nextInt();
        scan.nextLine();
        switch (choice) {
            case 1:
                pt.addPatient();
                break;
            case 2:
                hm.BookAppoiment(pt, dr);
                break;
            case 3:
                dr.addDoctor();
                break;    
            case 4:
                pt.ViewPatientDetails();
                break;
            case 5:
                dr.ViewDoctorDetails();   
                break;
            case 6:
                System.out.println("Thank You for Booking");   
                return; 
            default:
                System.out.println("Enter Valid Choice");
                break;
        }

       }
    }
}
