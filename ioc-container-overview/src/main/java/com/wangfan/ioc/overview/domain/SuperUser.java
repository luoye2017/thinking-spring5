package com.wangfan.ioc.overview.domain;

import com.wangfan.ioc.overview.annotation.Super;

/**
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-08-29
 */
@Super
public class SuperUser extends User{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }

}
