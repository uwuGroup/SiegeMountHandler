package me.asakura_kukii.siegemounthandler.deserializer.loader.common;

import me.asakura_kukii.siegemounthandler.data.FileData;
import me.asakura_kukii.siegemounthandler.deserializer.loader.plugin.ConfigLoader;
import me.asakura_kukii.siegemounthandler.utility.FileHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

public enum LoaderType {
    CONFIG(new ConfigLoader());

    public final HashMap<String, FileData> map;
    public final File folder;
    public final Loader fIO;
    public final HashMap<String, Object> subMap;


    LoaderType(Loader fIO) {
        this.folder = FileHandler.loadSubfolder(this.name().toLowerCase(Locale.ROOT));
        this.map = new HashMap<String, FileData>();
        this.fIO = fIO;
        this.subMap = new HashMap<String, Object>();
    }
}
