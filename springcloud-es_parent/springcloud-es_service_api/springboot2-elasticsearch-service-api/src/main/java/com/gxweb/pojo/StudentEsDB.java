package com.gxweb.pojo;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * studentEs实体类
 *
 * @author CYQ
 * indexName:索引库的名称,类似数据库
 * * type:类型，类似表
 */
@Document(indexName = "student_db", type = "student")
public class StudentEsDB implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;//user_id
    private String classAndGrade;//班级

    private String candidateNumber;//考生号
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;//姓名
    private String sex;//性别
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String nameOfMajor;//专业名称
    private String ethnic;//名族
    private String politicsStatus;//政治面貌
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String AdmissionToTheProvince;//录取省份
    private String patch;//批次
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String SourceProvinces;//来源省份
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String TheSource;//来源市
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String AreaCountyCityStates;//区县市州



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassAndGrade() {
        return classAndGrade;
    }

    public void setClassAndGrade(String classAndGrade) {
        this.classAndGrade = classAndGrade;
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNameOfMajor() {
        return nameOfMajor;
    }

    public void setNameOfMajor(String nameOfMajor) {
        this.nameOfMajor = nameOfMajor;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getAdmissionToTheProvince() {
        return AdmissionToTheProvince;
    }

    public void setAdmissionToTheProvince(String AdmissionToTheProvince) {
        this.AdmissionToTheProvince = AdmissionToTheProvince;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String getSourceProvinces() {
        return SourceProvinces;
    }

    public void setSourceProvinces(String SourceProvinces) {
        this.SourceProvinces = SourceProvinces;
    }

    public String getTheSource() {
        return TheSource;
    }

    public void setTheSource(String TheSource) {
        this.TheSource = TheSource;
    }

    public String getAreaCountyCityStates() {
        return AreaCountyCityStates;
    }

    public void setAreaCountyCityStates(String AreaCountyCityStates) {
        this.AreaCountyCityStates = AreaCountyCityStates;
    }

    public StudentEsDB(Long id, String classAndGrade, String candidateNumber, String name, String sex, String nameOfMajor, String ethnic, String politicsStatus, String admissionToTheProvince, String patch, String sourceProvinces, String theSource, String areaCountyCityStates) {
        this.id = id;
        this.classAndGrade = classAndGrade;
        this.candidateNumber = candidateNumber;
        this.name = name;
        this.sex = sex;
        this.nameOfMajor = nameOfMajor;
        this.ethnic = ethnic;
        this.politicsStatus = politicsStatus;
        AdmissionToTheProvince = admissionToTheProvince;
        this.patch = patch;
        SourceProvinces = sourceProvinces;
        TheSource = theSource;
        AreaCountyCityStates = areaCountyCityStates;
    }

    public StudentEsDB() {
    }

    @Override
    public String toString() {
        return "StudentEsDB{" +
                "id=" + id +
                ", classAndGrade='" + classAndGrade + '\'' +
                ", candidateNumber='" + candidateNumber + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nameOfMajor='" + nameOfMajor + '\'' +
                ", ethnic='" + ethnic + '\'' +
                ", politicsStatus='" + politicsStatus + '\'' +
                ", AdmissionToTheProvince='" + AdmissionToTheProvince + '\'' +
                ", patch='" + patch + '\'' +
                ", SourceProvinces='" + SourceProvinces + '\'' +
                ", TheSource='" + TheSource + '\'' +
                ", AreaCountyCityStates='" + AreaCountyCityStates + '\'' +
                '}';
    }
}
