package com.shpakovskiy.roomsandlamps.util;

import org.junit.Assert;
import org.junit.Test;

public class HibernateSessionFactoryUtilTest {

    @Test
    public void SessionFactoryShouldBeNotNull() {
        Assert.assertNotNull(HibernateSessionFactoryUtil.getSessionFactory());
    }
}
