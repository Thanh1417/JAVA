package GGV;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;

public class GUIGiangvien extends JFrame implements ActionListener{
    private XLGV xlgv = new XLGV();
    private int currentRow = -1;
    private JTable tbGiangVien;
    private JTextField txtMaDD, txtHoten, txtGT, txtDonvi, txtSoct;
    private JButton btnThem, btnXoa, btnSua, btnSearch;
    public GUIGiangvien() {
        setTitle("DAGV");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //Tao JPanel
        JPanel panel = new JPanel(new GridBagLayout()); //GridBagLayout làm trình quản lý bố cục cho JPanel
        GridBagConstraints gbc = new GridBagConstraints();  //định nghĩa cách bố trí từng thành phần trong lưới
        gbc.insets = new Insets(10, 10, 10, 10);    //Khoảng cách padding giữa các thành phần trong bảng

        //Cac truong thong tin
        JLabel lbMaDD = new JLabel("Ma dinh danh:");
        txtMaDD = new JTextField(20);
        JLabel lbTen = new JLabel("Ho ten:");
        txtHoten = new JTextField(20);
        JLabel lbGT = new JLabel("Gioi tinh:");
        txtGT = new JTextField(20);
        JLabel lbDonvi = new JLabel("Don vi:");
        txtDonvi = new JTextField(20);
        JLabel lbSoCT = new JLabel("So cong trinh:");
        txtSoct = new JTextField(20);

        //Them cac phan tu vao bo cuc
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lbMaDD, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtMaDD, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lbTen, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtHoten, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lbGT, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtGT, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lbDonvi, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtDonvi, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lbSoCT, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(txtSoct, gbc);

        //Button
        btnThem = new JButton("Them");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(btnThem, gbc);

        btnXoa = new JButton("Xoa");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(btnXoa, gbc);

        btnSua = new JButton("Sua");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(btnSua, gbc);

        btnSearch = new JButton("Tim kiem");
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(btnSearch, gbc);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);

        //Bang du lieu
        String[] columnNames = {"MA DINH DANH", "HO TEN", "GIOI TINH", "DON VI", "SO CONG TRINH"};
        DefaultTableModel modelGV = new DefaultTableModel(columnNames, 0);
        tbGiangVien = new JTable(modelGV);
        JScrollPane scrollPane = new JScrollPane(tbGiangVien);
        add(scrollPane, BorderLayout.CENTER);

        //Hien thi du lieu
        xlgv.getCon();
        loadData(modelGV);

        //Listener: Hiển thị dữ liệu lên các trường khi chọn dữ liệu trong bảng
        tbGiangVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbGiangVien.getSelectedRow();
                if(selectedRow >= 0) {
                    txtMaDD.setText(modelGV.getValueAt(selectedRow, 0).toString());
                    txtHoten.setText(modelGV.getValueAt(selectedRow, 1).toString());
                    txtGT.setText(modelGV.getValueAt(selectedRow, 2).toString());
                    txtDonvi.setText(modelGV.getValueAt(selectedRow, 3).toString());
                    txtSoct.setText(modelGV.getValueAt(selectedRow, 4).toString());
                }
            }
        });

        //Them
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String mdd = txtMaDD.getText();
                String ht = txtHoten.getText();
                String gt = txtGT.getText();
                String dv = txtDonvi.getText();
                int sct = Integer.parseInt(txtSoct.getText());

                boolean res = xlgv.insertGV(new Giangvien(mdd, ht, gt, dv, sct));
                if (res) {
                    loadData(modelGV);
                    JOptionPane.showMessageDialog(null, "Them thanh cong");
                } else {
                    JOptionPane.showMessageDialog(null, "Them khong thanh cong");
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mdd = txtMaDD.getText();
                String ht = txtHoten.getText();
                String gt = txtGT.getText();
                String dv = txtDonvi.getText();
                int sct = Integer.parseInt(txtSoct.getText());

                boolean res = xlgv.updateGV(new Giangvien(mdd, ht, gt, dv, sct));
                if (res) {
                    loadData(modelGV);
                    JOptionPane.showMessageDialog(null, "Sua thanh cong");
                } else {
                    JOptionPane.showMessageDialog(null, "Sua khong thanh cong");
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mdd = txtMaDD.getText();
                String ht = txtHoten.getText();
                String gt = txtGT.getText();
                String dv = txtDonvi.getText();
                int sct = Integer.parseInt(txtSoct.getText());

                boolean res = xlgv.deleteGV(new Giangvien(mdd, ht, gt, dv, sct));
                if (res) {
                    loadData(modelGV);
                    JOptionPane.showMessageDialog(null, "Xoa thanh cong");
                } else {
                    JOptionPane.showMessageDialog(null, "Xoa khong thanh cong");
                }
            }
        });

    }

    private void loadData(DefaultTableModel tbmodel) {
        ResultSet rs = xlgv.getGV();

        try {
            tbmodel.setRowCount(0);

            while (rs.next()) {
                tbmodel.addRow(new Object[] {
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                });
            }
            tbmodel.fireTableDataChanged(); //Cap nhat du lieu bang
        } catch (Exception e) {
            System.out.println("Loi loadData: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


