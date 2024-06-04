package test;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
public class Student extends Parent implements Serializable, Cloneable {

    Integer id;
    String name;
    Integer age;
    Double avgGrade;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
