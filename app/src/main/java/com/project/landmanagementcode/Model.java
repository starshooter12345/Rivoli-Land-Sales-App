package com.project.landmanagementcode;

public class Model {
    String id,image,landtitle,extentinperches,priceperperch,landaddress,landdescription,sellername,sellerphone,selleremail,addTimeStamp,updateTimeStamp;

    public Model(String id, String image, String landtitle, String extentinperches, String priceperperch, String landaddress, String landdescription, String sellername, String sellerphone, String selleremail, String addTimeStamp, String updateTimeStamp) {
        this.id = id;
        this.image = image;
        this.landtitle = landtitle;
        this.extentinperches = extentinperches;
        this.priceperperch = priceperperch;
        this.landaddress = landaddress;
        this.landdescription = landdescription;
        this.sellername = sellername;
        this.sellerphone = sellerphone;
        this.selleremail = selleremail;
        this.addTimeStamp = addTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLandtitle() {
        return landtitle;
    }

    public void setLandtitle(String landtitle) {
        this.landtitle = landtitle;
    }

    public String getExtentinperches() {
        return extentinperches;
    }

    public void setExtentinperches(String extentinperches) {
        this.extentinperches = extentinperches;
    }

    public String getPriceperperch() {
        return priceperperch;
    }

    public void setPriceperperch(String priceperperch) {
        this.priceperperch = priceperperch;
    }

    public String getLandaddress() {
        return landaddress;
    }

    public void setLandaddress(String landaddress) {
        this.landaddress = landaddress;
    }

    public String getLanddescription() {
        return landdescription;
    }

    public void setLanddescription(String landdescription) {
        this.landdescription = landdescription;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getSellerphone() {
        return sellerphone;
    }

    public void setSellerphone(String sellerphone) {
        this.sellerphone = sellerphone;
    }

    public String getSelleremail() {
        return selleremail;
    }

    public void setSelleremail(String selleremail) {
        this.selleremail = selleremail;
    }

    public String getAddTimeStamp() {
        return addTimeStamp;
    }

    public void setAddTimeStamp(String addTimeStamp) {
        this.addTimeStamp = addTimeStamp;
    }

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(String updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }
}
