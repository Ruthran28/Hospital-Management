import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Doctor {
    private Connection Con;
    private Scanner scan;
    public Doctor(Connection con,Scanner scan){
        this.Con=con;
        this.scan=scan;
    }
    public void addDoctor(){
        try {
            String Query="Insert into Doctors (name,Specialization) values (?,?)";
            System.out.println("Enter Doctor name");
            String name=scan.nextLine();
            System.out.println("Enter Doctor Specialization");
            String spec=scan.nextLine();
            PreparedStatement pr=Con.prepareStatement(Query);
            pr.setString(1, name);
            pr.setString(2, spec);

            if (pr.executeUpdate()>=1) {
                System.out.println("Doctor add successfully");
            }else{
                System.out.println("Doctor is not insert ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void ViewDoctorDetails(){
        String Query="Select * from Doctors";
         try {
            PreparedStatement pr=Con.prepareStatement(Query);
            ResultSet Result=pr.executeQuery();
            System.out.println("--------------------------------------------------");
            System.out.println("DoctorId | Name               | Specialization    |");
            System.out.println("--------------------------------------------------");
            while (Result.next()) {
                int id=Result.getInt("Doctor_id");
                String name=Result.getString("name");
                String Specialization=Result.getString("Specialization");
                System.out.println(""+id +"         | "+name+"            | "+Specialization+"     |");
                System.out.println("--------------------------------------------------");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
      public boolean getDoctorById(int id){
        String Query="Select * from Doctors where Doctor_id=?";
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
