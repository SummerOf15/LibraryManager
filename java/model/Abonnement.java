package model;

public enum Abonnement {
    BASIC(1,"BASIC"),
    PREMIUM(2,"PREMIUM"),
    VIP(3,"VIP");

    private int value;
    private String name;

    Abonnement(int val, String name){
        this.value=val;
        this.name=name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Abonnement findByName(String name){
        for(Abonnement ab:Abonnement.values()){
            if(ab.name.equals(name))
                return ab;
        }
        return null;
    }
}
