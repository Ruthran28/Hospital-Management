import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class HospitalManageMent {
     private Connection Con;
     private Scanner scan;
    public HospitalManageMent(Connection con,Scanner scan){
        this.Con=con;
        this.scan=scan;
    }
    public void BookAppoiment(Patient pt,Doctor dr){
        System.out.println("Enter Patient id");
        int pid=scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Doctor ID");
        int did=scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the Appoiment-date in YYYY-MM-DD");
        String date=scan.nextLine();
        if (pt.getPatientById(pid) && dr.getDoctorById(did)) {
            if(CheckAppoiment(pid,did,date)){
                String Query="insert into Appoiment(Patient_id,Doctor_id,Appoiment_date)Values(?,?,?);";
                try {
                    PreparedStatement pr=Con.prepareStatement(Query);
                    pr.setInt(1,pid);
                    pr.setInt(2, did);
                    pr.setDate(3, Date.valueOf(date));
                    if(pr.executeUpdate()>=1){
                        System.out.println("Appoiment Booked");
                    }else{
                        System.out.println("Failed to Book Appoiment");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else{
                System.out.println("No Slots are Available in this date");
            }
        }else{
            System.out.println("Patient id or Doctor id is not exist");
        }
    }
    private boolean CheckAppoiment(int pid, int did, String date) {
        String Query="select Count(*) from Appoiment where Doctor_id=? AND Appoiment_date=?";
        try {
            PreparedStatement pr=Con.prepareStatement(Query);
            pr.setInt(1, did);
            pr.setDate(2, java.sql.Date.valueOf(date));
            ResultSet R=pr.executeQuery();
            if (R.next()) {
                int Count=R.getInt(1);
                if (Count==0) {
                    return true;
                }else{
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
   
}
