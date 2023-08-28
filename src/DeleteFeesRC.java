import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteFeesRC extends JFrame implements ActionListener {

    JFrame f,f1;
    JLabel l1,l2,l3,l4;
    JButton b1,b2,b3;
    List dtl = new List();
    JTextField t1,t3;
    JPasswordField t2;
    JPanel p1,p2,p3,p4,p5;
    Connection con;
    Statement st,st1,st2;
    ResultSet rs,rs1;
    String uname, pass, temptext, temptext1;

    public DeleteFeesRC()
    {
        try {
            con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school","root","root");
        } catch (SQLException ignored) {}

        f = new JFrame("!!AUTHENTICATION!!");
        f1 = new JFrame("Payment Record Window");
        f.setLayout(new GridLayout(5,1));

        p1 = new JPanel(new GridLayout(1,1));
        p2 = new JPanel(new GridLayout(1,2));
        p3 = new JPanel(new GridLayout(1,3));
        p4 = new JPanel(new GridLayout(1,3));
        p5 = new JPanel(new GridLayout(1,3));

        l1 = new JLabel("Welcome to School Management System");
        l1.setFont(new Font("Times New Roman",Font.BOLD,20));
        l1.setVisible(true);

        l2 = new JLabel("Enter User_name:");
        l2.setVisible(true);
        l3 = new JLabel("Enter Password:");
        l3.setVisible(true);

        b1 = new JButton("Submit");
        b2 = new JButton("Search");
        b3= new JButton("DELETE");

        t1 = new JTextField(15);
        t2 = new JPasswordField(15);

        p1.add(l1);
        p2.add(l2);
        p2.add(t1);
        p3.add(l3);
        p3.add(t2);
        p3.add(b1);


        f.add(p1);
        f.add(p2);
        f.add(p3);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        f.setSize(500,350);
        f1.setSize(650,350);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        uname = t1.getText();
        pass = t2.getText();

        if (e.getSource()==b1)
        {
            try {
                st = con.createStatement();

                rs = st.executeQuery("select User_name from admin where User_name='"+ uname +"';");
                rs.next();
                String i = rs.getString(1);

                rs = st.executeQuery("select Password from admin where Password='"+ pass +"';");
                rs.next();
                String j = rs.getString(1);


                if (i.equals(uname) && j.equals(pass) )
                {

                    f1.setVisible(true);
                    f1.setResizable(false);
                    f1.setLayout(new GridLayout(4,1));

                    l4 = new JLabel("Please Enter the Date\n" +
                            "(YYYYMMDD)");
                    t3 = new JTextField(20);

                    p2.remove(l2);
                    p2.remove(t1);
                    p2.add(l4);
                    p2.add(t3);
                    p2.add(b2);

                    p3.remove(l3);
                    p3.remove(t2);
                    p3.remove(b1);
                    p3.add(dtl);

                    p4.add(new JLabel(""));
                    p4.add(b3);
                    p4.add(new JLabel(""));

                    f1.add(p1);
                    f1.add(p2);
                    f1.add(p3);
                    f1.add(p4);


                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Invalid Authentication\n" +
                            "Please Enter Correct User_name or Password","<---WARNING--->",JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException ignored){}

        } else if (e.getSource()==b2) {

            temptext = t3.getText();

            JOptionPane.showMessageDialog(null, temptext);

            try {

                st1 = con.createStatement();
                rs1 = st.executeQuery("select * from studentstrct where date='"+ temptext +"';");

                while (rs1.next())
                {
                    temptext1 = ("PRN:  "+rs1.getString("prn_number")+"     name:  "+rs1.getString
                            ("name")+"      class:  "+rs1.getString("class")+"       fees paid:  "
                            +rs1.getString("fees_paid"));

                    dtl.add(temptext1);
                }

            } catch (SQLException ignored){}
        } else if (e.getSource()==b3) {

            temptext = dtl.getSelectedItem();
            String pprn = temptext.substring(5,12);
            pprn=pprn.trim();
            try {

                st2 = con.createStatement();

                int aaa=JOptionPane.showConfirmDialog(null,"The data Will Be Deleted Permanently\n" +
                        "DO YOU WANT TO CONTINUE!!!","WARNING", JOptionPane.OK_CANCEL_OPTION);
                if(aaa==JOptionPane.OK_OPTION)
                {
                    JOptionPane.showMessageDialog(null,pprn);

                    st2.executeUpdate("delete from studentstrct where prn_number='"+pprn+"';");

                    JOptionPane.showMessageDialog(null, "Data DELETED Successfully.", "NOTICE"
                            ,JOptionPane.INFORMATION_MESSAGE);
                con.commit();
                }
                else
                {
                    System.exit(0);
                }

            } catch (SQLException ignored){}

        }

    }
}
