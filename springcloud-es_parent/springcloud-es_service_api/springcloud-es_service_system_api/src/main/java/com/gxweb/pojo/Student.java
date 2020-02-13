package com.gxweb.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * student实体类
 * @author 黑马架构师2.5
 *
 */
@Table(name="student")
@Document(indexName = "stuindex", type = "doc")   //要加,不然报空指针异常
public class Student implements Serializable {

	@Id
	private Integer id;//id
	private String classAndGrade;//班级
	private String candidateNumber;//考生号
	private String name;//姓名
	private String sex;//性别
	private String nameOfMajor;//专业名称
	private String ethnic;//名族
	private String politicsStatus;//政治面貌
	private String AdmissionToTheProvince;//录取省份
	private String patch;//批次
	private String SourceProvinces;//来源省份
	private String TheSource;//来源市
	private String AreaCountyCityStates;//区县市州


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Student() {
	}

	public Student(Integer id, String classAndGrade, String candidateNumber, String name, String sex, String nameOfMajor, String ethnic, String politicsStatus, String admissionToTheProvince, String patch, String sourceProvinces, String theSource, String areaCountyCityStates) {
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

	@Override
	public String toString() {
		return "Student{" +
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
