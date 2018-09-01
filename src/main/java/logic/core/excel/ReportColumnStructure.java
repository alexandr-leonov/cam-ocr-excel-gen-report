package logic.core.excel;

public enum ReportColumnStructure {
    SHOPPING_DATE,
    MARKET_NAME,
    DESCRIPTION_GIFT,
    COUNT_GIFTS,
    COST_GIFT;

    public static ReportColumnStructure[] getValues(){
        return new ReportColumnStructure[]{SHOPPING_DATE,MARKET_NAME,DESCRIPTION_GIFT,COUNT_GIFTS,COST_GIFT};
    }
}
