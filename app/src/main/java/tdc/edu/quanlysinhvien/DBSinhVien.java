package tdc.edu.quanlysinhvien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBSinhVien extends SQLiteOpenHelper {

    /**
     * Khởi tạo DBSINHVIEN
     */
    public DBSinhVien(@Nullable Context context) {
        // Gọi hàm khởi tạo của lớp cha SQLiteOpenHelper
        super(context, "dbSinhVien", null, 1);
    }

    /**
     * Tạo bảng tbSinhVien
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Câu lệnh SQL để tạo bảng tblSinhVien với các cột ma, ten, gioitinh
        String sql = "Create table tblSinhVien(ma Text, ten Text, gioitinh Text)";

        // Thực hiện câu lệnh SQL để tạo bảng
        db.execSQL(sql);
    }

    /**
     * Thêm sinh viên
     *
     * @param sv Sinh viên
     */
    public void ThemDL(SinhVien sv) {
        // Câu lệnh SQL để thêm một dòng vào bảng tblSinhVien
        String sql = "Insert into tblSinhVien values(?,?,?)";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng SinhVien
        db.execSQL(sql, new String[]{sv.getMa(), sv.getTen(), sv.getGioitinh()});
    }

    /**
     * Xóa sinh viên
     *
     * @param sv sinhvien
     */
    public void XoaDL(SinhVien sv) {
        // Câu lệnh SQL để xóa một dòng từ bảng tblSinhVien dựa trên mã sinh viên
        String sql = "Delete from tblSinhVien where ma = ?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với mã sinh viên từ đối tượng SinhVien
        db.execSQL(sql, new String[]{sv.getMa()});
    }


    /**
     * Sửa thông tin sinh viên
     *
     * @param sv The sinhvien.
     */
    public void SuaDL(SinhVien sv) {
        // Câu lệnh SQL để cập nhật thông tin sinh viên trong bảng tblSinhVien
        String sql = "Update tblSinhVien set ten = ?, gioitinh=? where ma=?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng SinhVien
        // sv.getTen() và sv.getGioitinh() sẽ thay thế cho ?, sv.getMa() sẽ thay thế cho ?
        db.execSQL(sql, new String[]{sv.getTen(), sv.getGioitinh(), sv.getMa()});
    }


    /**
     * Đọc dữ liệu từ db
     *
     * @return danh sách đối tượng sinh viên
     */
    public List<SinhVien> DocDL() {
        // Khởi tạo danh sách sinh viên
        List<SinhVien> listSinhVien = new ArrayList<>();

        // Câu lệnh SQL để lấy tất cả dữ liệu từ bảng tblSinhVien
        String sql = "Select * from tblSinhVien";

        // Mở cơ sở dữ liệu để đọc
        SQLiteDatabase db = getReadableDatabase();

        // Thực hiện truy vấn SQL và lưu kết quả vào Cursor
        Cursor cursor = db.rawQuery(sql, null);

        // Kiểm tra xem con trỏ có dữ liệu không
        if (cursor.moveToFirst()) {
            do {
                // Khởi tạo đối tượng sinh viên mới
                SinhVien sinhvien = new SinhVien();

                // Đọc dữ liệu từ cột 0 (Mã sinh viên) và cập nhật vào đối tượng
                sinhvien.setMa(cursor.getString(0).toString());

                // Đọc dữ liệu từ cột 1 (Tên sinh viên) và cập nhật vào đối tượng
                sinhvien.setTen(cursor.getString(1).toString());

                // Đọc dữ liệu từ cột 2 (Giới tính) và cập nhật vào đối tượng
                sinhvien.setGioitinh(cursor.getString(2).toString());

                // Thêm đối tượng sinh viên vào danh sách
                listSinhVien.add(sinhvien);
            } while (cursor.moveToNext()); // Di chuyển con trỏ đến hàng tiếp theo
        }

        // Trả về danh sách sinh viên
        return listSinhVien;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
