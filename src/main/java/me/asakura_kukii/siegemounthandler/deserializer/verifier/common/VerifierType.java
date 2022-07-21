package me.asakura_kukii.siegemounthandler.deserializer.verifier.common;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.*;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.basiclist.*;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeBoolean;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeDouble;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeFloat;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeInteger;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeString;

public enum VerifierType {
    STRING(new SiegeString()),
    COLORED_STRING(new SiegeColoredString()),
    BOOLEAN(new SiegeBoolean()),
    INTEGER(new SiegeInteger()),
    FLOAT(new SiegeFloat()),
    DOUBLE(new SiegeDouble()),

    LIST_STRING(new SiegeStringList()),
    LIST_COLORED_STRING(new SiegeColoredStringList()),
    LIST_BOOLEAN(new SiegeBooleanList()),
    LIST_INTEGER(new SiegeIntegerList()),
    LIST_FLOAT(new SiegeFloat()),
    LIST_DOUBLE(new SiegeDoubleList());

    public Verifier f;

    VerifierType(Verifier f) {
        this.f = f;
    }
}
