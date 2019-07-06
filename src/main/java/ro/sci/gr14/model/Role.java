package ro.sci.gr14.model;

public enum Role {
    ADMIN(0),HANDYMAN(1),CUSTOMER(2);

    public final int val;

    private Role(int val){
        this.val=val;
    }

    public int getRoleValue(){
        return val;
    }
}
