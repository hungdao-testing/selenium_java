package com.aspire.loan.data.type;

public enum IncorporatePackageType {
    KICKSTART("Kickstart"),
    KICKSTART_PLUS("Kickstart Plus");

    private String packageName;

    IncorporatePackageType(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}
