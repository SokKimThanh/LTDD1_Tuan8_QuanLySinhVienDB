package tdc.edu.quanlysinhvien;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewDanhSachSinhVien extends AppCompatActivity {
    DBSinhVien dbSinhVien;
    EditText edtMaSV, edtTenSV, edtGioiTinhSV;
    Button btnDocDL, btnThemDL, btnXoaDL, btnSuaDL;

    ListView lvDanhSachSinhVien;

    ArrayAdapter<SinhVien> sinhVienArrayAdapter;

    List<SinhVien> listsv = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    /**
     * phương thức khởi tạo các sự kiện
     * cho các nút trong ứng dụng.
     * Các sự kiện bao gồm việc đọc
     * dữ liệu từ cơ sở dữ liệu,
     * thêm dữ liệu mới, chọn một mục
     * từ danh sách, xóa một mục từ
     * cơ sở dữ liệu và cập nhật một
     * mục trong cơ sở dữ liệu
     */
    private void setEvent() {
        KhoiTao();
        // Đọc dữ liệu từ cơ sở dữ liệu khi nhấp vào nút Đọc DL
        btnDocDL.setOnClickListener(v -> {
            listsv.clear();
            // Kiểm tra xem cơ sở dữ liệu có rỗng không
            if ((long) dbSinhVien.DocDL().size() <= 0) {
                Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
                return;
            }
            // Thêm dữ liệu từ cơ sở dữ liệu vào danh sách và cập nhật giao diện
            listsv.addAll(dbSinhVien.DocDL());
            sinhVienArrayAdapter.notifyDataSetChanged();
        });
        // Thêm dữ liệu mới khi nhấp vào nút Thêm DL
        btnThemDL.setOnClickListener(v -> {
            // Kiểm tra xem các trường nhập liệu có rỗng không
            if (edtMaSV.getText().length() <= 0) {
                edtMaSV.setError("Nhap ma sv");
                return;
            }
            if (edtTenSV.getText().length() <= 0) {
                edtTenSV.setError("Nhap Ten sv");
                return;
            }
            if (edtGioiTinhSV.getText().length() <= 0) {
                edtGioiTinhSV.setError("Nhap gioi tinh sv");
                return;
            }

            // Tạo đối tượng SinhVien mới từ các trường nhập liệu
            SinhVien sv = new SinhVien();
            sv.setMa(edtMaSV.getText().toString());
            sv.setTen(edtTenSV.getText().toString());
            sv.setGioitinh(edtGioiTinhSV.getText().toString());
            // Thêm đối tượng SinhVien vào cơ sở dữ liệu
            dbSinhVien.ThemDL(sv);
            Toast.makeText(this, "ThemThanhCong", Toast.LENGTH_SHORT).show();
            LamMoiListView();
            LamMoiField();
        });

        // Chọn một mục từ danh sách khi nhấp vào một mục trong ListView
        lvDanhSachSinhVien.setOnItemClickListener((parent, view, position, id) -> {
            SinhVien sinhVien = listsv.get(position);
            edtMaSV.setText(sinhVien.getMa());
            edtTenSV.setText(sinhVien.getTen());
            edtGioiTinhSV.setText(sinhVien.getGioitinh());
        });

        // Xóa một mục từ cơ sở dữ liệu khi nhấp vào nút Xóa DL
        btnXoaDL.setOnClickListener(v -> {
            if (edtMaSV.getText().length() <= 0) {
                edtMaSV.setError("Nhap ma sv");
                return;
            }
            SinhVien sv = new SinhVien();
            sv.setMa(edtMaSV.getText().toString());
            dbSinhVien.XoaDL(sv);
            Toast.makeText(this, "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
            LamMoiListView();
            LamMoiField();
        });
        // Cập nhật một mục trong cơ sở dữ liệu khi nhấp vào nút Sửa DL
        btnSuaDL.setOnClickListener(v -> {
            if (edtMaSV.getText().length() <= 0) {
                edtMaSV.setError("Nhap ma sv");
                return;
            }
            if (edtTenSV.getText().length() <= 0) {
                edtTenSV.setError("Nhap Ten sv");
                return;
            }
            if (edtGioiTinhSV.getText().length() <= 0) {
                edtGioiTinhSV.setError("Nhap gioi tinh sv");
                return;
            }

            SinhVien sv = new SinhVien();
            sv.setMa(edtMaSV.getText().toString());
            sv.setTen(edtTenSV.getText().toString());
            sv.setGioitinh(edtGioiTinhSV.getText().toString());
            dbSinhVien.SuaDL(sv);
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            LamMoiListView();
            LamMoiField();
        });
    }


    /**
     * Làm mới các field
     */
    private void LamMoiField() {
        edtMaSV.setText("");
        edtTenSV.setText("");
        edtGioiTinhSV.setText("");
        edtMaSV.clearFocus();
        edtTenSV.clearFocus();
        edtGioiTinhSV.clearFocus();
    }

    /**
     * Làm mới danh sách listview
     */
    private void LamMoiListView() {
        listsv.clear();
        if ((long) dbSinhVien.DocDL().size() <= 0) {
            listsv.addAll(dbSinhVien.DocDL());
            sinhVienArrayAdapter.notifyDataSetChanged();
            Toast.makeText(this, "DB Rỗng không có dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }
        listsv.addAll(dbSinhVien.DocDL());
        sinhVienArrayAdapter.notifyDataSetChanged();
    }

    private void KhoiTao() {
        // dbsinhvien truy cập dữ liệu DB
        dbSinhVien = new DBSinhVien(this);
        // adapter hiển thị dữ liệu lên listview
        sinhVienArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listsv);
        lvDanhSachSinhVien.setAdapter(sinhVienArrayAdapter);
    }

    private void setControl() {
        edtMaSV = findViewById(R.id.edtMaSV);
        edtTenSV = findViewById(R.id.edtTenSV);
        edtGioiTinhSV = findViewById(R.id.edtGioiTinhSV);
        btnDocDL = findViewById(R.id.btnDocDL);
        btnThemDL = findViewById(R.id.btnThemDL);
        btnXoaDL = findViewById(R.id.btnXoaDL);
        btnSuaDL = findViewById(R.id.btnSuaDL);
        lvDanhSachSinhVien = findViewById(R.id.lvDanhSachSinhVien);
    }
}