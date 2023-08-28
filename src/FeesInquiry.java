import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FeesInquiry extends JFrame implements ActionListener {

    JFrame f;
    JLabel l1, l2;
    JPanel p1, p2, p3, p4, p5;
    JButton b1;
    JTextField t1;
    Connection con;
    Statement st, st1;
    ResultSet rs1,rs2;
    List dtl = new List();
    String temptext,temptext1, temptext2;
//    double bal,toPay,totl;

    public FeesInquiry()
    {

        f = new JFrame("!!Balance Inquiry!!");
        f.setLayout(new GridLayout(5,1));

        try {
            con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school","root","root");
        } catch (SQLException ignored) {}

        p1 = new JPanel(new GridLayout(1,1));
        p2 = new JPanel(new GridLayout(1,2));
        p3 = new JPanel(new GridLayout(1,3));
        p4 = new JPanel(new GridLayout(1,3));
        p5 = new JPanel(new GridLayout(1,3));

        l1 = new JLabel("Welcome to School Management System");
        l1.setFont(new Font("Times New Roman",Font.BOLD,20));
        l1.setVisible(true);

        l2 = new JLabel("Enter Your PRN number:");
        l2.setVisible(true);

        t1 = new JTextField(15);

        b1 = new JButton("Submit");

        p1.add(l1);
        p2.add(l2);
        p2.add(t1);


        p3.add(b1);
        p4.add(dtl);
        dtl.setVisible(false);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);

        b1.addActionListener(this);

        f.setSize(500,350);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==b1)
        {
            temptext = t1.getText();
            dtl.setVisible(true);

//            int endIn =dtl.countItems();

            dtl.removeAll();
            try {
                st = con.createStatement();
            rs1 = st.executeQuery("select * from studentstrct where prn_number='"+ temptext +"';");

            while (rs1.next())
            {
                temptext1 = ("Name:  "+rs1.getString("name")+"      class:  "
                        +rs1.getString("class")+"       fees paid:  "
                        +rs1.getString("fees_paid")+"       date:  "+rs1.getString("date"));

                dtl.add(temptext1);

                temptext2 = rs1.getString("class");
            }

            st1 = con.createStatement();
            rs2 = st1.executeQuery("select * from stdeadlock where prn='"+ temptext +"';");

                rs2.next();
                temptext1="     Balance     "+rs2.getString("fees_paid");
                dtl.add(temptext1);

        } catch (SQLException ignored){}

        }

    }

    public static void main(String[] args) {
        new FeesInquiry();
    }
}