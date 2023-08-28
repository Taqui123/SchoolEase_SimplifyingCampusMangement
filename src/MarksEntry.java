import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MarksEntry extends JFrame implements ActionListener {

    JFrame f;
    JLabel l1,l2,l3,l4,l5,l6;
    JPanel p1, p2, p3, p4, p5, p6, p7;
    JButton b1,b2;
    JTextField t1,t2,t3,t4;
    Connection con;
    Statement st1,st2,st3;
    ResultSet rs1,rs2;
    JComboBox<String> cls;
    String a, text1,mk,sub,na,pprn;
    public MarksEntry()
    {

        try {

            con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school",
                    "root", "root");

        } catch (SQLException ignored) {}

        f = new JFrame("Mark Enter Window");
        f.setLayout(new GridLayout(7, 1));
        f.setSize(500, 400);
        f.setVisible(true);

        ///////////////////////////// panels //////////////////////////////////////////

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        p4 = new JPanel(new FlowLayout());
        p5 = new JPanel(new FlowLayout());
        p6 = new JPanel(new FlowLayout());
        p7 = new JPanel(new FlowLayout());

        //////////////////////////////////// labels/////////////////////////////////////

        l1 = new JLabel("Welcome to School Management System");
        l1.setFont(new Font("Times New Roman",Font.BOLD,20));
        l1.setVisible(true);

        l2 = new JLabel("Select Class:");
        l3 = new JLabel("PRN number:");
        l4 = new JLabel("Name of student:");
        l5 = new JLabel("Name of subject:");
        l6 = new JLabel("Marks Obtained:");

        ///////////////////////////////// buttons////////////////////////

        b1 = new JButton("Next");
        b2 = new JButton("Submit");

        ////////////////////////////////// text-field//////////////////

        t1 = new JTextField(15);
        t2 = new JTextField(15);
        t3 = new JTextField(15);
        t4 = new JTextField(15);


        try {
            st1 = con.createStatement();

            cls = new JComboBox<>();
            rs1 = st1.executeQuery("select class from courses;");

            while (rs1.next())
            {
                a = rs1.getString(1);
                cls.addItem(a);
            }
        } catch (SQLException ignored){}

        p1.add(l1);

        p2.add(l2);
        p2.add(cls);
        p2.add(b1);

        p3.add(l3);
        p3.add(t1);

        p4.add(l4);
        p4.add(t2);

        p5.add(l5);
        p5.add(t3);

        p6.add(l6);
        p6.add(t4);

        p7.add(b2);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(p5);
        f.add(p6);
        f.add(p7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        cls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    text1 = (String) cls.getSelectedItem();

                    st2 = con.createStatement();
                    rs2 = st2.executeQuery("select * from stdeadlock where class='"+text1+"';");

                } catch (SQLException ignored){}
            }
        });

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==b1)
        {
            try {

                rs2.next();
                t1.setText(rs2.getString("prn"));
                t2.setText(rs2.getString("first_name"));

            } catch (SQLException ignored){}

        } else if (e.getSource()==b2) {

            pprn = t1.getText();
            na = t2.getText();
            sub = t3.getText();
            mk = t4.getText();

            try {

                st3 = con.createStatement();

                String inp = ("insert into exams (name,prn_number,subject,marks,class) values " +
                        "('"+na+"','"+pprn+"','"+sub+"','"+mk+"','"+text1+"');");

                int aaa=JOptionPane.showConfirmDialog(null,"The data Will Be Deleted Permanently\n" +
                        "DO YOU WANT TO CONTINUE!!!","WARNING", JOptionPane.OK_CANCEL_OPTION);
                if(aaa==JOptionPane.OK_OPTION)
                {
                    st3.executeUpdate(inp);

                    JOptionPane.showMessageDialog(null,inp);

                    JOptionPane.showMessageDialog(null, "Data UPDATED Successfully.", "NOTICE"
                            ,JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException ignored){}

        }

    }
}
