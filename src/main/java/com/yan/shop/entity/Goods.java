package com.yan.shop.entity;

import java.sql.Timestamp;

public class Goods {

    private Integer haveProduct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.start_name
     *
     * @mbggenerated
     */
    private String startName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.search_times
     *
     * @mbggenerated
     */
    private Integer searchTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.key_word
     *
     * @mbggenerated
     */
    private String keyWord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.have_brush_single
     *
     * @mbggenerated
     */
    private Integer haveBrushSingle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.evaluation_times
     *
     * @mbggenerated
     */
    private Integer evaluationTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.have_pictrue_evaluation_times
     *
     * @mbggenerated
     */
    private Integer havePictrueEvaluationTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.sales_count
     *
     * @mbggenerated
     */
    private Integer salesCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.brush_single_times
     *
     * @mbggenerated
     */
    private Integer brushSingleTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.brush_single_cost
     *
     * @mbggenerated
     */
    private Double brushSingleCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.details_optimization
     *
     * @mbggenerated
     */
    private Integer detailsOptimization;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.analyze
     *
     * @mbggenerated
     */
    private String analyze;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.create_time
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.update_time
     *
     * @mbggenerated
     */
    private Timestamp updateTime;


    private int haveTogether;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSearchTimes() {
        return searchTimes;
    }

    public void setSearchTimes(Integer searchTimes) {
        this.searchTimes = searchTimes;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getHaveBrushSingle() {
        return haveBrushSingle;
    }

    public void setHaveBrushSingle(Integer haveBrushSingle) {
        this.haveBrushSingle = haveBrushSingle;
    }

    public Integer getEvaluationTimes() {
        return evaluationTimes;
    }

    public void setEvaluationTimes(Integer evaluationTimes) {
        this.evaluationTimes = evaluationTimes;
    }

    public Integer getHavePictrueEvaluationTimes() {
        return havePictrueEvaluationTimes;
    }

    public void setHavePictrueEvaluationTimes(Integer havePictrueEvaluationTimes) {
        this.havePictrueEvaluationTimes = havePictrueEvaluationTimes;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getBrushSingleTimes() {
        return brushSingleTimes;
    }

    public void setBrushSingleTimes(Integer brushSingleTimes) {
        this.brushSingleTimes = brushSingleTimes;
    }

    public Double getBrushSingleCost() {
        return brushSingleCost;
    }

    public void setBrushSingleCost(Double brushSingleCost) {
        this.brushSingleCost = brushSingleCost;
    }

    public Integer getDetailsOptimization() {
        return detailsOptimization;
    }

    public void setDetailsOptimization(Integer detailsOptimization) {
        this.detailsOptimization = detailsOptimization;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAnalyze() {
        return analyze;
    }

    public void setAnalyze(String analyze) {
        this.analyze = analyze;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getHaveTogether() {
        return haveTogether;
    }

    public void setHaveTogether(int haveTogether) {
        this.haveTogether = haveTogether;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", startName='" + startName + '\'' +
                ", type=" + type +
                ", searchTimes=" + searchTimes +
                ", keyWord='" + keyWord + '\'' +
                ", haveBrushSingle=" + haveBrushSingle +
                ", evaluationTimes=" + evaluationTimes +
                ", havePictrueEvaluationTimes=" + havePictrueEvaluationTimes +
                ", salesCount=" + salesCount +
                ", brushSingleTimes=" + brushSingleTimes +
                ", brushSingleCost=" + brushSingleCost +
                ", detailsOptimization=" + detailsOptimization +
                ", remark='" + remark + '\'' +
                ", analyze='" + analyze + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", haveTogether=" + haveTogether +
                '}';
    }

    public Integer getHaveProduct() {
        return haveProduct;
    }

    public void setHaveProduct(Integer haveProduct) {
        this.haveProduct = haveProduct;
    }
}