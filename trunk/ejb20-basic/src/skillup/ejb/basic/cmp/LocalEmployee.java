package skillup.ejb.basic.cmp;

import java.util.Date;

import javax.ejb.EJBLocalObject;

public interface LocalEmployee extends EJBLocalObject {

	public String getEmpNo();

	public void setEmpNo(String empNo);

	public String getName();

	public void setName(String Name);

	public String getJob();

	public void setJob(String job);

	public String getMgr();

	public void setMgr(String mgr);

	public Date getHireDate();

	public void setHireDate(Date hireDate);

	public double getSal();

	public void setSal(double sal);

	public String getComm();

	public void setComm(String comm);

	public String getDeptNo();

	public void setDeptNo(String deptNo);

}
