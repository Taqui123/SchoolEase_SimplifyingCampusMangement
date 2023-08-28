import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditAddm extends JFrame implements ActionListener {

    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15;
    JPanel p1, p2, p3, p4, p5, p6, p7;
    JButton b1, b2, b3, b4;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11;
    Connection con;
    Statement st, st1, st2, st3;
    ResultSet rs1, rs2,rs3;
    JComboBox<String> rg;
    JComboBox<String> gen;
    String a, b, c, d, asd, inp;


    public EditAddm() {

        try {

            con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school",
                    "root", "root");
            st = con.createStatement();

        } catch (SQLException ignored) {
        }

        f = new JFrame("Edit Admission Window");
        f.setLayout(new GridLayout(7, 1));
        f.setSize(1000, 700);
        f.setVisible(true);

        //==========================================Panels==================//

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        p4 = new JPanel(new FlowLayout());
        p5 = new JPanel(new FlowLayout());
        p6 = new JPanel(new FlowLayout());
        p7 = new JPanel(new FlowLayout());

        //=======================================Labels====================//

        l1 = new JLabel("Welcome to Al-Farooque Urdu High School");
        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l1.setVisible(true);

        l2 = new JLabel("Enter PRN Number :");

        l4 = new JLabel("First Name:");
        l5 = new JLabel("Middle Name:");
        l6 = new JLabel("Last Name:");
        l7 = new JLabel("MOther's Name:");
        l8 = new JLabel("Father's Name:");

        l9 = new JLabel("Gender:");
        l10 = new JLabel("Religion:");
        l11 = new JLabel("Date of Birth:");
        l12 = new JLabel("Address:");
        l13 = new JLabel("Phone NUmber:");
        l14 = new JLabel("Secondary:");
        l15 = new JLabel("E-mail:");

        //=============================================Buttons==================//

        b1 = new JButton("OK");
        b2 = new JButton("Other Options");
        b3 = new JButton("Next");
        b4 = new JButton("Update");

        //=======================================Combo boxes=====================//

        rg = new JComboBox<>();
        rg.addItem("Select Option");
        rg.addItem("Islam");
        rg.addItem("Hinduism");
        rg.addItem("Christianity");
        rg.addItem("Buddhism");
        rg.addItem("Jainism");
        rg.addItem("Judaism");
        rg.addItem("Zoroastrianism");

        gen = new JComboBox<>();
        gen.addItem("Select Option");
        gen.addItem("Male");
        gen.addItem("Female");
        gen.addItem("Other");

        //===========================================textB0xes===================//

        t1 = new JTextField(15);
        t2 = new JTextField(15);
        t3 = new JTextField(15);
        t4 = new JTextField(15);
        t5 = new JTextField(15);
        t6 = new JTextField(15);
        t7 = new JTextField(15);
        t8 = new JTextField(15);
        t9 = new JTextField(15);
        t10 = new JTextField(15);
        t11 = new JTextField(15);

        //==================================Adding components==============//

        p1.add(l1);

        p2.add(l2);
        p2.add(t1);
        p2.add(b1);
        p2.add(b2);

        p3.add(l4);
        p3.add(t2);
        p3.add(l5);
        p3.add(t3);
        p3.add(l6);
        p3.add(t4);

        p4.add(l7);
        p4.add(t5);
        p4.add(l8);
        p4.add(t6);

        p5.add(l9);
        p5.add(gen);
        p5.add(l10);
        p5.add(rg);
        p5.add(l11);
        p5.add(t7);
        t7.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
                String SDF2;
                java.util.Date dd13=new Date();
                SDF2  = SDF.format(dd13);
                t7.setText(SDF2);

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        p5.add(l12);
        p5.add(t8);

        p6.add(l13);
        p6.add(t9);
        p6.add(l14);
        p6.add(t10);
        p6.add(l15);
        p6.add(t11);

        p7.add(b3);
        p7.add(b4);

        p3.setVisible(false);
        p4.setVisible(false);
        p5.setVisible(false);
        p6.setVisible(false);
        p7.setVisible(false);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(p5);
        f.add(p6);
        f.add(p7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            a = t1.getText();

            try {
                st1 = con.createStatement();
                rs1 = st1.executeQuery("select prn from stdeadlock where prn='" + a + "';");

                rs1.next();
                b = rs1.getString(1);

                if (a.equals(b)) {
                    l3 = new JLabel("Edit the Content You Want");
                    p2.remove(l2);
                    p2.remove(t1);
                    p2.remove(b1);
                    p2.remove(b2);
                    p2.add(l3);
                    p3.setVisible(true);
                    p4.setVisible(true);
                    p5.setVisible(true);
                    p6.setVisible(true);
                    p7.remove(b3);
                    p7.setVisible(true);

                    rs3 = st1.executeQuery("select * from stdeadlock where prn='"+a+"';");

                        rs3.next();
                        t2.setText(rs3.getString("first_name"));
                        t3.setText(rs3.getString("middle_name"));
                        t4.setText(rs3.getString("last_name"));
                        t5.setText(rs3.getString("mothers_name"));
                        t6.setText(rs3.getString("fathers_name"));
                        gen.setSelectedItem(rs3.getString("sex"));
                        rg.setSelectedItem(rs3.getString("religion"));
                        t7.setText(rs3.getString("dob"));
                        t8.setText(rs3.getString("address"));
                        t9.setText(rs3.getString("phone_no1"));
                        t10.setText(rs3.getString("phone_no2"));
                        t11.setText(rs3.getString("e_mail"));
                        asd = rs3.getString("prn");


                } else {

                    JOptionPane.showMessageDialog(null,"Please enter a valid PRN number",
                            "PROMPT",JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException ignored) {}

        } else if (e.getSource() == b2) {

            c = JOptionPane.showInputDialog(null, "Please Enter The First Name:",
                    "Advance methods",
                    JOptionPane.OK_CANCEL_OPTION);

          //  if (c==JOptionPane.CANCEL_OPTION)
            //{
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //}


            d = JOptionPane.showInputDialog(null, "Please Enter The Last Name:",
                    "Advance methods",
                    JOptionPane.INFORMATION_MESSAGE);
            if (c.length()>0 || d.length()>0)
            {

            l3 = new JLabel("Edit the Content You Want");
            p2.remove(l2);
            p2.remove(t1);
            p2.remove(b1);
            p2.remove(b2);
            p2.add(l3);
            p3.setVisible(true);
            p4.setVisible(true);
            p5.setVisible(true);
            p6.setVisible(true);
            p7.setVisible(true);

            try {

                st2 = con.createStatement();

                rs2 = st2.executeQuery("select * from stdeadlock where first_name='" + c + "'" +
                        " or last_name='" + d + "';");

                rs2.next();
                t2.setText(rs2.getString("first_name"));
                t3.setText(rs2.getString("middle_name"));
                t4.setText(rs2.getString("last_name"));
                t5.setText(rs2.getString("mothers_name"));
                t6.setText(rs2.getString("fathers_name"));
                gen.setSelectedItem(rs2.getString("sex"));
                rg.setSelectedItem(rs2.getString("religion"));
                t7.setText(rs2.getString("dob"));
                t8.setText(rs2.getString("address"));
                t9.setText(rs2.getString("phone_no1"));
                t10.setText(rs2.getString("phone_no2"));
                t11.setText(rs2.getString("e_mail"));
                asd = rs2.getString("prn");


                JOptionPane.showMessageDialog(null, "Student's PRN Number is: " + asd, "PROMPT",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ignored) {}
        }} else if (e.getSource() == b3) {
            try {

                rs2.next();
                t2.setText(rs2.getString("first_name"));
                t3.setText(rs2.getString("middle_name"));
                t4.setText(rs2.getString("last_name"));
                t5.setText(rs2.getString("mothers_name"));
                t6.setText(rs2.getString("fathers_name"));
                gen.setSelectedItem(rs2.getString("sex"));
                rg.setSelectedItem(rs2.getString("religion"));
                t7.setText(rs2.getString("dob"));
                t8.setText(rs2.getString("address"));
                t9.setText(rs2.getString("phone_no1"));
                t10.setText(rs2.getString("phone_no2"));
                t11.setText(rs2.getString("e_mail"));
                asd = rs2.getString("prn");


                JOptionPane.showMessageDialog(null, "Student's PRN Number is: " + asd, "PROMPT",
                        JOptionPane.INFORMATION_MESSAGE);



            } catch (SQLException ignored) {
            }
        } else if (e.getSource() == b4) {
            try {
                st3 = con.createStatement();

                inp = "update stdeadlock set first_name='" + t2.getText() + "',middle_name='" + t3.getText() + "',last_name" +
                        "='" + t4.getText() + "',mothers_name='" + t5.getText() + "',fathers_name='" + t6.getText() + "',sex='"
                        + gen.getSelectedItem() + "', religion='" + rg.getSelectedItem() + "',dob='" + t7.getText() + "'" +
                        ",address='" + t8.getText() + "',phone_no1='" + t9.getText() + "',phone_no2='"
                        + t10.getText() + "',e_mail='" + t11.getText() + "' where prn='" + asd + "';";


                st3.executeUpdate(inp);

                JOptionPane.showMessageDialog(null, "Data Updated Successfully.", "NOTICE"
                        , JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ignored) {
                System.out.println(ignored);
            }
        }
    }

    public static void main(String[] args) {
        new EditAddm();
    }
}
