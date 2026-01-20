import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Patient {
      private Connection Con;
      private Scanner scan;
    public Patient(Connection con,Scanner scan){
        this.Con=con;
        this.scan=scan;
    }
    
     public  void  addPatient( ){
          System.out.println("Enter Patient name");
          String patientname=scan.nextLine();
          System.out.println("Enter Age");
          int age=scan.nextInt();
          scan.nextLine();
          System.out.println("Enter Gender");
          String gender=scan.nextLine();
        try {
            String Query="Insert into Patient(name,age,gender) Values (?,?,?);"; 
            PreparedStatement pr=Con.prepareStatement(Query);
            pr.setString(1, patientname);
            pr.setInt(2, age);
            pr.setString(3, gender);
            if (pr.executeUpdate()>=1) {
                System.out.println("Insert Successfully!!!");
            }else{
                System.out.println("Not Insert");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
     }
     public void ViewPatientDetails(){
        String Query="Select * from Patient";
        try {
            PreparedStatement pr=Con.prepareStatement(Query);
            ResultSet Result=pr.executeQuery();
            System.out.println("--------------------------------------------------");
            System.out.println("PatientId | Name               | Age  | Gender |");
            System.out.println("--------------------------------------------------");
            while (Result.next()) {
                int id=Result.getInt("Patient_id");
                String name=Result.getString("name");
                int age=Result.getInt("age");
                String Gender=Result.getString("gender");
                System.out.println(""+id +"         | "+name+"            | "+age+"  | "+Gender+"     |");
                System.out.println("--------------------------------------------------");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
     }
     public boolean getPatientById(int id){
        String Query="Select * from Patient where Patient_id=?";
        try {
            PreparedStatement pr=Con.prepareStatement(Query);
            pr.setInt(1, id);
            ResultSet result=pr.executeQuery();
             return result.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
     }
}
