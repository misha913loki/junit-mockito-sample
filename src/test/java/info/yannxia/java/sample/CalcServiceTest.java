package info.yannxia.java.sample;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by yann on 2017/6/13.
 */
public class CalcServiceTest {

    private CalcService calcService = new CalcService();

    @Test
    public void test_compoundingCalc() throws Exception {
        Field field = calcService.getClass().getDeclaredField("monthlyRate");
        field.setAccessible(true);
        ReflectionUtils.setField(field, calcService, new BigDecimal("0.01"));

        BigDecimal total = calcService.compoundingCalc(new BigDecimal("4000"), 1);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("4000")) == 0);


        total = calcService.compoundingCalc(new BigDecimal("4000"), 2);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("8040")) == 0);

        total = calcService.compoundingCalc(new BigDecimal("4000"), 4);
        Assert.assertTrue("total money error", total.compareTo(new BigDecimal("16241.604")) == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_compoundingCalcIllegalArgumentMonthlyPrincipal() {
        calcService.compoundingCalc(BigDecimal.ZERO, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_compoundingCalcIllegalArgumentMonth() {
        calcService.compoundingCalc(BigDecimal.TEN, 0);
    }

}