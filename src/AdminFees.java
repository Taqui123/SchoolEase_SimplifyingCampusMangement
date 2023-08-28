import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminFees extends JFrame implements ActionListener {

    JFrame f;
    JComboBox<String> core;
    JLabel l1,l2,l3,l4,l5;
    JButton b1,b2;
    JTextField t1,t3;
    JPasswordField t2;
    JPanel p1,p2,p3,p4,p5;
    Connection con;
    Statement st,st1,st2;
    ResultSet rs,rs1;
    String a, uname, pass,Clas;
    double n,m;

    public AdminFees() throws Exception {

        con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school","root","root");
        st = con.createStatement();

        f = new JFrame("Change Fees window");
        f.setLayout(new GridLayout(5,1));

        core = new JComboBox<>();

         rs = st.executeQuery("select Class from courses;");

        while (rs.next())
        {
             a = rs.getString(1);
             core.addItem(a);
        }

        p1 = new JPanel(new GridLayout(1,1));
        p2 = new JPanel(new GridLayout(1,2));
        p3 = new JPanel(new GridLayout(1,3));
        p4 = new JPanel(new GridLayout(1,4));
        p5 = new JPanel(new GridLayout(1,3));

        l1 = new JLabel("Welcome to School Management System");
        l1.setFont(new Font("Times New Roman",Font.BOLD,20));

        l2 = new JLabel("Enter Current User_name:");
        l3 = new JLabel("Enter Current Password:");
        l4 = new JLabel("Select Course:");
        l4.setVisible(false);
        l5 = new JLabel("Course Fees:");
        l5.setVisible(false);

        b1 = new JButton("Submit");
        b2 = new JButton("Apply Changes");
        b2.setVisible(false);


        t1 = new JTextField(15);
        t2 = new JPasswordField(15);
        t3 = new JTextField(15);
        t3.setVisible(false);
        core.setVisible(false);

        p1.add(l1);
        p2.add(l2);
        p2.add(t1);
        p3.add(l3);
        p3.add(t2);
        p3.add(b1);
        p4.add(l4);
        p4.add(core);
        p4.add(l5);
        p4.add(t3);
        p5.add(new JLabel(""));
        p5.add(new JLabel(""));
        p5.add(b2);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(p5);

        b1.addActionListener(this);
        b2.addActionListener(this);
        core.addActionListener(e -> {
            try {
                Clas = (String) core.getSelectedItem();
                st1=con.createStatement();
                rs1 = st1.executeQuery("select fees from courses where Class='"+Clas+"';");
                rs1.next();
                n = rs1.getDouble(1);

                t3.setText(String.valueOf(n));
            } catch (SQLException ignored){}

        });

        f.setSize(500,350);
        f.setResizable(false);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
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

                if (i.equals(uname) && j.equals(pass))
                {
                    core.setVisible(true);
                     Clas = (String) core.getSelectedItem();

                    l2.setVisible(false);
                    t1.setVisible(false);
                    l3.setVisible(false);
                    t2.setVisible(false);
                    b1.setVisible(false);
                    l4.setVisible(true);
                    l5.setVisible(true);
                    t3.setVisible(true);
                    b2.setVisible(true);

                    rs = st.executeQuery("select fees from courses where Class='"+Clas+"';");
                    rs.next();
                    n = rs.getDouble(1);

                    t3.setText(String.valueOf(n));

                } else
                {
                    JOptionPane.showMessageDialog(null,"Invalid Authentication\n" +
                                    "Please Enter Correct User_name or Password",
                            "<---WARNING--->",JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException ignored){}

        } else if (e.getSource()==b2)
        {
            Clas = (String) core.getSelectedItem();

            try {
                st2 = con.createStatement();

                m = Double.parseDouble(t3.getText());

                st2.executeUpdate("update courses set fees='"+m+"' where Class='"+Clas+"';");
                con.commit();

                JOptionPane.showMessageDialog(null,"Fees Updated Successfully:)",
                        "PROMPT",JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ignored){}
        }
    }
}
