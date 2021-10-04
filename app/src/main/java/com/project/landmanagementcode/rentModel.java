package com.project.landmanagementcode;

public class rentModel {
    String id,image,rtitle,rdes,rental,rseller,rarea,addTimeStamp,updateTimeStamp;

    public rentModel(String id, String image, String rtitle, String rarea, String rental, String rdes, String rseller, String addTimeStamp, String updateTimeStamp) {
        this.id = id;

        this.rtitle = rtitle;
        this.rarea = rarea;
        this.rental = rental;
        this.rdes = rdes;
        this.rseller = rseller;
        this.image = image;
        this.addTimeStamp = addTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public rentModel(String id, String image, String rtitle, String rarea, String rental, String rdes, String rseller) {
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

    public String getRtitle() {
        return rtitle;
    }
    public void setRtitle(String landtitle) {
        this.rtitle = rtitle;
    }

    public String getRarea() {  return rarea;  }
    public void setRarea(String rarea) {    this.rarea = rarea;   }

    public String getRental() {
        return rental;
    }
    public void setRental(String rental) {
        this.rental = rental;
    }

    public String getRdes() {
        return rdes;
    }
    public void setRdes(String rdes) {
        this.rdes = rdes;
    }

    public String getRseller() {
        return rseller;
    }
    public void setRseller(String rseller) { this.rseller = rseller;    }

    public String getAddTimeStamp() {
        return addTimeStamp;
    }
    public void setAddTimeStamp(String addTimeStamp) {
        this.addTimeStamp = addTimeStamp;
    }

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }
    public void setUpdateTimeStamp(String updateTimeStamp) { this.updateTimeStamp = updateTimeStamp; }
}
