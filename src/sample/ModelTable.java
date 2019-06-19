package sample;

public class ModelTable {

    public ModelTable(String model, String engineModel, String gasolineType,
                      Integer id, Integer year, Integer cylinderNum,
                      Integer doorsNum, Integer torque, Integer weight,
                      Integer wheelSize, Integer hp, String color) {
        this.model = model;
        this.engineModel = engineModel;
        this.gasolineType = gasolineType;
        this.id = id;
        this.year = year;
        this.cylinderNum = cylinderNum;
        this.doorsNum = doorsNum;
        this.torque = torque;
        this.weight = weight;
        this.wheelSize = wheelSize;
        this.hp = hp;
        this.color = color;

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getGasolineType() {
        return gasolineType;
    }

    public void setGasolineType(String gasolineType) {
        this.gasolineType = gasolineType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCylinderNum() {
        return cylinderNum;
    }

    public void setCylinderNum(Integer cylinderNum) {
        this.cylinderNum = cylinderNum;
    }

    public Integer getDoorsNum() {
        return doorsNum;
    }

    public void setDoorsNum(Integer doorsNum) {
        this.doorsNum = doorsNum;
    }

    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(Integer wheelSize) {
        this.wheelSize = wheelSize;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    private String model;
    private String engineModel;
    private String gasolineType;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String color;
    private Integer id,year,cylinderNum,doorsNum,torque,weight,wheelSize,hp;
}
