package kr.co.crscube.crawler.model;


/**
 * Created by crs32 on 2019-07-25.
 */

public class CrawlerDataModel extends PaginationModel{

    private String label;
    private String range;
    private String unit;

    private PaginationModel paginationModel;

    public PaginationModel getPaginationModel() {
        return paginationModel;
    }

    public void setPaginationModel(PaginationModel paginationModel) {
        this.paginationModel = paginationModel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
