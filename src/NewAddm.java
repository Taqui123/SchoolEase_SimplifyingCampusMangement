/*
        1. change the size for the components of year, day and month is remaining.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import static java.awt.Font.*;

public class NewAddm extends JFrame  implements ActionListener
{
    JFrame f;
    JComboBox<String> rg;
    JComboBox<String> gen;
    JComboBox<String> sts;
    JComboBox<String> dis;
    JComboBox<String> ct;
    JComboBox<String> pcls;
    JComboBox<String> ncls;
    JComboBox<String> year;
    JComboBox<String> mon;
    JComboBox<String> day;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27;
    JButton b1,b2,b3;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20;
    JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15;
    JCheckBox ch;
    Connection con;
    Statement st,st1,st2,st3;
    Font myfun = new Font("times new Roman",BOLD,20);
    ResultSet rs,rs1,rs2,rs3,rs4,rs5;
    String a;
    int tprn,tprn1;
    double n;

    public NewAddm() throws Exception
    {

        con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school","root","root");
        st = con.createStatement();

        f = new JFrame("New Admission Window");
        f.setLayout(new GridLayout(15,1));

        ch = new JCheckBox("Accept Terms & Condition for Admission.");
        ch.setFont( new Font("times new Roman",BOLD,16));

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

        day = new JComboBox<>();
      //  day.setBounds(10,10,5,10);
        for (int i = 1; i < 32; i++)
        {
            if (i<=9)
            {
                day.addItem("0"+ i);
            }
            else
                day.addItem(String.valueOf(i));
        }

        mon = new JComboBox<>();
        for (int j = 1; j < 13; j++)
        {
            if (j<=9)
            {
                mon.addItem("0"+ j);
            }
            else
                mon.addItem(String.valueOf(j));
        }

        year =new JComboBox<>();
        for (int k = 1950; k<=2023; k++)
        {
            year.addItem(String.valueOf(k));
        }

        sts = new JComboBox<>();
        rs = st.executeQuery("select * from State_vise;");

        while (rs.next())
        {
            a = rs.getString(1);
            sts.addItem(a);
        }


        dis = new JComboBox<>();
        rs1 = st.executeQuery("select * from District_vise;");

        while (rs1.next())
        {
            a = rs1.getString(1);
            dis.addItem(a);
        }

        ct = new JComboBox<>();
        rs1 = st.executeQuery("select * from City_vise;");

        while (rs1.next())
        {
            a = rs1.getString(1);
            ct.addItem(a);
        }

        pcls = new JComboBox<>();
        rs2 = st.executeQuery("select class from courses;");

        while (rs2.next())
        {
            a = rs2.getString(1);
            pcls.addItem(a);
        }

        ncls = new JComboBox<>();
        rs3 = st.executeQuery("select class from courses;");

        while (rs3.next())
        {
            a = rs3.getString(1);
            ncls.addItem(a);
        }

        l1 = new JLabel("Welcome to Al-Farooque Urdu High School");
        l1.setFont(new Font("Times New Roman",BOLD,30));
        l1.setHorizontalAlignment(JLabel.CENTER);

        l2 = new JLabel("* Personal Information :-");
        l2.setFont(myfun);

        l3 = new JLabel("First Name :");
        l4 = new JLabel("Middle Name :");
        l5 = new JLabel("Last Name :");
        l6 = new JLabel("Mother's Name :");
        l7 = new JLabel("Father's Name :");
        l8 = new JLabel("Gender :");
        l9 = new JLabel("Date of Birth :");
        l10 = new JLabel("Religion :");

        l11 = new JLabel("* Address Details:-");
        l11.setFont(myfun);

        l12 = new JLabel("State :");
        l13 = new JLabel("District :");
        l14 = new JLabel("City :");
        l15 = new JLabel("Pin Code :");

        l16 = new JLabel("* Contact Details:-");
        l16.setFont(myfun);

        l17 = new JLabel("Parents NO :");
        l18 = new JLabel("Secondary :");
        l19 = new JLabel("E-Mail ID :");

        l20 = new JLabel("* Admission Details:-");
        l20.setFont(myfun);

        l21 = new JLabel("Previous Class :");
        l22 = new JLabel("Admission in Class :");
        l23 = new JLabel(" Total Course Fees :");
        l24 = new JLabel("First Instalment(60%) :");
        l25 = new JLabel("Second Instalment(40%) :");
        l26 = new JLabel("Birth Certificate Number");
        l27 = new JLabel("PRN Number :");
        l27.setHorizontalAlignment(JLabel.RIGHT);

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
        t12 = new JTextField(15);
        t13 = new JTextField(15);
        t14 = new JTextField(15);
        t15 = new JTextField(25);
        t16 = new JTextField(15);
        t17 = new JTextField(15);
        t18 = new JTextField(15);
        t19 = new JTextField(15);
        t20 = new JTextField(15);

        p1 = new JPanel(new GridLayout(1,1,20,10));
        p2 = new JPanel(new GridLayout(1,1,20,10));
        p3 = new JPanel(new GridLayout(1,1,20,10));
        p4 = new JPanel(new GridLayout(1,1,20,10));
        p5 = new JPanel(new GridLayout(1,1,10,10));
        p6 = new JPanel(new GridLayout(1,1,20,10));
        p7 = new JPanel(new GridLayout(1,1,20,10));
        p8 = new JPanel(new GridLayout(1,1,20,10));
        p9 = new JPanel(new GridLayout(1,1,20,10));
        p10 = new JPanel(new GridLayout(1,1,20,10));
        p11 = new JPanel(new GridLayout(1,1,20,10));
        p12 = new JPanel(new GridLayout(1,1,20,10));
        p13 = new JPanel(new GridLayout(1,1,20,10));
        p14 = new JPanel(new GridLayout(1,1,20,10));
        p15 = new JPanel(new GridLayout(1,1,20,10));

        try {
            st3 = con.createStatement();
            rs5 = st3.executeQuery("select max(prn) from stdeadlock;");
            rs5.next();

            tprn = Integer.parseInt(String.valueOf(rs5.getString(1)));
            tprn1 = tprn + 1;
           t9.setText(String.valueOf(tprn1));

        }catch (SQLException ignored){}

        b1 = new JButton("Clear");
        b2 = new JButton("Cancel");
        b3 = new JButton("Save");

        p1.add(l1);
        p2.add(l2);

        p3.add(l3);
        p3.add(t1);
        t1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String x;
                x = t1.getText();
                t1.setText(x.toUpperCase());
            }
        });
        p3.add(l4);
        p3.add(t2);
        t2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String y;
                y = t2.getText();
                t2.setText(y.toUpperCase());
            }
        });
        p3.add(l5);
        p3.add(t3);
        t3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String z;
                z = t3.getText();
                t3.setText(z.toUpperCase());
            }
        });

        p4.add(l6);
        p4.add(t4);
        t4.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String mx;
                mx = t4.getText();
                t4.setText(mx.toUpperCase());
            }
        });
        p4.add(l7);
        p4.add(t5);
        t5.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String fx;
                fx = t5.getText();
                t5.setText(fx.toUpperCase());
            }
        });
        p4.add(l8);
        p4.add(gen);

        p5.add(l9);
        p5.add(year);
        p5.add(mon);
        p5.add(day);
        p5.add(l10);
        p5.add(rg);

        p6.add(l11);
        p7.add(l12);
        p7.add(sts);
        p7.add(l13);
        p7.add(dis);
        p7.add(l14);
        p7.add(ct);

        p8.add(l15);
        p8.add(t12);
        p8.add(new JLabel(""));
        p8.add(new JLabel(""));
        p8.add(new JLabel(""));
        p8.add(new JLabel(""));

        p9.add(l16);
        p10.add(l17);
        p10.add(t13);
        t13.setText("0000000000");
        t13.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
            public void focusLost(FocusEvent e) {
                String a;
                a = t13.getText();
                if (a.length() != 10)
                {
                    JOptionPane.showMessageDialog(null,"Entered Mobile Number is Wrong\n" +
                            "Please Enter a Valid Mobile Number");

                    t13.setText("");
                    t13.requestFocus();
                }
            }
        });
        p10.add(l18);
        p10.add(t14);
        t14.setText("0000000000");
        t14.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String b;
                b = t13.getText();
                if (b.length() != 10)
                {
                    JOptionPane.showMessageDialog(null,"Entered Mobile Number is Wrong\n" +
                            "Please Enter a Valid Mobile Number");

                    t14.setText("");
                    t14.requestFocus();
                }
            }
        });
        p10.add(l19);
        p10.add(t15);
        t15.setText("@gmail.com");
        t15.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String X = t15.getText();
                int lle=X.length();
                int mila=0,dd1=0;
                while(dd1<lle)
                {

                    if(X.charAt(dd1)=='@')
                        mila++;
                    if(X.charAt(dd1)=='.')
                        mila++;
                    dd1++;
                }
                if(mila<2) {
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid E-mail"
                    ,"Warning",JOptionPane.WARNING_MESSAGE);
                    t15.requestFocus();
                }
            }
        });

        p11.add(l20);
        p12.add(l21);
        p12.add(pcls);
        p12.add(l22);
        p12.add(ncls);
        p12.add(l23);
        p12.add(t18);
        t18.setEditable(false);

        p13.add(l24);
        p13.add(t19);
        p13.add(l25);
        p13.add(t20);
        p13.add(l26);
        p13.add(t8);

        p14.add(ch);
        p14.add(l27);
        p14.add(t9);
        t9.setEditable(false);

        p15.add(new JLabel(""));
        p15.add(new JLabel(""));
        p15.add(new JLabel(""));
        p15.add(b1);
        p15.add(b2);
        p15.add(b3);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(p5);
        f.add(p6);
        f.add(p7);
        f.add(p8);
        f.add(p9);
        f.add(p10);
        f.add(p11);
        f.add(p12);
        f.add(p13);
        f.add(p14);
        f.add(p15);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        sts.addActionListener(this);
        gen.addActionListener(this);
        dis.addActionListener(this);
        rg.addActionListener(this);
        pcls.addActionListener(this);
        ncls.addActionListener(e -> {
            String s = (String) ncls.getSelectedItem();

            try {
                st1=con.createStatement();
                rs4 = st1.executeQuery("select fees from courses where Class='"+s+"';");
                rs4.next();

                n = rs4.getDouble(1);
                t18.setText(String.valueOf(n));

                t19.setText(String.valueOf(n*0.6));
                t20.setText(String.valueOf(n*0.4));

            } catch (SQLException ignored) {}
        });
        ct.addActionListener(e -> {
            String ss = (String) ct.getSelectedItem();
            String m;

            try {
                st1=con.createStatement();
                rs4 = st1.executeQuery("select pin_code from city_vise where city_name='"+ss+"';");
                rs4.next();

                m = rs4.getString(1);
                t12.setText(m);
            } catch (SQLException ignored){}
        });

        f.setSize(1000,700);
        f.setVisible(true);
        f.setResizable(false);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==b1)
        {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t7.setText("");
            t8.setText("");
            t11.setText("");
            t12.setText("");
            t13.setText("0000000000");
            t14.setText("0000000000");
            t15.setText("@gmail.com");
            gen.setSelectedIndex(0);
            rg.setSelectedIndex(0);
            sts.setSelectedIndex(0);
            dis.setSelectedIndex(0);
            pcls.setSelectedIndex(0);
            ncls.setSelectedIndex(0);
            ct.setSelectedIndex(0);
            year.setSelectedIndex(0);
            mon.setSelectedIndex(0);
            day.setSelectedIndex(0);
            ch.setSelected(false);
        }

        if (e.getSource()==b2)
        {
            System.exit(1);
        }

        if (e.getSource()==b3)
        {
            String MyDate = String.valueOf(year.getSelectedItem())+ mon.getSelectedItem() +
                    day.getSelectedItem();


            try {
                st2 = con.createStatement();

                String inp = "insert into stdeadlock (prn,first_name,middle_name,last_name,mothers_name,fathers_name,sex" +
                        ",religion,dob,address,phone_no1,phone_no2,e_mail,class,fees_paid) values ('"+tprn1+"','"+t1.getText()+"'" +
                        ",'"+t2.getText()+"','"+t3.getText()+"','"+t4.getText()+"','"+t5.getText()+"','"+gen.getSelectedItem()+"'" +
                        ",'"+rg.getSelectedItem()+"','"+MyDate+"','"+sts.getSelectedItem()+dis.getSelectedItem()+ct.getSelectedItem()
                +t12.getText()+"','"+t13.getText()+"','"+t14.getText()+"','"+t15.getText()+"','"+ncls.getSelectedItem()+"','"+t19.getText()+"');";

                if (ch.isSelected())
                {
                        st2.executeUpdate(inp);

                    JOptionPane.showMessageDialog(null, "Data Saved Successfully", "NOTICE"
                            , JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Accept Terms & Condition First"
                    ,"PROMPT",JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException ignored) {}
        }
    }
}