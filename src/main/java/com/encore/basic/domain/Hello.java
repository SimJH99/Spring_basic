package com.encore.basic.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@Data   // getter, setter 및 toString, equlas등 사전 구현
@NoArgsConstructor
public class Hello {
    private String name;
    private String email;
    private String password;

    //    Builder 패턴 직접 구현
    public Hello(MyBulider myBulider) {
        this.name = myBulider.name;
        this.email = myBulider.email;
        this.password = myBulider.password;
    }

    public static MyBulider bulider(){
        return new MyBulider();
    }

    public static class MyBulider {
        private String name;
        private String email;
        private String password;

        public MyBulider name(String name) {
            this.name = name;
            return this;
        }

        public MyBulider email(String email) {
            this.email = email;
            return this;
        }

        public MyBulider password(String password) {
            this.password = password;
            return this;
        }

        public Hello build () {
            return new Hello(this);
        }
    }
}
