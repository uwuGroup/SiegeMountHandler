package me.asakura_kukii.siegemounthandler.data;

import me.asakura_kukii.siegemounthandler.deserializer.loader.common.LoaderType;

public abstract class FileData {
    //information
    public String identifier;
    public String fileName;
    public LoaderType fT;
}
