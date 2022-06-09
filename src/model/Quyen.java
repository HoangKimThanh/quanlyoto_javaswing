package model;

public class Quyen {
    private String chucVu;
    private String tenloaiquanly;
    private int them;
    private int xoa;
    private int sua;
    private int xem;

    public int getThem() {
        return them;
    }

    public void setThem(int them) {
        this.them = them;
    }

    public int getXoa() {
        return xoa;
    }

    public void setXoa(int xoa) {
        this.xoa = xoa;
    }

    public int getSua() {
        return sua;
    }

    public void setSua(int sua) {
        this.sua = sua;
    }

    public int getXem() {
        return xem;
    }

    public void setXem(int xem) {
        this.xem = xem;
    }
    

    public Quyen() {
    }

    public Quyen(String chucVu,String tenloaiquanly, int them, int xoa, int sua, int xem) {
        this.chucVu = chucVu;
        this.tenloaiquanly = tenloaiquanly;
        this.them = them;
        this.xoa = xoa;
        this.sua = sua;
        this.xem = xem;
        
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    public String getTenloaiquanly() {
        return tenloaiquanly;
    }

    public void setTenloaiquanly(String tenloaiquanly) {
        this.tenloaiquanly = tenloaiquanly;
    }

    
   

}
