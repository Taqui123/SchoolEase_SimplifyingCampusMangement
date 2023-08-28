import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProjectMainFrame extends JFrame implements ActionListener {

    Connection con;
    Statement st1;
    ResultSet rs1;
    JFrame f,f1;
    JMenuBar MB;
    JMenu m1,m2,m3,m4;
    JMenuItem m1i1,m1i2,m1i3,m1i4,
            m2i1,m2i2,m2i3,
            m3i1,m3i2,
            m4i1,m4i2,m4i3;
    JLabel l1,l2,l3;
    JTextField t1;
    JPasswordField t2;
    JButton b1;
    Font MyFun = new Font("Times New Roman",Font.BOLD,26);
    String uname, pass;
    public ProjectMainFrame () throws Exception {

        con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school","root","root");

        f = new JFrame("Welcome to AL-Farooque Urdu School");
        f.setLayout(new FlowLayout());
        f.setSize(900,600);
//        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("AL-Farooque Urdu High School, Beed");
        l1.setFont(MyFun);

        MB = new JMenuBar();
        m1 = new JMenu("Admission");
        m2 = new JMenu("Fees");
        m3 = new JMenu("Exams");
        m4 = new JMenu("Administrator");

        m1i1 = new JMenuItem("New Admission");
        m1i2 = new JMenuItem("Edit Admission");
        m1i3 = new JMenuItem("Delete");
        m1i4 = new JMenuItem("Exit");

        m2i1 = new JMenuItem("Pay Fees");
        m2i2 = new JMenuItem("Balance Inquiry");
        m2i3 = new JMenuItem("Delete Record");

        m3i1 = new JMenuItem("Marks Entry");
        m3i2 = new JMenuItem("Report Card");

        m4i1 = new JMenuItem("Fees");
        m4i2 = new JMenuItem("Courses");
        m4i3 = new JMenuItem("Change Password");

        m1.add(m1i1);
        m1.add(m1i2);
        m1.add(m1i3);
        m1.add(m1i4);

        m2.add(m2i1);
        m2.add(m2i2);
        m2.add(m2i3);

        m3.add(m3i1);
        m3.add(m3i2);

        m4.add(m4i1);
        m4.add(m4i2);
        m4.add(m4i3);

        MB.add(m1);
        MB.add(m2);
        MB.add(m3);
        MB.add(m4);
        f.setJMenuBar(MB);
        f.add(l1);

        m1i1.addActionListener(this);
        m1i2.addActionListener(this);
        m1i3.addActionListener(this);
        m1i4.addActionListener(this);
        m2i1.addActionListener(this);
        m2i2.addActionListener(this);
        m2i3.addActionListener(this);
        m3i1.addActionListener(this);
        m4i1.addActionListener(this);
        m4i2.addActionListener(this);
        m4i3.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==m1i1)
        {
            try {
                new NewAddm();
            } catch (Exception ignored) {}
        } else if (e.getSource()==m1i2)
        {
            new EditAddm();
        } else if (e.getSource()==m1i3)
        {
            f1 = new JFrame("Admin Authentication");
            f.setLayout(new FlowLayout());
            f.setSize(500, 250);
            f.setVisible(true);

            l2 = new JLabel("Please Enter the User_name:");
            l3 = new JLabel("Please Enter the Password:");

            t1 = new JTextField(15);
            t2 = new JPasswordField(15);

            b1 = new JButton("Login");

            f.add(l2);
            f.add(t1);
            f.add(l3);
            f.add(t2);
            f.add(b1);

                b1.addActionListener(e1 -> {

                    uname = t1.getText();

                    pass = t2.getText();

                    try {
                        st1 = con.createStatement();

                        rs1 = st1.executeQuery("select User_name from admin where User_name='" + uname + "';");
                        rs1.next();
                        String i = rs1.getString(1);

                        rs1 = st1.executeQuery("select Password from admin where Password='" + pass + "';");
                        rs1.next();
                        String j = rs1.getString(1);

                        if (i.equals(uname) && j.equals(pass))
                        {
                            new DelAddm();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Authentication\n" +
                            "Please Enter Correct User_name or Password", "<---WARNING--->", JOptionPane.WARNING_MESSAGE);
                        }

                    } catch (SQLException ignored) {}
                });

            } else if (e.getSource()==m1i4) {

            System.exit(0);

        } else if (e.getSource()==m2i1) {
             new FeesEntry();

        } else if (e.getSource()==m2i2) {
            new FeesInquiry();

        } else if (e.getSource()==m2i3) {
            new DeleteFeesRC();

        } else if (e.getSource()==m3i1) {
            new MarksEntry();

        } else if (e.getSource()==m3i2) {
            new ReportCard();

        } else if (e.getSource()==m4i1)
        {
            try {
                new AdminFees();
            } catch (Exception ignored) {}
        } else if (e.getSource()==m4i2)
        {
            try {
                new AdminCourse();
            } catch (Exception ignored) {}
        } else if (e.getSource()==m4i3)
        {
            try {
                new CHPass();
            } catch (Exception ignored) {}
        }
    }

    public static void main(String[] args)throws Exception
    {
        new ProjectMainFrame();
    }
}
