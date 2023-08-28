import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReportCard extends JFrame implements ActionListener {

    JFrame f,f1;
    Connection con;
    Statement st,st1,st2,st3;
    ResultSet rs,rs1,rs2,rs3;
    JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9;
    JLabel l1,l2,l3;
    JTextField t1,t2,t3;
    JComboBox<String> cls;
    JButton b1;
    JTable jt;
    String temptext, std,a;

    public ReportCard()
    {
        try {
            con = DriverManager.getConnection("JDBC:mysql://localhost:3306/school","root","root");
        } catch (SQLException ignored) {}


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

        f = new JFrame("!!Report Card Panel!!");
        f.setLayout(new GridLayout(5,1));

        p1 = new JPanel(new GridLayout(1,1));
        p2 = new JPanel(new GridLayout(1,2));
        p3 = new JPanel(new GridLayout(1,3));
        p4 = new JPanel(new GridLayout(1,3));
        p5 = new JPanel(new GridLayout(1,3));
        p6 = new JPanel(new GridLayout(1,3));
        p7 = new JPanel(new GridLayout(1,3));
        p8 = new JPanel(new GridLayout(1,3));
        p9 = new JPanel(new GridLayout(1,3));

        l1 = new JLabel("Welcome to Al-Farooque U.H School");
        l1.setFont(new Font("Times New Roman",Font.BOLD,20));
        l1.setVisible(true);

        l2 = new JLabel("Enter Your PRN Number:");
        l2.setFont(new Font("Times New Roman",Font.BOLD,16));
        l2.setVisible(true);
        l3 = new JLabel("Select Class");
        l3.setFont(new Font("Times New Roman",Font.BOLD,16));

        t1 = new JTextField(15);
        t2 = new JTextField(15);
        t3 = new JTextField(15);

        b1 = new JButton("Submit");

        p1.add(l1);
        p2.add(l2);
        p2.add(t1);


        p3.add(l3);
        p3.add(cls);

        p5.add(new JLabel(""));
        p5.add(b1);
        p5.add(new JLabel(""));

        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(p5);

        f.setSize(500,350);
        f.setVisible(true);

        b1.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {

            f1 = new JFrame("Result Sheet");
            f1.setLayout(new GridLayout(4,1));
            f1.setSize(500,400);
            f1.setVisible(true);

            temptext = t1.getText();
            std = (String) cls.getSelectedItem();
            try {
                st = con.createStatement();
                rs = st.executeQuery(" select count(subject) from exams where prn_number='"
                        +temptext+"' and class='"+ std +"';");

                rs.next();
                int aaa= Integer.parseInt(rs.getString(1));
                String [][] ss=new String[aaa+1][2];
                String[] ss2 ={"Subject","Marks"};
                st2 = con.createStatement();
                rs2 = st2.executeQuery("select * from exams where prn_number='"+temptext+"' and class='"+ std +"';");
                ss[0][0]="Subject             ";
                ss[0][1]="Marks Obtained    ";
                int row=1;
                while (rs2.next())
                {
                    ss[row][0]=rs2.getString(3);
                    ss[row][1]=rs2.getString(4);
                    row=row+1;
                }

                st3 = con.createStatement();
                rs3 = st3.executeQuery("select * from exams where prn_number='"+temptext+"' and class='"+ std +"';");

                rs3.next();
                t2.setText(rs3.getString("name"));
                t2.setEditable(false);
                t3.setText(rs3.getString("Class"));
                t3.setEditable(false);

//                JOptionPane.showMessageDialog(null,"hello");


                jt = new JTable(ss, ss2);
                jt.setVisible(true);

                p6.add(new JLabel("Name of Student"));
                p6.add(t2);
                p6.add(new JLabel(""));


                p7.add(new JLabel("Class of Student: "));
                p7.add(t3);
                p7.add(new JLabel(""));

                p8.add(jt);

                f1.add(p1);
                f1.add(p6);
                f1.add(p7);
                f1.add(p8);



            } catch (SQLException ignored) {}
        }
    }
}
