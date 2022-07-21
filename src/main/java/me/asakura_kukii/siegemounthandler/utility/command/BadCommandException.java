package me.asakura_kukii.siegemounthandler.utility.command;

public class BadCommandException extends RuntimeException{
    private String typeStr;

    public BadCommandException(String typeStr){
        this.typeStr = typeStr;
    }

    public BadCommandException(String anInt, Exception e) {
        super(e);
        this.typeStr = anInt;
    }

    public String getTypeStr(){
        return typeStr;
    }
}
