package com.example.type;

public class DataSourceType {

    public enum DataBaseType {
        Master, Slave
    }

    // 使用ThreadLocal保证线程安全
    private static final ThreadLocal<DataBaseType> TYPE = new ThreadLocal<DataBaseType>();

    public static void setDataBaseType(DataBaseType dataBaseType) {
        if (dataBaseType == null) {
            throw new NullPointerException();
        }
        TYPE.set(dataBaseType);
    }

    public static DataBaseType getDataBaseType() {
        DataBaseType dataBaseType = TYPE.get() == null ? DataBaseType.Master : TYPE.get();
        return dataBaseType;
    }

    public static void clearDataBaseType() {
        TYPE.remove();
    }
}
