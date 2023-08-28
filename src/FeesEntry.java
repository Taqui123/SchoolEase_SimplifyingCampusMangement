import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class FeesEntry extends JFrame implements ActionListener
{

    JFrame f,f1;
    JLabel l1, l2, l3, l4, l5, l6, l7,l8,l9;
    JPanel p1, p2, p3, p4, p5,p6,p7,p8;
    JTextField t1, t2, t3, t4, t5,t6,t7,t8;
    JButton b1,b2,b3;
    Connection con;
    Statement st, st1,st2,st3;
    ResultSet rs,rs1,rs2,rs3;
    JComboBox<String> ncls;
    CheckboxGroup ch = new CheckboxGroup();
    Checkbox c1,c2;
    String a,da;
    double n,nn,x;

    public FeesEntry()
    {
        try {

            con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school","root","root");
            st = con.createStatement();

            f = new JFrame("Fees Entry Window");
            f.setLayout(new GridLayout(6, 1));

            //=====================================combo boxes=================//

            ncls = new JComboBox<>();
            rs = st.executeQuery("select * from courses;");

            while (rs.next())
            {
                ncls.addItem(rs.getString(1));
            }

        } catch (SQLException ignored){}

        //==========================================Panels==================//

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        p4 = new JPanel(new FlowLayout());
        p5 = new JPanel(new FlowLayout());
        p6 = new JPanel(new FlowLayout());
        p7 = new JPanel(new FlowLayout());
        p8 = new JPanel(new FlowLayout());

        //=======================================Labels====================//

        l1 = new JLabel("Welcome to Al-Farooque Urdu High School");
        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l1.setVisible(true);
        l2 = new JLabel("Select Class:");
        l3 = new JLabel("Name of student:");
        l4 = new JLabel("PRN of student:");
        l5 = new JLabel("Total Fees:");
        l6 = new JLabel("Balance:");
        l7 = new JLabel("Amount:");
        l8 = new JLabel("Transaction ID:");
        l9 = new JLabel("Date:");

        //================================== Buttons======================//

        b1 = new JButton("Next");
        b2 = new JButton("Proceed to Pay");
        b3= new JButton("Pay");

        //==================================text fields===================//

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        t3 = new JTextField(10);
        t4 = new JTextField(10);
        t5 = new JTextField(10);
        t6 = new JTextField(10);
        t7 = new JTextField(10);
        t7.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");
                String SDF2;
                Date dd13=new Date();
                SDF2  = SDF.format(dd13);
                t7.setText(SDF2);

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        t8 = new JTextField(10);

        //==================================check box===================//

        c1 = new Checkbox("Cash Payment", ch,false);
        c2 = new Checkbox("Online Payment",ch,false);


        //==================================Adding components==============//

        p1.add(l1);

        p2.add(l2);
        p2.add(ncls);

        p3.add(l3);
        p3.add(t1);
        p3.add(l4);
        p3.add(t2);
        p3.add(b1);

        p4.add(l5);
        p4.add(t3);
        t3.setEditable(false);
        p4.add(l6);
        p4.add(t4);
        t4.setEditable(false);
        p4.add(l7);
        p4.add(t5);

        p5.add(c1);
        p5.add(c2);

        p6.add(b2);

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(p5);
        f.add(p6);

        ncls.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        f.setSize(600, 350);
        f.setResizable(false);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==ncls)
        {

            a = (String) ncls.getSelectedItem();
            JOptionPane.showConfirmDialog(null,a,
                     "Confirmation",JOptionPane.OK_CANCEL_OPTION);

                try {

                    st1 = con.createStatement();
                    rs1 = st1.executeQuery("select * from stdeadlock where class='"+a+"';");
                } catch (SQLException ignored){}

                try {
                    st2 = con.createStatement();
                    rs2 = st2.executeQuery("select * from courses where class='"+a+"';");
                    rs2.next();
                            n = rs2.getDouble("fees");

                } catch (SQLException ignored){}

        } else if (e.getSource()==b1) {
            {
                try {

                    rs1.next();
                    t1.setText(rs1.getString("First_name"));
                    t2.setText(rs1.getString("prn"));
                    t4.setText(rs1.getString("Fees_paid"));
                    t3.setText(String.valueOf(n));
                    t5.setText(String.valueOf(Double.parseDouble(t3.getText())-Double.parseDouble(t4.getText())));

                } catch (SQLException ignored){}

            }
        } else if (e.getSource()==b2) {

            f1 = new JFrame("Payment Window");
            f1.setLayout(new GridLayout(3,1));
            f1.setSize(400, 200);
            f1.setVisible(true);

            if (c1.getState())
            {
                p7.add(l7);
                p7.add(t6);
                p7.add(l9);
                p7.add(t7);

                p8.add(b3);
                f1.add(p1);
                f1.add(p7);
                f1.add(p8);
            }
            else if (c2.getState())
            {
                p7.add(l7);
                p7.add(t6);
                p7.add(l8);
                p7.add(t7);

                p8.add(l9);
                p8.add(t8);
                p8.add(b3);

                f1.add(p1);
                f1.add(p7);
                f1.add(p8);
            }

        } else if (e.getSource()==b3)
        {
            x = Double.parseDouble(t6.getText());
            da = t7.getText();

            try{
                String str = "insert into studentstrct (name,PRN_Number,Class,Fees_Paid,Date) values ('"+t1.getText()+"'" +
                        ",'"+t2.getText()+"','"+ncls.getSelectedItem()+"','"+t6.getText()+"','"+t7.getText()+"');";

                st3 = con.createStatement();

                int aa=JOptionPane.showConfirmDialog(null,"Please Conform the entered Amount\n" +
                        "DO YOU WANT TO CONTINUE!!!","WARNING", JOptionPane.OK_CANCEL_OPTION);
                if(aa==JOptionPane.OK_OPTION)
                {

                    st3.executeUpdate(str);

                     rs3 = st3.executeQuery("select Fees_paid from stdeadlock where PRN='"+t2.getText()+"';");
                     rs3.next();

                     nn = rs3.getDouble(1)+Double.parseDouble(t6.getText());

                    st3.executeUpdate("update stdeadlock set Fees_paid='"+(nn)+"' " +
                            "where PRN='"+t2.getText()+"';");

                    JOptionPane.showMessageDialog(null, "Data UPDATED Successfully.", "NOTICE"
                            ,JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ignored){}
        }

    }

    public static void main(String[] args) {
        new FeesEntry();
    }
}
