package com.tables;

import javax.persistence.*;

@Entity
@Table(name = "head_department", schema = "public", catalog = "Messenger")
public class HeadDepartmentEntity {
    private int headDepartmentId;
    private long managerSystemId;
    private long departmentDepartmentId;

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="head_department_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name = "head_department_id")
    public int getHeadDepartmentId() {
        return headDepartmentId;
    }

    public void setHeadDepartmentId(int headDepartmentId) {
        this.headDepartmentId = headDepartmentId;
    }

    @Basic
    @Column(name = "manager_system_id")
    public long getManagerSystemId() {
        return managerSystemId;
    }

    public void setManagerSystemId(long managerSystemId) {
        this.managerSystemId = managerSystemId;
    }

    @Basic
    @Column(name = "department_department_id")
    public long getDepartmentDepartmentId() {
        return departmentDepartmentId;
    }

    public void setDepartmentDepartmentId(long departmentDepartmentId) {
        this.departmentDepartmentId = departmentDepartmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeadDepartmentEntity that = (HeadDepartmentEntity) o;

        if (headDepartmentId != that.headDepartmentId) return false;
        if (managerSystemId != that.managerSystemId) return false;
        if (departmentDepartmentId != that.departmentDepartmentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = headDepartmentId;
        result = 31 * result + (int) (managerSystemId ^ (managerSystemId >>> 32));
        result = 31 * result + (int) (departmentDepartmentId ^ (departmentDepartmentId >>> 32));
        return result;
    }
}
